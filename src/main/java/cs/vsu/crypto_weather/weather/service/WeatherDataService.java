package cs.vsu.crypto_weather.weather.service;

import cs.vsu.crypto_weather.weather.entity.WeatherData;
import cs.vsu.crypto_weather.weather.repository.WeatherDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherDataService {
    private final WeatherDataRepository weatherDataRepository;

    public void save(WeatherData weatherData) {
        weatherDataRepository.save(weatherData);
    }
    public List<WeatherData> findAll() {
        return weatherDataRepository.findAll();
    }
    public WeatherData findFirstOrderByTimeDesc() {
        return weatherDataRepository.findFirstByOrderByTimeDesc();
        //StringSerializer
    }

    public WeatherData findFirstByCityOrderByTimeDesc(String cityName) {
        return weatherDataRepository.findFirstByCityOrderByTimeDesc(cityName);
    }
}
