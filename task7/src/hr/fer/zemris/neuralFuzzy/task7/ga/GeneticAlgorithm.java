package hr.fer.zemris.neuralFuzzy.task7.ga;

import static hr.fer.zemris.neuralFuzzy.task7.Consts.pm1;
import static hr.fer.zemris.neuralFuzzy.task7.Consts.pm2;
import static hr.fer.zemris.neuralFuzzy.task7.Consts.pm3;
import static hr.fer.zemris.neuralFuzzy.task7.Consts.sigma1;
import static hr.fer.zemris.neuralFuzzy.task7.Consts.sigma2;
import static hr.fer.zemris.neuralFuzzy.task7.Consts.sigma3;
import static hr.fer.zemris.neuralFuzzy.task7.Consts.t1;
import static hr.fer.zemris.neuralFuzzy.task7.Consts.t2;
import static hr.fer.zemris.neuralFuzzy.task7.Consts.t3;

import java.util.Random;

public abstract class GeneticAlgorithm {
	
	private Random rand;
	
	public GeneticAlgorithm() {
		rand = new Random();
	}

	public abstract Unit iteration();
	
	public void mutate(Unit unit) {
		double num = rand.nextDouble();
		
		double tSum = t1 + t2 + t3;
		
		if(num < (t1 / tSum))
			mutate1(unit, pm1, sigma1);
		
		else if(num > (t1 / tSum) && num < ((t1+t2) / tSum))
			mutate1(unit, pm2, sigma2);
		
		else
			mutate2(unit, pm3, sigma3);
			
	}
	
	public Unit crossover(Unit first, Unit second) {
		double num = rand.nextDouble();
		
		if(num < (1 / 3.0))
			return uniformCrossover(first, second);
		
		if(num > (1 / 3.0) && num < (2 / 3.0))
			return arithmeticCrossover(first, second);
		
		return heuristicCrossover(first, second);
	}
	
	
	private void mutate1(Unit unit, double mutationProbability, double sigma) {
		
		for(int i = 0; i < unit.length(); i++) {
			if(rand.nextFloat() < mutationProbability)
				unit.setParameter(rand.nextGaussian() * sigma, i);
		}
	}
	
	private void mutate2(Unit unit, double mutationProbability, double sigma) {
		
		for(int i = 0; i < unit.length(); i++) {
			if(rand.nextFloat() < mutationProbability)
				unit.setParameter(unit.getParameter(i) + rand.nextGaussian() * sigma, i);
		}
	}
	
	private Unit uniformCrossover(Unit first, Unit second) {
		double[] childParameters = new double[first.length()];
		
		for(int i = 0; i < first.length(); i++) {
			childParameters[i] = rand.nextFloat() < 0.5 ?
					first.getParameter(i) : second.getParameter(i);
		}
		return new Unit(childParameters);
	}
	
	private Unit arithmeticCrossover(Unit p1, Unit p2) {
		double rand = new Random().nextDouble();
		double[] x = new double[p1.length()];
		for(int i = 0; i < p1.length(); i++) {
			x[i] = rand * p1.getParameter(i) + (1 - rand) * p2.getParameter(i);
		}
		return new Unit(x);
	}
	
	private Unit heuristicCrossover(Unit p1, Unit p2) {
		if(p1.getError() < p2.getError()) {
			Unit temp = p2;
			p2 = p1;
			p1 = temp;
		}
		Random rand = new Random();
		double[] x = new double[p1.length()];
		for(int i = 0; i < p1.length(); i++) {
			x[i] = rand.nextDouble() * (p2.getParameter(i) - p1.getParameter(i)) + p2.getParameter(i);
		}
		return new Unit(x);
	}
	
}
