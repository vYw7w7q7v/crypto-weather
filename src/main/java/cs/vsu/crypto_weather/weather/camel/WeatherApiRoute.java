package cs.vsu.crypto_weather.weather.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherApiRoute extends RouteBuilder {
    @Autowired
    private CamelContext camelContext;

    @Autowired
    private WeatherDataTransformationProcessor transformationProcessor;
    @Autowired
    private WeatherDataLoadProcessor loadProcessor;

    @Override
    public void configure() throws Exception {

        from("timer:mytimer?period=10000")
                .routeId("JDBC route")
                .toD("http://api.weatherapi.com/v1/current.json?" +
                        "key=813eff2b8a174601b28101610240407&q=Moscow&aqi=no?" +
                        "httpMethod=GET")
//                .to("jolt:file://src/main/java/cs/vsu/crypto_weather/weather/camel/jolt1.json?" +
//                        "inputType=JsonString&outputType=JsonString")
                .process(transformationProcessor)
                .log(body().toString())
                .process(loadProcessor)
                .log(body().toString());

    }
}
