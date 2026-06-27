package ru.aston.task3;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class BrightnessControllerAdapterTest {

	List<Integer> expected;
	long expectedOnes;

	@BeforeEach
	void setUp() {
		expected = List.of(1, 1, 0, 1, 0);
		expectedOnes = expected.stream().filter(x -> x == 1).count();
	}


	@Test
	@DisplayName("Primitive test")
	public void whenPrimitiveUsed_thenCorrectOnesCountExpected() {
		DiodeBrightnessController controller = new PrimitiveDiodeBrightnessController();
		controller.setPwm(expected);
		List<Integer> pwm = controller.getPwm();

		long actualOnes = pwm.stream().filter(x -> x == 1).count();

		assertThat(expectedOnes).isEqualTo(actualOnes);
	}

	@Test
	@DisplayName("Adapter test")
	public void whenAdapterUsed_thenCorrectOnesCountExpected() {
		ModernDiodeBrightnessController modernController = new ModernDiodeBrightnessController();
		DiodeBrightnessController controller = new ModernDiodeAdapter(modernController);
		controller.setPwm(expected);
		List<Integer> pwm = controller.getPwm();

		long actualOnes = pwm.stream().filter(x -> x == 1).count();

		assertThat(expectedOnes).isEqualTo(actualOnes);
	}
}
