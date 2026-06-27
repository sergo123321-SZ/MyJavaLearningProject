package ru.aston.task3;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class LoggerDecoratorTest {
	private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final String message = "Hello World!";
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@BeforeEach
	void setUp() {
		System.setOut(new PrintStream(outStream));
	}

	@AfterEach
	void cleanUp() {
		System.setOut(originalOut);
	}

	@Test
	@DisplayName("Test default logger")
	public void whenPrintMessageWithOriginalLogger_thenUnchangedExpected() {
		Logger logger = new ConsoleLogger();

		for (Logger.LogLevel logLevel : Logger.LogLevel.values()) {
			logger.log(logLevel, message);
			String actual = outStream.toString();
			outStream.reset();

			assertThat(actual).isEqualTo(message + "\n");
		}
	}

	@Test
	@DisplayName("Test time logger decorator")
	public void whenPrintMessageWithTimeDecoratedLogger_thenTimeWithMessageExpected() {
		Logger logger = new LogTimeDecorator(new ConsoleLogger());

		for (Logger.LogLevel logLevel : Logger.LogLevel.values()) {
			String currentDateTime = LocalDateTime.now().format(formatter);
			logger.log(logLevel, message);
			String actual = outStream.toString();
			outStream.reset();

			assertThat(actual).isEqualTo(currentDateTime + " " + message + "\n");
		}
	}

	@Test
	@DisplayName("Test level marker logger decorator")
	public void whenPrintMessageWithMarkerDecoratedLogger_thenMarkerWithMessageExpected() {
		Logger logger = new LogMarkerDecorator(new ConsoleLogger());

		for (Logger.LogLevel logLevel : Logger.LogLevel.values()) {
			String marker = switch (logLevel) {
				case ERROR -> "[ERR]";
				case WARNING -> "[WARN]";
				case INFO -> "[INF]";
			};
			logger.log(logLevel, message);
			String actual = outStream.toString();
			outStream.reset();

			assertThat(actual).isEqualTo(marker + " " + message + "\n");
		}
	}

	@Test
	@DisplayName("Test level marker + time logger decorator")
	public void whenPrintMessageWithMarkerAndTimeDecoratedLogger_thenMarkerWithTimeWithMessageExpected() {
		Logger logger = new LogTimeDecorator(new LogMarkerDecorator(new ConsoleLogger()));

		for (Logger.LogLevel logLevel : Logger.LogLevel.values()) {
			String currentDateTime = LocalDateTime.now().format(formatter);
			String marker = switch (logLevel) {
				case ERROR -> "[ERR]";
				case WARNING -> "[WARN]";
				case INFO -> "[INF]";
			};
			logger.log(logLevel, message);
			String actual = outStream.toString();
			outStream.reset();

			assertThat(actual).isEqualTo("%s %s %s%n".formatted(marker, currentDateTime, message));
		}
	}
}
