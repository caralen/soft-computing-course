package hr.fer.zemris.neuralFuzzy.task6;

import static java.lang.Math.cos;
import static java.lang.Math.pow;

public class Pattern {

	private double x, y, z;
	
	public Pattern(double x, double y) {
		this.x = x;
		this.y = y;
		this.z = (pow(x - 1, 2) + pow(y + 2, 2) - 5 * x * y + 3) * pow(cos(x / 5), 2);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}
	
}
