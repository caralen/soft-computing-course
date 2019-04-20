package hr.fer.zemris.neural.task5.nn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import hr.fer.zemris.neural.task5.utils.Consts;
import hr.fer.zemris.neural.task5.utils.Util;

public class NeuralNet {
	
	protected int[] architecture;
	protected Map<Integer, List<Neuron>> layers;
	private Random rand;

	public NeuralNet(int[] architecture) {
		this.architecture = architecture;
		layers = new LinkedHashMap<>();
		rand = new Random();
		createNeurons();
	}
	
	protected void createNeurons() {
		for(int layerIndex = 0; layerIndex < architecture.length; layerIndex++) {
			List<Neuron> neurons = new ArrayList<>();
			int prevLayerSize = layerIndex == 0 ? 0 : architecture[layerIndex-1];
			
			for(int i = 0; i < architecture[layerIndex]; i++) {
				Neuron neuron = new Neuron(layerIndex, i);
				createWeights(neuron, prevLayerSize);
				neurons.add(neuron);
			}
			layers.put(layerIndex, neurons);
		}
	}
	
	private void createWeights(Neuron neuron, int prevLayerSize) {
		if (prevLayerSize == 0) return;
		
		double[] weights = new double[prevLayerSize];
		
		for(int i = 0; i < prevLayerSize; i++) {
			weights[i] = rand.nextDouble();
		}
		neuron.setWeights(weights);
		neuron.setW0(rand.nextDouble());
	}
	
	
	public List<Double> classify(List<Double> inputs) {
		
		clearData();
		
		List<double[]> testSample = new ArrayList<>();
		double[] ins = Util.listToArray(inputs);
		testSample.add(ins);
		
		propagateData(testSample);
		
		List<Double> outs = new ArrayList<>();
		for(Neuron neuron : layers.get(layers.size()-1)) {
			outs.add(neuron.sample(0).getOut());
		}
		return outs;
	}
		
	private void createData(int samplesNum) {
		for (Map.Entry<Integer, List<Neuron>> layerEntry : layers.entrySet()) {
			List<Neuron> layer = layerEntry.getValue();
			for(Neuron neuron : layer) {
				neuron.createData(samplesNum);
			}
		}
	}

	
	private void clearData() {
		for (Map.Entry<Integer, List<Neuron>> layerEntry : layers.entrySet()) {
			List<Neuron> layer = layerEntry.getValue();
			for(Neuron neuron : layer) {
				neuron.clearData();
			}
		}
	}

	protected void propagateData(List<double[]> samples) {
		int samplesNumber = samples.size();
		createData(samplesNumber);
		
		for (Map.Entry<Integer, List<Neuron>> layerEntry : layers.entrySet()) {
			List<Neuron> layer = layerEntry.getValue();
			
			if(layerEntry.getKey() == 0) {
				for(int i = 0; i < layer.size(); i++) {
					Neuron neuron = layer.get(i);
					
					for(int s = 0; s < samplesNumber; s++) {
						double in = samples.get(s)[i];
						neuron.sample(s).setOut(in);
					}
				}
				
			} else {
				for(Neuron neuron: layer) {
					List<Neuron> prevLayer = layers.get(layerEntry.getKey()-1);
					
					for(int s = 0; s < samplesNumber; s++) {
						double sum = neuron.getW0();
						
						for(int j = 0; j < prevLayer.size(); j++) {
							Neuron prevNeuron = prevLayer.get(j);
							sum += prevNeuron.sample(s).getOut() * neuron.getWeight(j);
						}
						sum = Util.sigmoid(sum);
						neuron.sample(s).setOut(sum);
						
					}
				}
			}
		}
	}
	
	protected void calculateErrors(List<double[]> samples, List<double[]> outputs) {
		ArrayList<Integer> keys = new ArrayList<Integer>(layers.keySet());
		Collections.reverse(keys);
        
		for(Integer key: keys) {
				
			for(int s = 0; s < samples.size(); s++) {
				for(Neuron neuron: layers.get(key)) {
					
					//izlazni sloj
					if(key.equals(keys.size()-1)) {
						double y = neuron.sample(s).getOut();
						double error = y * (1 - y) * (outputs.get(s)[neuron.getOrder()] - y);
						neuron.sample(s).setError(error);
						
					//skriveni sloj
					} else {
						double sum = 0;
						for(Neuron nextNeuron: layers.get(key+1)) {
							sum += nextNeuron.sample(s).getError() * nextNeuron.getWeight(neuron.getOrder());
						}
						double y = neuron.sample(s).getOut();
						neuron.sample(s).setError(y * (1-y) * sum);
					}
				}
			}
        }
	}
	

	protected void updateWeights(List<double[]> samples) {
		for (Map.Entry<Integer, List<Neuron>> pair : layers.entrySet()) {
			if(pair.getKey() == 0) continue;
			
			for(Neuron neuron: pair.getValue()) {
				for(Neuron prevNeuron: layers.get(pair.getKey()-1)) {
					
					double sum = 0;
					double sumW0 = 0;
					
					for(int s = 0; s < samples.size(); s++) {
						sum += neuron.sample(s).getError() * prevNeuron.sample(s).getOut();
						sumW0 += neuron.sample(s).getError();
					}
					neuron.setWeight(neuron.getWeight(prevNeuron.getOrder()) + Consts.eta * sum, prevNeuron.getOrder());
					neuron.setW0(neuron.getW0() + Consts.eta * sumW0);
				}
			}
		}
	}
	
	public double mse(List<double[]> samples, List<double[]> outputs) {
		double sum = 0;
				
		for(Neuron neuron : layers.get(layers.size() - 1)) {
			for(int s = 0; s < outputs.size(); s++) {
				sum += Math.pow(neuron.sample(s).getOut() - outputs.get(s)[neuron.getOrder()], 2);
			}
		}
		return sum / (2 * outputs.size());
	}
	
	public double[] getOuput(int size) {
		double[] arr = new double[size];
		int counter = 0;
		
		for(Neuron neuron : layers.get(layers.size()-1)) {
			for(int s = 0; s < size; s++) {
				arr[counter++] = neuron.sample(s).getOut();
			}
		}
		return arr;
	}
	
	public void printOuput(int size) {
		
		for(int s = 0; s < size; s++) {

			double[] arr = new double[architecture[architecture.length-1]];
			int counter = 0;
			
			for(Neuron neuron : layers.get(layers.size()-1)) {
				arr[counter++] = neuron.sample(s).getOut();
			}
			System.out.println(Arrays.toString(arr));
		}
	}

}
