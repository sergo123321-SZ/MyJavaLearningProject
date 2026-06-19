package ru.aston.task2;


import java.util.Comparator;
import java.util.List;


public class Main {
	private static final Comparator<Book> COMPARE_BY_PAGES_THEN_NATURAL = Comparator
			.comparingInt(Book::getPages)
			.thenComparing(Comparator.naturalOrder());

	public static void main(String[] args) {
		StudentJsonReader reader = new StudentJsonReader();
		List<Student> students = reader.readStudentsFromFile("Data/students.json");
		students.stream()
				.peek(student -> System.out.printf("Student: %s%n", student))
				.map(Student::getBooks)
				.flatMap(List::stream)
				.sorted(Main.COMPARE_BY_PAGES_THEN_NATURAL)
				.distinct()
				.filter(book -> book.getYear() > 2000)
				.limit(3)
				.map(Book::getYear)
				.findFirst()
				.ifPresentOrElse(
						year -> System.out.printf("Year of publication of the found book: %d%n", year),
						() -> System.out.printf("Unable to find any book released after 2000%n")
				);
	}
}