package ru.aston.task3;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class CarSorter {
	private List<SortingType> sortingChain;

	private CarSorter() {
	}

	static void sort(@NotNull List<Car> cars, @NotNull final List<SortingType> sortingChain) {
		if (cars.isEmpty() || sortingChain.isEmpty()) {
			throw new IllegalArgumentException("cars and sortingChain MUST not be empty");
		}
		for (SortingType sortingType : sortingChain) {
			sortingType.doSort(cars);
		}
	}

	static public CarSorter sorting(@NotNull SortingType sortingType) {
		CarSorter sorter = new CarSorter();
		sorter.sortingChain = new LinkedList<>();
		sorter.sortingChain.add(sortingType);

		return sorter;
	}

	public CarSorter thenSorting(@NotNull SortingType sortingType) {
		if (sortingChain == null) {
			throw new IllegalStateException("Unable to continue sorting. Initialize the sorting chain first!");
		}

		sortingChain.add(sortingType);
		return this;
	}

	public void sort(List<Car> cars) {
		if (sortingChain == null) {
			throw new IllegalStateException("Unable to continue sorting. Initialize the sorting chain first!");
		}

		try {
			for (SortingType sortingType : sortingChain) {
				sortingType.doSort(cars);
			}
		}
		finally {
			sortingChain = null;
		}
	}

	enum SortingType {
		ASC {
			@Override
			void doSort(@NotNull List<Car> cars) {
				cars.sort(Comparator.naturalOrder());
			}
		},
		DESC {
			@Override
			void doSort(@NotNull List<Car> cars) {
				cars.sort(Comparator.reverseOrder());
			}
		},
		EVEN_YEAR_NATURAL_ODD_AS_IS {
			@Override
			void doSort(@NotNull List<Car> cars) {
				List<Car> evenYearCars = new ArrayList<>(cars.size());
				for (Car car : cars) {
					if (car.getYear() % 2 == 0) {
						evenYearCars.add(car);
					}
				}

				evenYearCars.sort(Comparator.naturalOrder());

				int evenIndex = 0;
				for (int i = 0; i < cars.size(); ++i) {
					Car car = cars.get(i);
					if (car.getYear() % 2 == 0) {
						cars.set(i, evenYearCars.get(evenIndex));
						++evenIndex;
					}
				}
			}
		};

		abstract void doSort(@NotNull List<Car> cars);
	}
}
