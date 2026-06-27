package ru.aston.task3;


public class ModernDiodeBrightnessController {
	private double electricPower;

	public double getBrightness() {
		return electricPower / 33.;
	}

	public void setBrightness(double brightness) {
		electricPower = brightness * 33;
	}
}
