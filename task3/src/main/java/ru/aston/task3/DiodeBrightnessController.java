package ru.aston.task3;


import java.util.List;


public interface DiodeBrightnessController {
	void setPwm(List<Integer> pwm);
	List<Integer> getPwm();
}
