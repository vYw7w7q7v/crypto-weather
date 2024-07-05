package cs.vsu.crypto_weather.weather.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "weather_data")
@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
@Builder
@ToString
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36)
    private String id;

    private String city;

    private Double temp;

    private Date time;



}
