package cs.vsu.crypto_weather.crypto.entity;

public enum CryptoWeatherState {

    BUY("buy"),
    CONTINUE_BUYING("continue_buying"),
    SELL("sell");

    private final String name;

    CryptoWeatherState(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
