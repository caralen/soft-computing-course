package hr.fer.zemris.neural.task5.nn;

public class Neuron {

	private int layer;
	
	private int order;
	
	private double[] weights;
	
	private double w0;
	
	private NeuronData[] data;
	
	public Neuron(int layer, int order) {
		this.layer = layer;
		this.order = order;
	}
	
	public int getLayer() {
		return layer;
	}

	public int getOrder() {
		return order;
	}
	
	public double[] getWeights() {
		return weights;
	}
	
	public double getWeight(int index) {
		return weights[index];
	}
	
	public void setWeights(double[] weights) {
		this.weights = weights;
	}
	
	public void setWeight(double weight, int index) {
		this.weights[index] = weight;
	}
	
	public double getW0() {
		return w0;
	}

	public void setW0(double w0) {
		this.w0 = w0;
	}

	public NeuronData sample(int index) {
		return data[index];
	}
	
	public void createData(int samplesNum) {
		data = new NeuronData[samplesNum];
		for(int i = 0; i < samplesNum; i++) {
			data[i] = new NeuronData();
		}
	}
	
	public void clearData() {
		data = null;
	}
}
