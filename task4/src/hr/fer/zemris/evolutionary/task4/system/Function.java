package hr.fer.zemris.evolutionary.task4.system;

@FunctionalInterface
public interface Function {

	double evaluate(double x, double y, double[] params);
}
