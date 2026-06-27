package ru.aston.task3;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WeatherProviderProxyTest {
	@Test
	@DisplayName("Weather test")
	public void whenRequestWeather_thenActualResultExpected() {
		WeatherProvider weatherProvider = new InternetWeatherProvider();
		String expected = "The weather in the city: 28ºC";
		String actual = weatherProvider.getWeather();

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	@DisplayName("Weather Proxy test")
	public void whenRequestWeatherViaProxy_thenSameActualResultExpected() {
		WeatherProvider weatherProvider = new CachedInternetWeatherProvider();
		String expected = "The weather in the city: 28ºC";
		String actual = weatherProvider.getWeather();

		assertThat(actual).isEqualTo(expected);
	}
}
