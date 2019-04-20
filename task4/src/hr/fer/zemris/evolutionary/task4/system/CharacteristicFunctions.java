package hr.fer.zemris.evolutionary.task4.system;

import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.E;
import static java.lang.Math.pow;

public class CharacteristicFunctions {

	public static Function f = (x, y, p) -> {
		if (p.length != 5)
			throw new IllegalArgumentException("Length of parameters array must be 5.");

		double sinValue = sin(p[0] + p[1] * x);
		double cosValue = p[2] * cos(x * (p[3] + y));
		double eValue = 1 / (1 + pow(E, pow(x - p[4], 2)));

		return sinValue + cosValue * eValue;
	};
			
}
