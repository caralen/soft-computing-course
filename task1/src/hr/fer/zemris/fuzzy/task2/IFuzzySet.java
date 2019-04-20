package hr.fer.zemris.fuzzy.task2;

import hr.fer.zemris.fuzzy.task1.DomainElement;
import hr.fer.zemris.fuzzy.task1.IDomain;

public interface IFuzzySet {

	IDomain getDomain();
	double getValueAt(DomainElement element);
}
