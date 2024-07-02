package cs.vsu.crypto_weather.crypto.controller;

import cs.vsu.crypto_weather.crypto.entity.CryptoData;
import cs.vsu.crypto_weather.crypto.service.CryptoDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/crypto")
@RequiredArgsConstructor
public class CryptoController {
    @Autowired
    private CryptoDataService cryptoDataService;


    @GetMapping
    public String getCrypto(@RequestParam String symbol, Model crypto) {
        List<CryptoData> cryptoData = cryptoDataService.findBySymbol(symbol);
        crypto.addAttribute("cryptoData", cryptoData);
        return "crypto";
    }

}