package cs.vsu.crypto_weather.weather.camel;

import cs.vsu.crypto_weather.weather.entity.WeatherData;
import lombok.RequiredArgsConstructor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static java.text.MessageFormat.format;

@Component
@RequiredArgsConstructor
public class WeatherApiRoute extends RouteBuilder {

    private final WeatherDataTransformationProcessor transformationProcessor;
    private final WeatherDataLoadProcessor weatherDataLoadProcessor;
    private final SendWeatherDataToKafkaProcessor sendWeatherDataToKafkaProcessor;

    @Value("${weather_apikey}")
    private String apikey;

    private final String CITY_LIST = "Moscow;Dubai;London";

    private String getConfiguredExternalWeatherApi() {
        var externalApiPattern = "http://api.weatherapi.com/v1/current.json?" +
                "key={0}&q={1}&aqi=no?httpMethod=GET";
        var cityNameFormat = "${body}";
        return format(externalApiPattern, apikey, cityNameFormat);
    }

    @Override
    public void configure() {

        var splitToken = ";";
        var timerUri = "timer:weather_data_timer?period=10000&repeatCount=2";
        var weatherApiRouteId = "weather_data_route";

        var requestWeatherDataLogMessage = "request weather data for city {0}!";
        var receiveWeatherDataLogMessage = "weather data received from external api!";
        var transformWeatherDataLogMessage = "weather data transformed to DTO: {0}!";
        var loadToDBWeatherDataLogMessage = "weather data loaded to database!";
        var loadToDBErrorWeatherDataLogMessage = "load to database error!!!";
        var sentToKafkaWeatherDataLogMessage = "weather data sent to kafka consumer!";

        from(timerUri)
                .routeId(weatherApiRouteId)
                .process(exchange -> exchange.getIn().setBody(CITY_LIST))
                .split(body().tokenize(splitToken))
                    .log(format(requestWeatherDataLogMessage, body()))
                .toD(getConfiguredExternalWeatherApi())
                    .log(receiveWeatherDataLogMessage)
                .process(transformationProcessor)
                    .log(format(transformWeatherDataLogMessage, bodyAs(WeatherData.class)))
                .doTry()
                    .process(weatherDataLoadProcessor)
                    .log(loadToDBWeatherDataLogMessage)
                .endDoTry()
                .doCatch(RuntimeException.class)
                    .log(loadToDBErrorWeatherDataLogMessage)
                .doFinally()
                .process(sendWeatherDataToKafkaProcessor)
                    .log(sentToKafkaWeatherDataLogMessage);

    }
}
