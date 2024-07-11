package cs.vsu.crypto_weather.weather.kafka;

import cs.vsu.crypto_weather.weather.entity.WeatherData;
import cs.vsu.crypto_weather.weather.service.WeatherDataService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@RequiredArgsConstructor
public class KafkaSender {
    private final KafkaTemplate<String, WeatherData> kafkaTemplate;
    private final KafkaProducer<String, WeatherData> kafkaProducer;

    private final WeatherDataService weatherDataService;
    public void sendMessage() {
//        Message<WeatherData> message = MessageBuilder
//                .withPayload()
        kafkaTemplate.send("weather", weatherDataService.findFirstByOrderByTimeDesc());
    }

    public void sendWeatherData(WeatherData weatherData) {
        Message<WeatherData> message = MessageBuilder
                .withPayload(weatherData)
                .setHeader(KafkaHeaders.TOPIC, "weather")
                .build();
        //kafkaTemplate.send(message);
        kafkaProducer.send(new ProducerRecord<>("weather", weatherData));
    }
}
