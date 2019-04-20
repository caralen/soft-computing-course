package hr.fer.zemris.neuralFuzzy.task7.ga;

import static hr.fer.zemris.neuralFuzzy.task7.Consts.tournamentSize;
import static hr.fer.zemris.neuralFuzzy.task7.ga.Util.findworst;
import static hr.fer.zemris.neuralFuzzy.task7.ga.Util.randomSelectUnits;

import java.util.List;

import hr.fer.zemris.neuralFuzzy.task7.Dataset;
import hr.fer.zemris.neuralFuzzy.task7.NeuralNet;

public class EliminationGA extends GeneticAlgorithm {
	
	private NeuralNet net;
	private Dataset dataset;
	private List<Unit> population;
	
	public EliminationGA(NeuralNet net, Dataset dataset, List<Unit> population) {
		this.net = net;
		this.dataset = dataset;
		this.population = population;
	}

	@Override
	public Unit iteration() {
		List<Unit> tournament = randomSelectUnits(population, tournamentSize);
		
		Unit worst = findworst(tournament);
		tournament.remove(worst);
		population.remove(worst);
		
		Unit child = crossover(tournament.get(0), tournament.get(1));
		mutate(child);
		
		child.setError(net.calcError(dataset, child.getParameters()));
		
		population.add(child);
		
		return Util.findBest(population);
	}
	
}
