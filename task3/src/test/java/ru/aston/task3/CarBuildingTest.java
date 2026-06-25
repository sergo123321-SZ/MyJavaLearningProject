package ru.aston.task3;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CarBuildingTest {

	@Test
	@DisplayName("Compare cars with same fields built with constructor and builder")
	void whenComparingCarsWithSameFields_thenTrueReturned() {
		Car constructCar = new Car("Lada Kalina", 1999, 250000.7548392);
		double meter1 = 150000.7548;
		double meter2 = 100000.0000392;
		Car buildedCar = new Car.Builder().setModel("Lada Kalina").setYear(1999).setMeter(meter1 + meter2).build();

		assertThat(constructCar).isEqualTo(buildedCar);
	}

	@Test
	@DisplayName("Compare cars with same model, and year, but different meter built with constructor and builder")
	void whenComparingCarsWithDifferentMeterField_thenFalseReturned() {
		Car constructCar = new Car("Lada Kalina", 1999, 250000.7548392);
		double meter1 = 150000.754800001;
		double meter2 = 100000.0000392;
		Car buildedCar = new Car.Builder()
				.setModel("Lada Kalina")
				.setYear(1999)
				.setMeter(meter1 + meter2)
				.build();

		assertThat(constructCar).isNotEqualTo(buildedCar);
	}
}
