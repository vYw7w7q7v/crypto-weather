package cs.vsu.crypto_weather.crypto.service;

import cs.vsu.crypto_weather.crypto.entity.CryptoManual;
import cs.vsu.crypto_weather.crypto.repository.CryptoManualRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
