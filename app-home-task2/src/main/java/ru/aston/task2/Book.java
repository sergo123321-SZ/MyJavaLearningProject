package ru.aston.task2;


import java.util.Comparator;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;


public final class Book implements Comparable<Book> {
	private final String title;
	private final String author;
	private final int pages;
	private final int year;

	public Book(@NotNull final String title, @NotNull final String author, final int pages, final int year) {
		this.title = Objects.requireNonNull(title, "title must not be null");
		this.author = Objects.requireNonNull(author, "author must not be null");
		this.pages = pages;
		this.year = year;
	}

	public String getAuthor() {
		return author;
	}

	public int getPages() {
		return pages;
	}

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (!(other instanceof Book otherBook)) {
			return false;
		}

		return Objects.equals(title, otherBook.title)
				&& Objects.equals(author, otherBook.author)
				&& pages == otherBook.pages
				&& year == otherBook.year;
	}

	@Override
	public int compareTo(@NotNull Book other) {
		return Comparator
				.comparing(Book::getTitle, Comparator.nullsLast(String::compareTo))
				.thenComparing(Book::getAuthor, Comparator.nullsLast(String::compareTo))
				.thenComparingInt(Book::getPages)
				.thenComparingInt(Book::getYear)
				.compare(this, other);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, author, pages, year);
	}

	@Override
	public String toString() {
		return "Book[" +
				"title='" + title + '\'' +
				", author='" + author + '\'' +
				", pages=" + pages +
				", year=" + year +
				']';
	}
}
