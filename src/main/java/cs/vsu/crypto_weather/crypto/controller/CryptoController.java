package cs.vsu.crypto_weather.crypto.controller;

import cs.vsu.crypto_weather.crypto.entity.CryptoData;
import cs.vsu.crypto_weather.crypto.entity.CryptoManual;
import cs.vsu.crypto_weather.crypto.service.CryptoDataService;
import cs.vsu.crypto_weather.crypto.service.CryptoManualService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/crypto")
@RequiredArgsConstructor
public class CryptoController {
    private final CryptoDataService cryptoDataService;


    @GetMapping
    public String getCrypto(@RequestParam String symbol, Model model) {
        List<CryptoData> cryptoData = cryptoDataService.findBySymbol(symbol);
        model.addAttribute("cryptoData", cryptoData);
        return "crypto.html";
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        List<CryptoData> cryptoDataList = cryptoDataService.findAllSortedByTime();
        model.addAttribute("cryptoData", cryptoDataList);
        return "crypto.html";
    }

}
