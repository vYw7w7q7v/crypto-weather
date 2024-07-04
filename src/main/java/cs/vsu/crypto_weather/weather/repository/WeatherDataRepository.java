package cs.vsu.crypto_weather.weather.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WeatherDataRepository extends JpaRepository<WeatherData, UUID> {
    List<WeatherData> findAll(Sort sort);
}
