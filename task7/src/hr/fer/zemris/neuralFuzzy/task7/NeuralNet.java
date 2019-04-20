package hr.fer.zemris.neuralFuzzy.task7;

import static java.lang.Math.abs;
import static java.lang.Math.exp;

import java.util.Arrays;

public class NeuralNet {

	private int[] architecture;
	
	private double[] outs;
	
	private int paramsCounter;

	public NeuralNet(int[] architecture) {
		this.architecture = architecture;
		int neuronsNum = 0;

		for (int i = 0; i < architecture.length; i++) {
			neuronsNum += architecture[i];
		}
		outs = new double[neuronsNum];
	}

	public double[] calcOutput(double[] params, double[] ins) {
		int outsCounter;
		paramsCounter = 0;

		for (outsCounter = 0; outsCounter < architecture[0]; outsCounter++) {
			outs[outsCounter] = ins[outsCounter];
		}

		for (int layer = 1; layer < architecture.length; layer++) {

			for (int i = 0; i < architecture[layer]; i++) {

				if (layer == 1)
					outs[outsCounter++] = similarity(params);
				else
					outs[outsCounter++] = sigmoid(weightedSum(params, layer, i));

			}
		}
		int lastLayer = architecture[architecture.length - 1]; 
		return Arrays.copyOfRange(outs, outs.length - lastLayer, outs.length);
	}

	public double calcError(Dataset dataset, double[] params) {
		int lastLayer = architecture[architecture.length - 1];
		double sum = 0;
		
		for (int i = 0; i < dataset.size(); i++) {
			
			double[] ins = dataset.getSample(i);
			double[] output = calcOutput(params, Arrays.copyOfRange(ins, 0, 2));
			
			for(int j = 0; j < lastLayer; j++) {
				sum += Math.pow(dataset.getSample(i)[j] - output[j], 2);
			}
		}
		return sum / dataset.size();
	}
	
	public void test(Dataset dataset, double[] params) {
		int correct = 0;
		for(int i = 0; i < dataset.size(); i++) {
			double[] sample = dataset.getSample(i);
			double[] ins = Arrays.copyOfRange(sample, 0, 2);
			double[] outs = Arrays.copyOfRange(sample, 2, 5);
			
			double[] output = calcOutput(params, ins);
			output = squeeze(output);
			boolean equal = Arrays.toString(output).equals(Arrays.toString(outs));
			
			System.out.println("Ispravni: " + Arrays.toString(outs) + ", izracunati: " + Arrays.toString(output) + ", equal: " + equal);
			
			if(equal) correct++;
		}
		System.out.println("Ispravnih klasifikacija: " + correct);
	}
	
	private double[] squeeze(double[] arr) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] < 0.5)
				arr[i] = 0;
			else
				arr[i] = 1;
		}
		return arr;
	}

	public int numberOfNeededParams() {
		int params = 0;

		for (int i = 1; i < architecture.length; i++) {
			if (i == 1) {
				params += architecture[i - 1] * architecture[i] * 2;
			} else {
				params += (architecture[i - 1] + 1) * architecture[i];
			}
		}
		return params;
	}
	
	private int numberOfOutsBefore(int layer) {
		int outs = 0;

		for (int i = 0; i < layer; i++) {
			outs += architecture[i];
		}
		return outs;
	}

	private double similarity(double[] params) {
		double sum = 0;

		for (int i = 0; i < architecture[0]; i++) {
			sum += abs(outs[i++] - params[paramsCounter]) / abs(params[paramsCounter + 1]);
			paramsCounter += 2;
		}
		return 1 / (1 + sum);
	}

	private double weightedSum(double[] params, int layer, int index) {
		int outsCounter = numberOfOutsBefore(layer - 1) + index;
		double sum = params[paramsCounter++];
		
		for (int i = 0; i < architecture[layer-1]; i++) {
			sum += outs[outsCounter++] * params[paramsCounter++];
		}
		return sum / architecture[layer-1];
	}
	
	private double sigmoid(double x) {
		return 1.0 / (1 + exp(-x));
	}
}
