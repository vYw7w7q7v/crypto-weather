package cs.vsu.crypto_weather;

import cs.vsu.crypto_weather.crypto.service.CryptoDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CryptoWeatherApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private CryptoDataService cryptoDataService;
	@Test
	void test1() {
//		CryptoData cryptoData = CryptoData.builder()
//				.symbol("BTC")
//				.price(1000d)
//				.time(new Date(1000))
//				.build();
//		cryptoDataService.save(cryptoData);
//		System.out.println(cryptoDataService.findBySymbol("BTC"));
	}

	@Test
	void test2() {
		System.out.println(cryptoDataService.findAllOrderedByTime());
	}

}
