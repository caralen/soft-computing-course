package hr.fer.zemris.evolutionary.task4.algorithms;

import static hr.fer.zemris.evolutionary.task4.Main.calculateError;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hr.fer.zemris.evolutionary.task4.Unit;

public class GenerationGA extends GeneticAlgorithm {
	
	private boolean elitism;
	
	private double mutationProbability;
	
	private static final int parents_number = 2;
	
	
	public GenerationGA(boolean elitism, double mutationProbability) {
		this.elitism = elitism;
		this.mutationProbability = mutationProbability;
	}

	@Override
	public Unit iteration(List<Unit> population) {
		List<Unit> nextPopulation = new ArrayList<>();
		
		if(elitism)
			nextPopulation.add(findBest(population));
		
		boolean skipFirst = elitism;
		
		for(int i = 0; i < population.size() - 1; i++) {
			if(skipFirst) {
				skipFirst = false;
				continue;
			}
			Unit[] parents = randomSelectParents(population);
			Unit child = crossover(parents[0], parents[1]);
			mutate(child, mutationProbability);
			calculateError(child);
			nextPopulation.add(child);
		}
		population = nextPopulation;
		return findBest(population);
	}
	
	private Unit[] randomSelectParents(List<Unit> population) {
		
		Unit[] parents = new Unit[parents_number];
		Random rand = new Random();
		
		for(int i = 0; i < parents_number; i++) {
			parents[i] = population.get(rand.nextInt(population.size()));
		}
		return parents;
	}

	private Unit findBest(List<Unit> population) {
		Unit best = population.get(0);
		
		for(Unit unit : population) {
			if(unit.getError() < best.getError())
				best = unit;
		}
		return best;
	}

}
