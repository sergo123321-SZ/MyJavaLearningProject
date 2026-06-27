package ru.aston.task3;


public interface Logger {
	void log(LogLevel level, String message);

	enum LogLevel {
		INFO,
		WARNING,
		ERROR
	}
}
