package ru.aston.task3;


public class LogMarkerDecorator implements Logger {
	private final Logger logger;

	public LogMarkerDecorator(final Logger logger) {
		this.logger = logger;
	}

	@Override
	public void log(final LogLevel level, final String message) {
		String marker = switch (level) {
			case ERROR -> "[ERR]";
			case WARNING -> "[WARN]";
			case INFO -> "[INF]";
		} + " ";

		logger.log(level, marker + message);
	}
}
