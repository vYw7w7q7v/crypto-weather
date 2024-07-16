package cs.vsu.crypto_weather.crypto.service;

import cs.vsu.crypto_weather.crypto.entity.CryptoManual;
import cs.vsu.crypto_weather.crypto.repository.CryptoManualRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptoManualService {
    private final CryptoManualRepository cryptoManualRepository;

    public void save(CryptoManual cryptoManual) {
        if (!cryptoManualRepository.existsBySymbolIgnoreCase(cryptoManual.getSymbol())) {
            cryptoManualRepository.save(cryptoManual);
        }
    }

    @Transactional
    public void deleteBySymbolIgnoreCase(String symbol) {
        cryptoManualRepository.deleteBySymbolIgnoreCase(symbol);
    }

    public List<CryptoManual> findBySymbol(String cryptoSymbol) {
        return cryptoManualRepository.findBySymbol(cryptoSymbol);
    }

    public List<CryptoManual> findAll() {
        return cryptoManualRepository.findAll();
    }

    public List<CryptoManual> findByCityName(String city) {
        return cryptoManualRepository.findByCityName(city);
    }
}
