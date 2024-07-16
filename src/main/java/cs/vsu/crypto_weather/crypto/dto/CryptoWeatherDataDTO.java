package cs.vsu.crypto_weather.crypto.dto;

import cs.vsu.crypto_weather.crypto.entity.CryptoWeatherState;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CryptoWeatherDataDTO {
    private String symbol;
    private String city;
    private Double temp;
    private Date time;
    private CryptoWeatherState cryptoWeatherState;
}
