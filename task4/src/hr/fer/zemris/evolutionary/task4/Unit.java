package hr.fer.zemris.evolutionary.task4;

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
}
