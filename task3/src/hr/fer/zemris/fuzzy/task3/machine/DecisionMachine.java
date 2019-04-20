package hr.fer.zemris.fuzzy.task3.machine;

import hr.fer.zemris.fuzzy.task1.sets.IFuzzySet;

public interface DecisionMachine {

	double mandani(double ...values);
	IFuzzySet cut(IFuzzySet set, double value);
	IFuzzySet union(IFuzzySet ...sets);
}
