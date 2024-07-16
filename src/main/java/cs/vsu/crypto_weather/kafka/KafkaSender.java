package cs.vsu.crypto_weather.kafka;

import cs.vsu.crypto_weather.crypto.dto.CryptoWeatherDataDTO;
import cs.vsu.crypto_weather.weather.entity.WeatherData;
import cs.vsu.crypto_weather.weather.service.WeatherDataService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaSender {
    private final KafkaProducer<String, CryptoWeatherDataDTO> kafkaProducer;

    private final WeatherDataService weatherDataService;

//    public void sendWeatherData(WeatherData weatherData) {
//        kafkaProducer.send(new ProducerRecord<>("weather", weatherData));
//    }

    public void sendCryptoWeatherData(CryptoWeatherDataDTO cryptoWeatherDataDTO) {
        kafkaProducer.send(new ProducerRecord<>("weather", cryptoWeatherDataDTO));
    }
}
