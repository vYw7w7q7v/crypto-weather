package cs.vsu.crypto_weather.crypto.controller;

import cs.vsu.crypto_weather.crypto.entity.CryptoManual;
import cs.vsu.crypto_weather.crypto.service.CryptoDataService;
import cs.vsu.crypto_weather.crypto.service.CryptoManualService;
import cs.vsu.crypto_weather.crypto.service.CryptoWeatherDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("crypto_manual")
@RequiredArgsConstructor
public class CryptoManualController {
    private final CryptoManualService cryptoManualService;
    private final CryptoDataService cryptoDataService;
    private final CryptoWeatherDataService cryptoWeatherDataService;

    @GetMapping("/test")
    public void test() {
        var k = 1;
    }

    @GetMapping("/add")
    public void add(@RequestParam String symbol,
                    @RequestParam String cityName,
                    @RequestParam String buyBorder,
                    @RequestParam String sellBorder) {
        var cryptoManual = CryptoManual.builder()
                .symbol(symbol)
                .cityName(cityName)
                .buyBorder(Double.parseDouble(buyBorder))
                .sellBorder(Double.parseDouble(sellBorder))
                .build();
        cryptoManualService.save(cryptoManual);
    }
    @PostMapping()
    public String delete(@RequestParam String symbol, Model model) {
        cryptoManualService.deleteBySymbolIgnoreCase(symbol);
        cryptoDataService.deleteAllBySymbolIgnoreCase(symbol);
        return "redirect:/crypto/all";
    }


}
