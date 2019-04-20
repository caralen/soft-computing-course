package hr.fer.zemris.neuralFuzzy.task7;

import static hr.fer.zemris.neuralFuzzy.task7.Consts.architecture;
import static hr.fer.zemris.neuralFuzzy.task7.Consts.filename;
import static hr.fer.zemris.neuralFuzzy.task7.Consts.iterations;
import static hr.fer.zemris.neuralFuzzy.task7.Consts.popSize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hr.fer.zemris.neuralFuzzy.task7.ga.EliminationGA;
import hr.fer.zemris.neuralFuzzy.task7.ga.GeneticAlgorithm;
import hr.fer.zemris.neuralFuzzy.task7.ga.Unit;

public class Main {

	public static void main(String[] args) throws IOException {
		Dataset dataset = Dataset.getDataset(filename);
		NeuralNet net = new NeuralNet(architecture);
		List<Unit> population = createPopulation(net, dataset);
		GeneticAlgorithm ga = new EliminationGA(net, dataset, population);

		Unit best = null;
		for(int i = 0; i < iterations; i++) {
			best = ga.iteration();
			
			if(i % 10000 == 0) 
				System.out.println(best.getError());
		}
		net.test(dataset, best.getParameters());
	}
	
	
	private static double[] createParams(int number) {
		double[] params = new double[number];
		Random rand = new Random();
		
		for(int i = 0; i < number; i++) {
			params[i] = rand.nextDouble() * 10 - 5;
		}
		return params;
	}
	
	private static List<Unit> createPopulation(NeuralNet net, Dataset dataset){
		List<Unit> population = new ArrayList<>();
		
		for(int i = 0; i < popSize; i++) {
			double[] params = createParams(net.numberOfNeededParams());
			Unit unit = new Unit(params);
			unit.setError(net.calcError(dataset, params));
			population.add(unit);
		}
		return population;
	}

}
