package ru.aston.task2;


import java.util.Comparator;
import java.util.List;


public class Main {
	public static void main(String[] args) {
		new StudentJsonReader()
				.readStudentsFromFile("Data/students.json")
				.stream()
				.peek(student -> System.out.printf("Student: %s%n", student))
				.map(Student::getBooks)
				.flatMap(List::stream)
				.sorted(Comparator.comparingInt(Book::getPages).thenComparing(Comparator.naturalOrder()))
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