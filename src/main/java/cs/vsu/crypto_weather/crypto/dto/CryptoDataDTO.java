package cs.vsu.crypto_weather.crypto.dto;


import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CryptoDataDTO {
    private String id;
    private String name;
    private String symbol;
    private Double price;
    private Date time;
}
