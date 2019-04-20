package hr.fer.zemris.neuralFuzzy.task7.ga;

import java.util.Arrays;

public class Unit {

	private double[] parameters;
	
	private double error;

	public Unit(double... parameters) {
		this.parameters = parameters;
	}

	public double[] getParameters() {
		return parameters;
	}

	public double getError() {
		return error;
	}

	public void setError(double error) {
		this.error = error;
	}
	
	public double getParameter(int index) {
		if(index < 0 || index >= parameters.length)
			throw new IllegalArgumentException("Index too big or too small!");
		
		return parameters[index];
	}
	
	public void setParameter(double value, int index) {
		if(index < 0 || index >= parameters.length)
			throw new IllegalArgumentException("Index too big or too small!");
		
		parameters[index] = value;
	}
	
	public int length() {
		return parameters.length;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unit other = (Unit) obj;
		if (!Arrays.equals(parameters, other.parameters))
			return false;
		return true;
	}
	
}
