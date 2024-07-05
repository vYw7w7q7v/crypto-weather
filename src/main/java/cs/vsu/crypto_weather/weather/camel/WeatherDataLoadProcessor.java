package cs.vsu.crypto_weather.weather.camel;

import cs.vsu.crypto_weather.weather.entity.WeatherData;
import cs.vsu.crypto_weather.weather.service.WeatherDataService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherDataLoadProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

    }
}
