package cs.vsu.crypto_weather.crypto.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class JdbcRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("http://api.weatherapi.com/v1/current.json?key=813eff2b8a174601b28101610240407&q=Moscow&aqi=no")
                .log(body().toString());

    }
}
