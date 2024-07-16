package cs.vsu.crypto_weather.camel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import cs.vsu.crypto_weather.weather.entity.WeatherData;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class WeatherDataTransformationProcessor implements Processor {
    private final ObjectMapper objectMapper;

    @Override
    public void process(Exchange exchange) throws Exception {
        var node = objectMapper.readTree(exchange.getIn().getBody(String.class));

        var format = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        var weatherData = WeatherData.builder()
                .city(node.findValue("location").findValue("name").asText())
                .temp(Double.parseDouble(node.findValue("current").findValue("temp_c").asText()))
                .time(format.parse(node.findValue("current").findValue("last_updated").asText()))
                .build();

        exchange.getIn().setBody(weatherData, WeatherData.class);
    }
}
