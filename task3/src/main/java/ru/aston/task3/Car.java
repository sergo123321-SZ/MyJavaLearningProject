package ru.aston.task3;


import lombok.Getter;
import lombok.ToString;
import org.apache.commons.math3.util.Precision;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Objects;


@ToString
public class Car implements Comparable<Car> {
	@Getter
	private final String model;
	@Getter
	private final int year;
	@Getter
	private final double meter;


	private Car(@NotNull final Builder builder) {
		this(builder.model, builder.year, builder.meter);
	}

	public Car(@NotNull final String model, final int year, final double meter) {
		if (year < 0) {
			throw new IllegalArgumentException("Year MUST be > 0");
		}
		if (meter < 0) {
			throw new IllegalArgumentException("Meter MUST be > 0");
		}

		this.model = Objects.requireNonNull(model, "Model MUST be provided");
		this.year = year;
		this.meter = meter;
	}

	@Override
	public boolean equals(Object otherObject) {
		if (otherObject == this) {
			return true;
		}

		if (!(otherObject instanceof Car other)) {
			return false;
		}

		return Objects.equals(model, other.model)
				&& year == other.year
				&& Precision.equals(meter, other.meter);
	}

	@Override
	public int compareTo(@NotNull Car o) {
		return Comparator.comparing(Car::getModel)
				.thenComparing(Car::getYear)
				.thenComparing(Car::getMeter)
				.compare(this, o);
	}

	public static class Builder {
		private String model;
		private int year = -1;
		private double meter = -1.;

		public Builder setModel(@NotNull final String model) {
			this.model = Objects.requireNonNull(model, "Model MUST be provided");
			return this;
		}

		public Builder setYear(final int year) {
			this.year = year;
			return this;
		}

		public Builder setMeter(final double meter) {
			this.meter = meter;
			return this;
		}

		public Car build() {
			return new Car(this);
		}
	}
}
