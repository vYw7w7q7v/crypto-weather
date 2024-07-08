package cs.vsu.crypto_weather.crypto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Entity(name = "crypto_manual")
@AllArgsConstructor
@Getter
@Builder
public class CryptoManual {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36)
    private String id;

    private String symbol;

}
