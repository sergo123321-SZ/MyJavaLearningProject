package ru.aston.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class BookTest {

	@Test
	@DisplayName("Check equality of 2 identical books")
	void equals_ShouldReturnTrue_WhenBooksAreIdentical() {
		Book book1 = new Book("Преступление и наказание", "Достоевский Ф.М.", 672, 1866);
		Book book2 = new Book("Преступление и наказание", "Достоевский Ф.М.", 672, 1866);

		assertThat(book1).isEqualTo(book2);
	}

	@Test
	@DisplayName("Check equality of 2 identical books via comparator")
	void compareTo_ShouldReturnZero_WhenBooksAreIdentical() {
		Book book1 = new Book("Преступление и наказание", "Достоевский Ф.М.", 672, 1866);
		Book book2 = new Book("Преступление и наказание", "Достоевский Ф.М.", 672, 1866);

		assertThat(book1.compareTo(book2)).isZero();
	}


}