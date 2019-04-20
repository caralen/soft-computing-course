package hr.fer.zemris.neuralFuzzy.task7.ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Util {

	public static List<Unit> randomSelectUnits(List<Unit> population, int numberOfParents) {
		Random rand = new Random();
		List<Unit> parents = new ArrayList<>();
		
		while(parents.size() != numberOfParents) {
			Unit unit = population.get(rand.nextInt(population.size()));
			
			if(!parents.contains(unit))
				parents.add(unit);
		}
		return parents;
	}

	public static Unit findBest(List<Unit> population) {
		Unit best = population.get(0);
		
		for(Unit unit : population) {
			if(unit.getError() < best.getError())
				best = unit;
		}
		return best;
	}
	
	public static Unit findworst(List<Unit> population) {
		Unit worst = population.get(0);
		
		for(Unit unit : population) {
			if(unit.getError() > worst.getError())
				worst = unit;
		}
		return worst;
	}
}
