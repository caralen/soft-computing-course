package hr.fer.zemris.fuzzy.task1.sets;

import hr.fer.zemris.fuzzy.task1.domains.DomainElement;
import hr.fer.zemris.fuzzy.task1.domains.IDomain;

public interface IFuzzySet {

	IDomain getDomain();
	double getValueAt(DomainElement element);
}
