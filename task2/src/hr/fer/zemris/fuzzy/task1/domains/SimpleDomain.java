package hr.fer.zemris.fuzzy.task1.domains;

import java.util.Iterator;

public class SimpleDomain extends Domain {

	private int first;
	private int last;
	private static final int numberOfComponents = 1;
	
	public SimpleDomain(int first, int last) {
		this.first = first;
		this.last = last;
	}

	@Override
	public Iterator<DomainElement> iterator() {
		return new IteratorImpl();
	}

	@Override
	public int getCardinality() {
		return Math.abs(last - first);
	}

	@Override
	public IDomain getComponent(int index) {
		if(index != 0) return null;
		return this;
	}

	@Override
	public int getNumberOfComponents() {
		return numberOfComponents;
	}

	public int getFirst() {
		return first;
	}

	public int getLast() {
		return last;
	}
	
	private class IteratorImpl implements Iterator<DomainElement> {
		private int current = first;
		
		@Override
		public boolean hasNext() {
			return current == last ? false : true;
		}

		@Override
		public DomainElement next() {
			return DomainElement.of(current++);
		}
	}
}
