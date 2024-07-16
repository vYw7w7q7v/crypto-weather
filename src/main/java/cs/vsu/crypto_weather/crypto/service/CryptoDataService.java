package cs.vsu.crypto_weather.crypto.service;

import cs.vsu.crypto_weather.crypto.entity.CryptoData;
import cs.vsu.crypto_weather.crypto.repository.CryptoDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptoDataService {

    private final CryptoDataRepository cryptoDataRepository;

    public CryptoData findFirstBySymbol(String symbol) {
        return cryptoDataRepository.findFirstBySymbol(symbol);
    }

    public List<CryptoData> findAllSortedByTime() {
        return cryptoDataRepository.findAll(Sort.by(Sort.Direction.ASC, "time"));
    };

    @Transactional
    public void deleteAllBySymbolIgnoreCase(String symbol) {
        cryptoDataRepository.deleteAllBySymbolIgnoreCase(symbol);
    }

    public List<CryptoData> findBySymbol(String symbol) {
        return cryptoDataRepository.findBySymbol(symbol);
    }
}
