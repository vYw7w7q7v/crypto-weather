package cs.vsu.crypto_weather.crypto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "crypto_data")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@ToString
@Builder
public class CryptoData {
    @Id
    @Column(length = 36)
    private String id;

    private String name;

    private String symbol;

    private Double price;

    private Date time;

}
