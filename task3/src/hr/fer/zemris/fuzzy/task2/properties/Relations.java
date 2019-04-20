package hr.fer.zemris.fuzzy.task2.properties;

import hr.fer.zemris.fuzzy.task1.domains.Domain;
import hr.fer.zemris.fuzzy.task1.domains.DomainElement;
import hr.fer.zemris.fuzzy.task1.domains.IDomain;
import hr.fer.zemris.fuzzy.task1.operations.IBinaryFunction;
import hr.fer.zemris.fuzzy.task1.operations.Operations;
import hr.fer.zemris.fuzzy.task1.sets.IFuzzySet;
import hr.fer.zemris.fuzzy.task1.sets.MutableFuzzySet;

public class Relations {
	
	private static final int first_index = 0;
	private static final int second_index = 1;
	private static final double zero_value = 0;
	private static final IBinaryFunction s = Operations.zadehOr();
	private static final IBinaryFunction t = Operations.zadehAnd();

	public static boolean isUtimesURelation(IFuzzySet relation) {
		int domainCardinality = relation.getDomain().getCardinality();
		
		if(domainCardinality == 0) 
			return false;
		if(relation.getDomain().elementForIndex(first_index).getNumberOfComponents() != 2) 
			return false;
		if(relation.getDomain().elementForIndex(first_index).getComponentValue(first_index) 
				!= relation.getDomain().elementForIndex(first_index).getComponentValue(second_index))
			return false;
		if(relation.getDomain().elementForIndex(domainCardinality-1).getComponentValue(first_index) 
				!= relation.getDomain().elementForIndex(domainCardinality-1).getComponentValue(second_index))
			return false;
		
		return true;
	}
	
	public static boolean isSymmetric(IFuzzySet relation) {
		if(!isUtimesURelation(relation))
			return false;
		
		int domainCardinality = relation.getDomain().getCardinality();
		int start = relation.getDomain().elementForIndex(first_index).getComponentValue(first_index);
		int end = relation.getDomain().elementForIndex(domainCardinality-1).getComponentValue(first_index);
		
		for(int i = start; i <= end; i++) {
			for(int j = start; j <= end; j++) {
				if(relation.getValueAt(DomainElement.of(i, j)) != relation.getValueAt(DomainElement.of(j, i)))
					return false;
			}
		}
		return true;
	}
	
	public static boolean isReflexive(IFuzzySet relation) {
		if(!isUtimesURelation(relation))
			return false;
		
		int start = startValue(relation.getDomain());
		int end = endValue(relation.getDomain());
		
		for(int i = start; i <= end; i++) {
			if(relation.getValueAt(DomainElement.of(i, i)) != 1)
				return false;
		}
		return true;
	}
	
	public static boolean isMaxMinTransitive(IFuzzySet relation) {
		if(!isUtimesURelation(relation))
			return false;
		
		int start = startValue(relation.getDomain());
		int end = endValue(relation.getDomain());
		
		for(int x = start; x <= end; x++) {
			for(int z = start; z <= end; z++) {
				double max = zero_value;
				for(int y = start; y <= end; y++) {
					max = s.valueAt(max, t.valueAt(relation.getValueAt(DomainElement.of(x, y)), relation.getValueAt(DomainElement.of(y, z))));
				}
				if(max > relation.getValueAt(DomainElement.of(x, z)))
					return false;
			}
		}
		return true;
	}

	public static IFuzzySet compositionOfBinaryRelations(IFuzzySet r1, IFuzzySet r2) {
		if(r1.getDomain().getComponent(second_index) != r2.getDomain().getComponent(first_index))
			throw new IllegalArgumentException("Fuzzy sets must have format XxY and YxZ for composition computation.");
		
		IDomain domainX = r1.getDomain().getComponent(first_index);
		IDomain domainY = r1.getDomain().getComponent(second_index);
		IDomain domainZ = r2.getDomain().getComponent(second_index);
		
		IDomain domain = Domain.combine(domainX, domainZ);
		MutableFuzzySet mutableSet = new MutableFuzzySet(domain);
		
		for(int x = startValue(domainX); x <= endValue(domainX); x++) {
			for(int z = startValue(domainZ); z <= endValue(domainZ); z++) {
				double max = zero_value;
				for(int y = startValue(domainY); y <= endValue(domainY); y++) {
					max = s.valueAt(max, t.valueAt(r1.getValueAt(DomainElement.of(x, y)), r2.getValueAt(DomainElement.of(y, z))));
				}
				mutableSet.set(DomainElement.of(x, z), max);
			}
		}
		return mutableSet;
	}
	
	private static int startValue(IDomain domain) {
		return domain.elementForIndex(first_index).getComponentValue(first_index);
	}
	
	private static int endValue(IDomain domain) {
		int domainCardinality = domain.getCardinality();
		return domain.elementForIndex(domainCardinality-1).getComponentValue(first_index);
	}

	public static String isFuzzyEquivalence(IFuzzySet r) {
		if(!isSymmetric(r))
			return "false";
		if(!isReflexive(r))
			return "false";
		if(!isMaxMinTransitive(r))
			return "false";
		
		return "true";
	}
}
