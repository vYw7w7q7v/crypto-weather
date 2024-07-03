package cs.vsu.crypto_weather.crypto.service;

import cs.vsu.crypto_weather.crypto.entity.CryptoData;
import cs.vsu.crypto_weather.crypto.repository.CryptoDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptoDataService {

    private final CryptoDataRepository cryptoDataRepository;
    public void save(CryptoData cryptoData) {
        cryptoDataRepository.save(cryptoData);
    }

    public List<CryptoData> findBySymbol(String symbol) {
        return cryptoDataRepository.findBySymbol(symbol);
    }

    public List<CryptoData> findAllOrderedByTime() {
        return cryptoDataRepository.findAll(Sort.by(Sort.Direction.ASC, "time"));
    };
}
