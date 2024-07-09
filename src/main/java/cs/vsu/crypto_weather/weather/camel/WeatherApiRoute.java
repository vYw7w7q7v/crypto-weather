package cs.vsu.crypto_weather.weather.camel;

import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static java.text.MessageFormat.format;

@Component
@RequiredArgsConstructor
public class WeatherApiRoute extends RouteBuilder {

    private final WeatherDataTransformationProcessor transformationProcessor;
    private final WeatherDataLoadProcessor loadProcessor;

    @Value("${weather_apikey}")
    private String apikey;

    private final String CITY_LIST = "Moscow;Dubai;London";

    private String getConfiguredExternalWeatherApi() {
        String cityNameFormat = "${body}";
        String externalApiPattern = "http://api.weatherapi.com/v1/current.json?key={0}&q={1}&aqi=no?httpMethod=GET";
        return format(externalApiPattern, apikey, cityNameFormat);
    }

    @Override
    public void configure() {

        String splitToken = ";";
        String timerUri = "timer:weather_data_timer?period=10000&repeatCount=2";
        String weatherApiRouteId = "weather_data_route";
        from(timerUri)
                .routeId(weatherApiRouteId)
                .process(exchange -> exchange.getIn().setBody(CITY_LIST))
                .split(body().tokenize(splitToken))
                    .log(body().toString())
                .toD(getConfiguredExternalWeatherApi())
                .process(transformationProcessor)
                    .log(body().toString())
                .process(loadProcessor)
                    .log(body().toString());

    }
}
