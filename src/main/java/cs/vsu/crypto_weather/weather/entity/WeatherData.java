package cs.vsu.crypto_weather.weather.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity(name = "weather_data")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WeatherData {
    @Id
    @Column(length = 36)
    private String id;

    private String city;

    private Double temp;

    private Date time;

}
