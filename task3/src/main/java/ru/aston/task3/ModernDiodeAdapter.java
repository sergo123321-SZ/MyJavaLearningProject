package ru.aston.task3;


import java.util.ArrayList;
import java.util.List;


public class ModernDiodeAdapter implements DiodeBrightnessController {
	private final ModernDiodeBrightnessController impl;

	public ModernDiodeAdapter(ModernDiodeBrightnessController impl) {
		this.impl = impl;
	}


	@Override
	public void setPwm(List<Integer> pwm) {
		if (pwm == null || pwm.isEmpty()) {
			impl.setBrightness(0);
			return;
		}

		long activeTicks = pwm.stream().filter(x -> x == 1).count();
		double dutyCycle = (double) activeTicks / pwm.size();
		double correctedBrightness = Math.pow(dutyCycle, 2);
		int finalBrightness = (int) Math.round(correctedBrightness * 100);

		impl.setBrightness(finalBrightness);
	}

	@Override
	public List<Integer> getPwm() {
		double currentBrightness = impl.getBrightness();
		double relativeBrightness = currentBrightness / 100.0;
		double dutyCycle = Math.sqrt(relativeBrightness);
		int activeTicks = (int) Math.round(dutyCycle * 5);

		List<Integer> pwmList = new ArrayList<>(5);

		for (int i = 0; i < 5; i++) {
			if (i < activeTicks) {
				pwmList.add(1);
			} else {
				pwmList.add(0);
			}
		}

		return pwmList;
	}
}
