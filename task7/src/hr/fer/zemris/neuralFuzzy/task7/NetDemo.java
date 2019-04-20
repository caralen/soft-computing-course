package hr.fer.zemris.neuralFuzzy.task7;

import static hr.fer.zemris.neuralFuzzy.task7.Consts.architecture;
import static hr.fer.zemris.neuralFuzzy.task7.Consts.filename;

import java.io.IOException;
import java.util.Random;

public class NetDemo {
	
	public static void main(String[] args) throws IOException {
		Dataset dataset = Dataset.getDataset(filename);
		NeuralNet net = new NeuralNet(architecture);
		
		System.out.println(net.calcError(dataset, createParams(net.numberOfNeededParams())));
	}
	
	private static double[] createParams(int number) {
		double[] params = new double[number];
		Random rand = new Random();
		
		for(int i = 0; i < number; i++) {
			params[i] = rand.nextDouble();
		}
		return params;
	}

}
