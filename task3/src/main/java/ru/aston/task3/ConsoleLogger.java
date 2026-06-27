package ru.aston.task3;


public class ConsoleLogger implements Logger {

	@Override
	public void log(final LogLevel level, final String message) {
		System.out.println(message);
	}
}
