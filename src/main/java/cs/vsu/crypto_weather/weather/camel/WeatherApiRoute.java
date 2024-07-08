package cs.vsu.crypto_weather.weather.camel;

import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@RequiredArgsConstructor
public class WeatherApiRoute extends RouteBuilder {

    private final WeatherDataTransformationProcessor transformationProcessor;
    private final WeatherDataLoadProcessor loadProcessor;

    @Value("${weather_apikey}")
    private String apikey;

    private final String timerUri = "timer:weather_data_timer?period=10000&repeatCount=2",
            weatherApiRouteId = "weather_data_route", cityList = "Moscow;Dubai;London",
            splitToken = ";", cityNamePattern = "${body}",
            externalApiPattern = "http://api.weatherapi.com/v1/current.json?key={0}&q={1}&aqi=no?httpMethod=GET";

    private String getConfiguredExternalWeatherApi() {
        return MessageFormat.format(externalApiPattern, apikey, cityNamePattern);
    }

    @Override
    public void configure() {

        from(timerUri)
                .routeId(weatherApiRouteId)
                .process(exchange -> exchange.getIn().setBody(cityList))
                .split(body().tokenize(splitToken))
                    .log(body().toString())
                .toD(getConfiguredExternalWeatherApi())
                .process(transformationProcessor)
                    .log(body().toString())
                .process(loadProcessor)
                    .log(body().toString());

    }
}
