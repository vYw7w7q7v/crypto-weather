package cs.vsu.crypto_weather.weather.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class WeatherApiRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:mytimer?period=10000")
                .toD("http://api.weatherapi.com/v1/current.json?" +
                        "key=813eff2b8a174601b28101610240407&q=Moscow&aqi=no?" +
                        "httpMethod=GET")
                .split().jsonpath("$.location.name")
                .log(body().toString());

    }
}
