package cs.vsu.crypto_weather.crypto.repository;

import cs.vsu.crypto_weather.crypto.entity.CryptoData;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface CryptoDataRepository extends JpaRepository<CryptoData, UUID> {

    List<CryptoData> findBySymbol(String symbol);

    List<CryptoData> findAll(Sort sort);

    List<CryptoData> findBySymbolAndTimeBetween(String symbol, Date start, Date end);
}
