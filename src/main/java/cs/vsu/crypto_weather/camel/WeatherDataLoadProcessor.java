package cs.vsu.crypto_weather.camel;

import cs.vsu.crypto_weather.weather.entity.WeatherData;
import cs.vsu.crypto_weather.weather.service.WeatherDataService;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@RequiredArgsConstructor
public class WeatherDataLoadProcessor implements Processor {
    private final WeatherDataService weatherDataService;
    @Override
    public void process(Exchange exchange) throws RuntimeException {
        var weatherData = exchange.getIn().getBody(WeatherData.class);
        weatherDataService.save(weatherData);
        //exchange.getIn().setBody(MessageFormat.format("weather data loaded! {0}", weatherData));
    }
}
