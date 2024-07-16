package cs.vsu.crypto_weather.camel;

import cs.vsu.crypto_weather.crypto.dto.CryptoWeatherDataDTO;
import cs.vsu.crypto_weather.crypto.service.CryptoManualService;
import cs.vsu.crypto_weather.crypto.service.CryptoWeatherDataService;
import cs.vsu.crypto_weather.weather.entity.WeatherData;
import cs.vsu.crypto_weather.kafka.KafkaSender;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendWeatherDataToKafkaProcessor implements Processor {
    private final CryptoManualService cryptoManualService;
    private final CryptoWeatherDataService cryptoWeatherDataService;
    private final KafkaSender kafkaSender;

    @Override
    public void process(Exchange exchange) throws Exception {
        var weatherData = exchange.getIn().getBody(WeatherData.class);
        var cryptoManual = cryptoManualService.findByCityName(weatherData.getCity());

        if (cryptoManual.isEmpty()) return;

        var cryptoWeatherDataDTO = cryptoWeatherDataService.getCurrentCryptoWeather(
                cryptoManual.getFirst().getSymbol()
        );
        exchange.getIn().setBody(cryptoWeatherDataDTO, CryptoWeatherDataDTO.class);
        kafkaSender.sendCryptoWeatherData(cryptoWeatherDataDTO);
    }
}
