package hr.fer.zemris.neural.task5.nn;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.neural.task5.utils.Consts;
import hr.fer.zemris.neural.task5.utils.Dataset;

import static hr.fer.zemris.neural.task5.utils.Consts.maxIterations;

public class MiniBatch implements Backpropagation {
	
	private NeuralNet net;
	private Dataset dataset;
	
	public MiniBatch(Dataset dataset) {
		this.net = new NeuralNet(Consts.architecture);
		this.dataset = dataset;
	}

	@Override
	public void trainNet() {
		
		List<double[]> samples = new ArrayList<>();
		List<double[]> outputs = new ArrayList<>();
		
		
		for(int i = 0; i < maxIterations; i++) {
			
			for(int s = 0; s < dataset.size(); s++) {
				samples.add(dataset.getSample(s));
				outputs.add(dataset.getOutput(s));
				
				if(s % 10 == 0) {
					net.propagateData(samples);
					net.calculateErrors(samples, outputs);
					net.updateWeights(samples);
					
					samples.clear();
					outputs.clear();
				}
			}
			if(i % 10 == 0) 
				System.out.println("Iteracija=" + i + " Srednja kvadratna pogreska je: " + net.mse(samples, outputs));
		}
	}
	
	@Override
	public List<Double> classify(List<Double> inputs) {
		return net.classify(inputs);
	}

}
