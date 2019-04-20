package hr.fer.zemris.neural.task5.nn;

import java.util.List;

public interface Backpropagation {

	public void trainNet();
	public List<Double> classify(List<Double> inputs);
}
