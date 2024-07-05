package cs.vsu.crypto_weather.weather.service;

import cs.vsu.crypto_weather.weather.camel.WeatherApiRoute;
import cs.vsu.crypto_weather.weather.entity.WeatherData;
import cs.vsu.crypto_weather.weather.repository.WeatherDataRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
@RequiredArgsConstructor
public class WeatherDataService {
    private final WeatherDataRepository weatherDataRepository;

    public void save(WeatherData weatherData) {
        weatherDataRepository.save(weatherData);
    }

}
