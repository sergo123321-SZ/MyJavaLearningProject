package ru.aston.task3;


public class InternetWeatherProvider implements WeatherProvider {

	@Override
	public String getWeather() {
		String body = "The weather in the city: 28ºC";
		/*
			HttpRequest request = new HttpRequest("weather.ru");
			HttpResponse response = httpRequest->get();
			// 5 sec elapsed
			body = response.getBobyOrThrow();
		 */
		return body;
	}
}
