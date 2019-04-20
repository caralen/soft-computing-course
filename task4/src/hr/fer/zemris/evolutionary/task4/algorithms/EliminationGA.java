package hr.fer.zemris.evolutionary.task4.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static hr.fer.zemris.evolutionary.task4.Main.calculateError;

import hr.fer.zemris.evolutionary.task4.Unit;

public class EliminationGA extends GeneticAlgorithm {
	
	private double mutationProbability;
	private static final int tournament_size = 3;
	
	public EliminationGA(double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

	@Override
	public Unit iteration(List<Unit> population) {
		Random rand = new Random();
		
		List<Unit> tournament = new ArrayList<>();
		
		for(int i = 0; i < tournament_size; i++) {
			tournament.add(population.get(rand.nextInt(population.size())));
		}
		
		Unit worst = tournament.get(0);
		for(Unit unit : tournament) {
			if(worst.getError() < unit.getError())
				worst = unit;
		}
		tournament.remove(worst);
		
		worst = crossover(tournament.get(0), tournament.get(1));
		mutate(worst, mutationProbability);
		calculateError(worst);
		return worst;
	}
	
}
