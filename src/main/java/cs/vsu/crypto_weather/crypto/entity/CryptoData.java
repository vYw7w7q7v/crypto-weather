package cs.vsu.crypto_weather.crypto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity(name = "crypto_data")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@ToString
@Builder
public class CryptoData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String symbol;

    private Double price;

    private Date time;

    public CryptoData(String symbol, Double price, Date time) {
        this.symbol = symbol;
        this.price = price;
        this.time = time;
    }

}
