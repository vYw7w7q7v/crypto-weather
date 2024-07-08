package cs.vsu.crypto_weather.crypto.controller;

import cs.vsu.crypto_weather.crypto.entity.CryptoManual;
import cs.vsu.crypto_weather.crypto.service.CryptoManualService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("crypto_manual")
@RequiredArgsConstructor
public class CryptoManualController {
    private final CryptoManualService cryptoManualService;

    @GetMapping("/add")
    public void add(@RequestParam String symbol) {
        cryptoManualService.save(CryptoManual.builder().symbol(symbol).build());
    }
}
