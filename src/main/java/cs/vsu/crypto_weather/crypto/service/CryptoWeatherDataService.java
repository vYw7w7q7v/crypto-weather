package cs.vsu.crypto_weather.crypto.service;

import cs.vsu.crypto_weather.crypto.dto.CryptoWeatherDataDTO;
import cs.vsu.crypto_weather.crypto.entity.CryptoManual;
import cs.vsu.crypto_weather.crypto.entity.CryptoWeatherState;
import cs.vsu.crypto_weather.weather.service.WeatherDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CryptoWeatherDataService {
    private final CryptoDataService cryptoDataService;
    private final CryptoManualService cryptoManualService;
    private final WeatherDataService weatherDataService;

    public CryptoWeatherDataDTO getCurrentCryptoWeather(String cryptoSymbol) {

        var cryptoManual = cryptoManualService.findBySymbol(cryptoSymbol).get(0);
        var weatherData = weatherDataService.findFirstByCityOrderByTimeDesc(
                cryptoManual.getCityName()
        );

        var cryptoWeatherState = getCurrentCryptoWeatherStateByTemp(weatherData.getTemp(),
                cryptoManual.getBuyBorder(), cryptoManual.getSellBorder());

        var cryptoWeatherDataDTOBuilder =  CryptoWeatherDataDTO.builder()
                .symbol(cryptoManual.getSymbol())
                .city(weatherData.getCity())
                .temp(weatherData.getTemp())
                .time(weatherData.getTime())
                .cryptoWeatherState(cryptoWeatherState);

        return cryptoWeatherDataDTOBuilder.build();
    }

    private CryptoWeatherState getCurrentCryptoWeatherStateByTemp(Double temp, Double buyBorder, Double sellBorder) {
        if (temp < buyBorder) {
            return CryptoWeatherState.CONTINUE_BUYING;
        } else if (temp > sellBorder) {
            return CryptoWeatherState.SELL;
        } else {
            return CryptoWeatherState.BUY;
        }
    }

}
