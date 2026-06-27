package ru.aston.task3;


public class CachedInternetWeatherProvider implements WeatherProvider {
	private String weather;

	@Override
	public String getWeather() {
		if (weather == null) {
			InternetWeatherProvider provider = new InternetWeatherProvider();
			weather = provider.getWeather();
		}
		return weather;
	}

}
