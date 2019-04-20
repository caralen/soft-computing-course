package hr.fer.zemris.fuzzy.task3.machine;

import hr.fer.zemris.fuzzy.task1.domains.DomainElement;
import hr.fer.zemris.fuzzy.task1.operations.IBinaryFunction;
import hr.fer.zemris.fuzzy.task1.operations.Operations;
import hr.fer.zemris.fuzzy.task1.sets.IFuzzySet;
import hr.fer.zemris.fuzzy.task1.sets.MutableFuzzySet;

public class MinimumBasedMachine implements DecisionMachine {
	
	public static final IBinaryFunction tNorm = Operations.zadehAnd();
	public static final IBinaryFunction sNorm = Operations.zadehOr();

	@Override
	public double mandani(double ...values) {
		if (values.length == 0) return 0;
		double min = values[0];
		
		for(double value : values) {
			min = tNorm.valueAt(min, value);
		}
		return min;
	}

	@Override
	public IFuzzySet cut(IFuzzySet set, double value) {
		MutableFuzzySet mutable = new  MutableFuzzySet(set.getDomain());
		
		for(DomainElement e : set.getDomain()) {
			mutable.set(e, tNorm.valueAt(value, set.getValueAt(e)));
		}
		return mutable;
	}

	@Override
	public IFuzzySet union(IFuzzySet ...sets) {
		MutableFuzzySet result = new MutableFuzzySet(sets[0].getDomain());
		
		for(IFuzzySet set : sets) {
			for(DomainElement e : set.getDomain()) {
				result.set(e, sNorm.valueAt(result.getValueAt(e), set.getValueAt(e)));
			}
		}
		return result;
	}

}
