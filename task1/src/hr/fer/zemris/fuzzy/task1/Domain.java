package hr.fer.zemris.fuzzy.task1;

import java.util.Iterator;

public abstract class Domain implements IDomain {
	
	public abstract int getCardinality();
	
	public abstract IDomain getComponent(int index);
	
	public abstract int getNumberOfComponents();
	
	public static IDomain intRange(int first, int last) {
		return new SimpleDomain(first, last);
	}
	
	public static IDomain combine(IDomain first, IDomain second) {
		return new CompositeDomain((SimpleDomain) first, (SimpleDomain) second);
	}
	
	public int indexOfElement(DomainElement element) {
		Iterator<DomainElement> iterator = this.iterator();
		int counter = 0;
		
		while(iterator.hasNext()) {
			DomainElement next = iterator.next();
			if(next.equals(element)) return counter;
			counter++;
		}
		return -1;
	}
	
	public DomainElement elementForIndex(int index) {
		Iterator<DomainElement> iterator = this.iterator();
		int counter = 0;
		
		while(iterator.hasNext()) {
			if(counter == index) return iterator.next();
			iterator.next();
			counter++;
		}
		return null;
	}
}
