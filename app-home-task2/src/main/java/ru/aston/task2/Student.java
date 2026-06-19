package ru.aston.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;


public class Student {
	private final String studentId;
	private final String name;
	private final List<Book> books;

	public Student(@NotNull final String studentId, @NotNull final String name, @NotNull final List<Book> books) {
		this.studentId = Objects.requireNonNull(studentId, "studentId must not be null");
		this.name = Objects.requireNonNull(name, "name must not be null");
		this.books = List.copyOf(books);
	}

	public Student withAddedBook(final Book book) {
		if (book == null) {
			return this;
		}

		List<Book> updatedBooks = new ArrayList<>(books.size() + 1);
		updatedBooks.addAll(books);
		updatedBooks.add(book);

		return new Student(studentId, name, updatedBooks);
	}

	public Student withAddedBooks(final Collection<Book> bookCollection) {
		if (bookCollection == null || bookCollection.isEmpty()) {
			return this;
		}

		List<Book> updatedBooks = new ArrayList<>(books.size() + bookCollection.size());
		updatedBooks.addAll(books);
		updatedBooks.addAll(bookCollection);

		return new Student(studentId, name, updatedBooks);
	}

	public Student withBooks(final Collection<Book> bookCollection) {
		return new Student(studentId, name, bookCollection == null ? List.of() : new ArrayList<>(bookCollection));
	}

	public String getStudentId() {
		return studentId;
	}

	public String getName() {
		return name;
	}

	public List<Book> getBooks() {
		return books;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (!(other instanceof Student otherStudent)) {
			return false;
		}

		return Objects.equals(studentId, otherStudent.studentId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentId);
	}

	@Override
	public String toString() {
		return "Student[" +
				"studentId='" + studentId + '\'' +
				", name='" + name + '\'' +
				", books=" + books +
				']';
	}
}
