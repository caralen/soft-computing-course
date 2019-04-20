package hr.fer.zemris.fuzzy.task2;

import hr.fer.zemris.fuzzy.task1.DomainElement;
import hr.fer.zemris.fuzzy.task1.IDomain;

public class MutableFuzzySet implements IFuzzySet {
	
	private double[] memberships;
	private IDomain domain;
	
	public MutableFuzzySet(IDomain domain) {
		this.domain = domain;
		memberships = new double[domain.getCardinality()];
	}

	@Override
	public IDomain getDomain() {
		return domain;
	}

	@Override
	public double getValueAt(DomainElement element) {
		return memberships[domain.indexOfElement(element)];
	}

	public MutableFuzzySet set(DomainElement element, double value) {
		memberships[domain.indexOfElement(element)] = value;
		return this;
	}
}
