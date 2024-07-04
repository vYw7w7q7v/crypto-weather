package cs.vsu.crypto_weather.weather.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class WeatherApiRoute extends RouteBuilder {

    public void dbConfig() {

    }

    private CamelContext camel = new DefaultCamelContext();

    private DataSource dataSource = new DriverManagerDataSource(
      "jdbc:mysql://localhost:3306/crypto_weather_db"
    );

    @Override
    public void configure() throws Exception {

        camel.getRegistry().bind("weather_data", dataSource);

        from("timer:mytimer?period=10000")
                .toD("http://api.weatherapi.com/v1/current.json?" +
                        "key=813eff2b8a174601b28101610240407&q=Moscow&aqi=no?" +
                        "httpMethod=GET")
                .to("jolt:file://src/main/java/cs/vsu/crypto_weather/weather/camel/jolt1.json?" +
                        "inputType=JsonString&outputType=JsonString")
                .routeId("JDBC route")
                .log(body().toString())
                .setBody(simple("\"insert into weather_data values('${body[city]}'," +
                        "'${body[temp]," +
                        "'${body[time]}'}')\""))
//                .to("jdbc:weather_data")
                .log(body().toString());

    }
}
