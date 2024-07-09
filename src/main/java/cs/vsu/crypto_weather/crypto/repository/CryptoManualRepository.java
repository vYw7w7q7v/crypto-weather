package cs.vsu.crypto_weather.crypto.repository;

import cs.vsu.crypto_weather.crypto.entity.CryptoManual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CryptoManualRepository extends JpaRepository<CryptoManual, UUID> {
    boolean existsBySymbolIgnoreCase(String symbol);
    void deleteBySymbolIgnoreCase(String symbol);

}
