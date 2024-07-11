package cs.vsu.crypto_weather.weather.controller;

import cs.vsu.crypto_weather.weather.kafka.KafkaSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaSender kafkaSender;

    @PostMapping("/kafka/send")
    public String send() {
        kafkaSender.sendMessage();
        return "Success";
    }
}
