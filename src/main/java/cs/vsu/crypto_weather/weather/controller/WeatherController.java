package cs.vsu.crypto_weather.weather.controller;

import cs.vsu.crypto_weather.weather.entity.WeatherData;
import cs.vsu.crypto_weather.weather.service.WeatherDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherDataService weatherDataService;
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("weather" ,weatherDataService.findAll());
        return "weather.html";
    }
}
