package hr.fer.zemris.evolutionary.task4.algorithms;

import java.util.List;

import hr.fer.zemris.evolutionary.task4.Unit;

public interface EvolutionaryAlgorithm {

	Unit iteration(List<Unit> population);
	void mutate(Unit unit , double mutationProbability);
	Unit crossover(Unit first, Unit second);
}
