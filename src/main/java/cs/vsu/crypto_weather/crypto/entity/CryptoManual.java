package cs.vsu.crypto_weather.crypto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity(name = "crypto_manual")
public class CryptoManual {
    @Id
    @Column(length = 36)
    private String id;

    private String symbol;

}
