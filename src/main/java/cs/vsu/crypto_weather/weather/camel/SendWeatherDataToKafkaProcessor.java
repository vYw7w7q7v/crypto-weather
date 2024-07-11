package cs.vsu.crypto_weather.weather.camel;

import cs.vsu.crypto_weather.weather.entity.WeatherData;
import cs.vsu.crypto_weather.weather.kafka.KafkaSender;
import cs.vsu.crypto_weather.weather.service.WeatherDataService;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendWeatherDataToKafkaProcessor implements Processor {
    private final WeatherDataService weatherDataService;
    private final KafkaSender kafkaSender;

    @Override
    public void process(Exchange exchange) throws Exception {
        var weatherData = exchange.getIn().getBody(WeatherData.class);

        kafkaSender.sendWeatherData(weatherData);
    }
}
