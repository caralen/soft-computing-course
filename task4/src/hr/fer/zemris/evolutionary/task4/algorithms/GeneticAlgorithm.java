package hr.fer.zemris.evolutionary.task4.algorithms;

import java.util.List;
import java.util.Random;

import hr.fer.zemris.evolutionary.task4.Unit;

public abstract class GeneticAlgorithm implements EvolutionaryAlgorithm {

	@Override
	public abstract Unit iteration(List<Unit> population);
	
	@Override
	public void mutate(Unit unit, double mutationProbability) {
		Random rand = new Random();
		
		for(int i = 0; i < unit.getParameters().length; i++) {
			if(rand.nextFloat() < mutationProbability)
				unit.setParameter(1 - unit.getParameter(i), i);
		}
	}
	
	@Override
	public Unit crossover(Unit first, Unit second) {
		Random rand = new Random();
		double[] childParameters = new double[first.length()];
		
		for(int i = 0; i < first.length(); i++) {
			childParameters[i] = rand.nextFloat() < 0.5 ?
					first.getParameter(i) : second.getParameter(i);
		}
		return new Unit(childParameters);
	}
}
