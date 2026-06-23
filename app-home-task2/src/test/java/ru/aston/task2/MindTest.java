package ru.aston.task2;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class MindTest {
	private List<Student> students;

	@BeforeEach
	void setUp() {
		URL resourceUrl = getClass().getClassLoader().getResource("students.json");
		assertThat(resourceUrl)
				.as("The testing resource data file")
				.isNotNull();

		StudentJsonReader reader = new StudentJsonReader();
		students = reader.readStudentsFromFile(resourceUrl.getPath());
		assertThat(students)
				.as("The read collection from the file")
				.isNotEmpty();
	}

	@Test
	@DisplayName("Find the student with the oldest book")
	void whenSearchingOldestBookUsingNestedStream_thenCorrectStudentAndBookReturned() {
		Book expectedBook = new Book("Чувство и чувствительность", "Джейн Остин", 448, 1811);

		Comparator<Student> byOldestBook = Comparator.comparingInt(
				s -> s.getBooks().stream()
						.mapToInt(Book::getYear)
						.min()
						.orElse(Integer.MAX_VALUE)
		);

		Student foundStudent = students.stream()
				.filter(s -> !s.getBooks().isEmpty())
				.min(byOldestBook)
				.orElseThrow();

		Book oldestBook = foundStudent.getBooks().stream()
				.min(Comparator.comparingInt(Book::getYear))
				.orElseThrow();

		assertThat(foundStudent)
				.extracting(Student::getStudentId, Student::getName)
				.containsExactly("3c98fbc1-90a6-4447-9753-277ba13fe052", "Мария Козлова");

		assertThat(oldestBook).isEqualTo(expectedBook);
	}

	@Test
	@DisplayName("Find the student with the oldest book with record")
	void whenSearchingOldestBookUsingLocalRecord_thenCorrectPairReturned() {
		Book expectedBook = new Book("Чувство и чувствительность", "Джейн Остин", 448, 1811);
		record StudentBookPair(Student student, Book book) {}

		StudentBookPair result = students.stream()
				.flatMap(s -> s.getBooks().stream()
						.map(b -> new StudentBookPair(s, b)))
				.min(Comparator.comparingInt(p -> p.book().getYear()))
				.orElseThrow();

		assertThat(result.student())
				.extracting(Student::getStudentId, Student::getName)
				.containsExactly("3c98fbc1-90a6-4447-9753-277ba13fe052", "Мария Козлова");

		assertThat(result.book()).isEqualTo(expectedBook);
	}

	@Test
	@DisplayName("Find the student with the largest book collection")
	void whenSearchingStudentWithLargestCollection_thenCorrectStudentWithEightBooksReturned() {
		Comparator<Student> byBookCollectionSize = Comparator.comparingInt(student -> student.getBooks().size());

		Student foundStudent =
				students.stream()
						.max(byBookCollectionSize)
						.orElseThrow();

		assertThat(foundStudent)
				.extracting(Student::getStudentId, Student::getName, s -> s.getBooks().size())
				.containsExactly("67b93605-7f41-4c6e-8263-d14f6b0f023e", "Наталья Соколова", 8);
	}

	@Test
	@DisplayName("Find the book with the longest title")
	void whenSearchingBookWithLongestTitle_thenHarryPotterBookReturned() {
		Comparator<Book> byTitleLength = Comparator.comparingInt(book -> book.getTitle().length());
		Book expectedBook = new Book("Гарри Поттер и философский камень", "Джоан Роулинг", 352, 1997);

		Book foundBook =
				students.stream()
						.flatMap(student -> student.getBooks().stream())
						.max(byTitleLength)
						.orElseThrow();

		assertThat(foundBook).isEqualTo(expectedBook);
	}
}
