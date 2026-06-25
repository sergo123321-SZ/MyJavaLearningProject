package ru.aston.task3;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class SortStrategyTest {
	private List<Car> carsList;

	@BeforeEach
	void setUp() {
		carsList = new ArrayList<>(Arrays.asList(
				new Car("Toyota", 2022, 50000),
				new Car("BMW2", 2021, 10000),
				new Car("Audi", 2020, 15000),
				new Car("Lada", 2023, 90000),
				new Car("Mercedes2", 2022, 250000),
				new Car("BMW1", 2022, 5000),
				new Car("Mercedes1", 2001, 3000000)
		));
	}

	@Test
	@DisplayName("Simple ASC sort")
	public void whenAscSortingCars_thenExpectedSortedCarsExpected() {
		List<Car> expectedCars = new ArrayList<>(Arrays.asList(
				new Car("Audi", 2020, 15000),
				new Car("BMW1", 2022, 5000),
				new Car("BMW2", 2021, 10000),
				new Car("Lada", 2023, 90000),
				new Car("Mercedes1", 2001, 3000000),
				new Car("Mercedes2", 2022, 250000),
				new Car("Toyota", 2022, 50000)
		));

		CarSorter.sort(carsList, List.of(CarSorter.SortingType.ASC));

		assertThat(expectedCars).isEqualTo(carsList);
	}

	@Test
	@DisplayName("Simple DESC sort")
	public void whenDescSortingCars_thenExpectedSortedCarsExpected() {
		List<Car> expectedCars = new ArrayList<>(Arrays.asList(
				new Car("Toyota", 2022, 50000),
				new Car("Mercedes2", 2022, 250000),
				new Car("Mercedes1", 2001, 3000000),
				new Car("Lada", 2023, 90000),
				new Car("BMW2", 2021, 10000),
				new Car("BMW1", 2022, 5000),
				new Car("Audi", 2020, 15000)
		));

		CarSorter.sort(carsList, List.of(CarSorter.SortingType.DESC));

		assertThat(expectedCars).isEqualTo(carsList);
	}

	@Test
	@DisplayName("Simple Special sort")
	public void whenSpecialSortingCars_thenExpectedSortedCarsExpected() {
		List<Car> expectedCars = new ArrayList<>(Arrays.asList(
				new Car("Audi", 2020, 15000),
				new Car("BMW2", 2021, 10000),
				new Car("BMW1", 2022, 5000),
				new Car("Lada", 2023, 90000),
				new Car("Mercedes2", 2022, 250000),
				new Car("Toyota", 2022, 50000),
				new Car("Mercedes1", 2001, 3000000)
		));

		CarSorter.sort(carsList, List.of(CarSorter.SortingType.EVEN_YEAR_NATURAL_ODD_AS_IS));

		assertThat(expectedCars).isEqualTo(carsList);
	}

	@Test
	@DisplayName("Combo DESC + Special sort")
	public void whenDescAndSpecialSortingCars_thenExpectedSortedCarsExpected() {
		List<Car> expectedCars = List.of(
				new Car("Audi", 2020, 15000),
				new Car("BMW1", 2022, 5000),
				new Car("Mercedes1", 2001, 3000000),
				new Car("Lada", 2023, 90000),
				new Car("BMW2", 2021, 10000),
				new Car("Mercedes2", 2022, 250000),
				new Car("Toyota", 2022, 50000)
		);

		CarSorter.sorting(CarSorter.SortingType.DESC)
				.thenSorting(CarSorter.SortingType.EVEN_YEAR_NATURAL_ODD_AS_IS)
				.sort(carsList);

		assertThat(expectedCars).isEqualTo(carsList);
	}

	@Test
	@DisplayName("Combo Special + DESC sort")
	public void whenSpecialAndDescSortingCars_thenExpectedSortedCarsExpected() {
		List<Car> expectedCars = new ArrayList<>(Arrays.asList(
				new Car("Toyota", 2022, 50000),
				new Car("Mercedes2", 2022, 250000),
				new Car("Mercedes1", 2001, 3000000),
				new Car("Lada", 2023, 90000),
				new Car("BMW2", 2021, 10000),
				new Car("BMW1", 2022, 5000),
				new Car("Audi", 2020, 15000)
		));

		CarSorter.sort(
				carsList,
				List.of(CarSorter.SortingType.EVEN_YEAR_NATURAL_ODD_AS_IS, CarSorter.SortingType.DESC)
		);

		assertThat(expectedCars).isEqualTo(carsList);
	}
}
