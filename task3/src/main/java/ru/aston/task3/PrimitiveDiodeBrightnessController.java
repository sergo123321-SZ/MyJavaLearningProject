package ru.aston.task3;


import java.util.List;


public class PrimitiveDiodeBrightnessController implements DiodeBrightnessController {
	private List<Integer> pwm;

	@Override
	public void setPwm(final List<Integer> pwm) {
		this.pwm = List.copyOf(pwm);
	}

	@Override
	public List<Integer> getPwm() {
		if (pwm == null) {
			return List.of();
		}

		return List.copyOf(pwm);
	}
}
