package cs.vsu.crypto_weather.crypto.controller;

import cs.vsu.crypto_weather.crypto.entity.CryptoManual;
import cs.vsu.crypto_weather.crypto.service.CryptoDataService;
import cs.vsu.crypto_weather.crypto.service.CryptoManualService;
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

    @GetMapping("/add")
    public void add(@RequestParam String symbol) {
        cryptoManualService.save(CryptoManual.builder().symbol(symbol).build());
    }
    @PostMapping()
    public String delete(@RequestParam String symbol, Model model) {
        cryptoManualService.deleteBySymbolIgnoreCase(symbol);
        cryptoDataService.deleteAllBySymbolIgnoreCase(symbol);
        return "redirect:/crypto/all";
    }
}
