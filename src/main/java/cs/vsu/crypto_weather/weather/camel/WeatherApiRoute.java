package cs.vsu.crypto_weather.weather.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherApiRoute extends RouteBuilder {

    @Autowired
    private WeatherDataTransformationProcessor transformationProcessor;
    @Autowired
    private WeatherDataLoadProcessor loadProcessor;

    @Value("${weather_apikey}")
    private String apikey;

    private String cityList = "Moscow;London";

    private String getConfiguredExternalWeatherApi(String cityName) {
        return String.format(
                "http://api.weatherapi.com/v1/current.json?" +
                "key=%s" +
                "&q=%s" +
                "&aqi=no?httpMethod=GET",
                apikey, cityName);
    }

    @Override
    public void configure() throws Exception {

        from("timer:weather_data_timer?period=10000&repeatCount=3")
                .routeId("weather_data_route")
                .process(exchange -> exchange.getIn().setBody(cityList))
                .split(body().tokenize(";"))
                    .log(body().toString())
                .toD(getConfiguredExternalWeatherApi(body().toString()))
                .process(transformationProcessor)
                    .log(body().toString())
                .process(loadProcessor)
                    .log(body().toString());

    }
}
