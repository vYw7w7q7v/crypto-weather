package cs.vsu.crypto_weather.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class WeatherConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
      return new ObjectMapper();
    }
}
