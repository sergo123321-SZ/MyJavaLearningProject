package ru.aston.task2;


import java.io.File;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class StudentJsonReader {
	private final ObjectMapper objectMapper;

	public StudentJsonReader() {
		objectMapper = new ObjectMapper();
		objectMapper.addMixIn(Student.class, StudentMixIn.class);
		objectMapper.addMixIn(Book.class, BookMixIn.class);
	}

	public List<Student> readStudentsFromFile(final String filePath) {
		if (filePath == null || filePath.isEmpty()) {
			return List.of();
		}

		final File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			System.err.printf("Unable to find the file '%s'%n", filePath);
			return List.of();
		}

		try {
			List<Student> students = objectMapper.readValue(file, new TypeReference<>() {});

			return (students != null) ? students : List.of();
		}
		catch (Exception e) {
			System.err.printf("Error occurred during the file data parsing: '%s'%n", e.getMessage());

			return List.of();
		}
	}

	abstract static class StudentMixIn {
		@JsonCreator
		StudentMixIn(
				@JsonProperty("id") String studentId,
				@JsonProperty("name") String name,
				@JsonProperty("books") List<Book> books
		) {}
	}

	abstract static class BookMixIn {
		@JsonCreator
		BookMixIn(
				@JsonProperty("title") String title,
				@JsonProperty("author") String author,
				@JsonProperty("pages") int pages,
				@JsonProperty("year") int year
		) {}
	}
}
