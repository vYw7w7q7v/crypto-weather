package cs.vsu.crypto_weather;

import cs.vsu.crypto_weather.crypto.entity.CryptoData;
import cs.vsu.crypto_weather.crypto.service.CryptoDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class CryptoWeatherApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private CryptoDataService cryptoDataService;
	@Test
	void test1() {
		CryptoData cryptoData = CryptoData.builder()
				.symbol("BTC")
				.price(1000d)
				.time(new Date(""))
				.build();
		cryptoDataService.save(cryptoData);
		System.out.println(cryptoDataService.findBySymbol("BTC"));
	}

}
