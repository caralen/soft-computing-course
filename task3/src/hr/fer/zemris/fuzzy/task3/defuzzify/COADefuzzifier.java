package hr.fer.zemris.fuzzy.task3.defuzzify;

import hr.fer.zemris.fuzzy.task1.domains.DomainElement;
import hr.fer.zemris.fuzzy.task1.domains.IDomain;
import hr.fer.zemris.fuzzy.task1.sets.IFuzzySet;

public class COADefuzzifier implements Defuzzifier {
	
	private static final int first_element = 0;

	@Override
	public int defuzzify(IFuzzySet set) {
		IDomain domain = set.getDomain();
		double up = 0;
		double down = 0;
		
		for(DomainElement element : domain) {
			double value = set.getValueAt(element);
			up += value * element.getComponentValue(first_element);
			down += value;
		}
		return (int) (up / down);
	}

}
