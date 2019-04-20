package hr.fer.zemris.fuzzy.task2;

import hr.fer.zemris.fuzzy.task1.DomainElement;
import hr.fer.zemris.fuzzy.task1.IDomain;

public class CalculatedFuzzySet implements IFuzzySet {
	
	private IDomain domain;
	private IIntUnaryFunction function;
	
	public CalculatedFuzzySet(IDomain domain, IIntUnaryFunction function) {
		this.domain = domain;
		this.function = function;
	}

	@Override
	public IDomain getDomain() {
		return domain;
	}

	@Override
	public double getValueAt(DomainElement element) {
		return function.valueAt(domain.indexOfElement(element));
	}

}
