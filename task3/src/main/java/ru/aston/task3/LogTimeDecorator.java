package ru.aston.task3;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LogTimeDecorator implements Logger {
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private final Logger logger;

	public LogTimeDecorator(final Logger logger) {
		this.logger = logger;
	}

	@Override
	public void log(final LogLevel level, final String message) {
		logger.log(level, LocalDateTime.now().format(formatter) + " " + message);
	}
}
