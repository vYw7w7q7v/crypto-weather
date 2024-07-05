package cs.vsu.crypto_weather.weather.camel;

import cs.vsu.crypto_weather.weather.entity.WeatherData;
import cs.vsu.crypto_weather.weather.service.WeatherDataService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherDataLoadProcessor implements Processor {

    @Autowired
    WeatherDataService weatherDataService;
    @Override
    public void process(Exchange exchange) throws Exception {
        WeatherData weatherData = exchange.getIn().getBody(WeatherData.class);
        weatherDataService.save(weatherData);
        exchange.getIn().setBody(String.format("weather data %s loaded!", weatherData.toString()));
    }
}
