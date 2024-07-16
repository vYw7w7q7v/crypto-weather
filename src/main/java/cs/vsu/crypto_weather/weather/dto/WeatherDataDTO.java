package cs.vsu.crypto_weather.weather.dto;

import lombok.*;

import java.util.Date;

@Data@Builder
public class WeatherDataDTO {
    private String id;
    private String city;
    private Double temp;
    private Date time;
}
