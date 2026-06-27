package ru.aston.task3;


import java.util.List;


public class PrimitiveDiodeBrightnessController implements DiodeBrightnessController {
	List<Integer> pwm;

	@Override
	public void setPwm(List<Integer> pwm) {
		this.pwm = pwm;
	}

	@Override
	public List<Integer> getPwm() {
		return List.copyOf(pwm);
	}
}
