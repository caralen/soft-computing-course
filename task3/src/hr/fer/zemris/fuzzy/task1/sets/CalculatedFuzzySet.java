package hr.fer.zemris.fuzzy.task1.sets;

import hr.fer.zemris.fuzzy.task1.domains.DomainElement;
import hr.fer.zemris.fuzzy.task1.domains.IDomain;

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
