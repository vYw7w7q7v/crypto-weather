package cs.vsu.crypto_weather.weather.controller;

import cs.vsu.crypto_weather.weather.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.apache.camel.ShutdownRunningTask;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    @PostMapping("/kafka/send")
    public String send(@RequestBody String message) {
        kafkaProducer.sendMessage(message);
        return "Success";
    }
}
