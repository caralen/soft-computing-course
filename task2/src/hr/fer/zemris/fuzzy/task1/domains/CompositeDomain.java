package hr.fer.zemris.fuzzy.task1.domains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CompositeDomain extends Domain {

//	private int current;
	private List<List<Integer>> elements;
	private SimpleDomain[] components;
	
	public CompositeDomain(SimpleDomain... components) {
		this.components = components;
		cartesian();
	}
	
	private void cartesian() {
		if (components.length < 2) {
			throw new IllegalArgumentException("Need at least two components for cartesian product.");
		}
		elements = cartesianRecursive(0, components);
		
		for(List<Integer> list : elements) {
			Collections.reverse(list);
		}
	}
	
	private static List<List<Integer>> cartesianRecursive(int index, SimpleDomain[] components) {
		List<List<Integer>> listOfLists = new ArrayList<>();
		if(index == components.length) {
			listOfLists.add(new ArrayList<>());
		} else {
			for(DomainElement element : components[index]) {
				for(List<Integer> list : cartesianRecursive(index + 1, components)) {
					list.add(element.getComponentValue(0));
					listOfLists.add(list);
				}
			}
		}
		return listOfLists;
	}

	@Override
	public Iterator<DomainElement> iterator() {
		return new IteratorImpl();
		
		/*current = 0;
		return new Iterator<DomainElement>() {

			@Override
			public boolean hasNext() {
				if(current >= elements.size()) return false;
				return true;
			}

			@Override
			public DomainElement next() {
				return DomainElement.of(toIntArray(elements.get(current++)));
			}
		};*/
	}
	
	private int[] toIntArray(List<Integer> list) {
		int[] array = new int[list.size()];

		for (int i = 0; i < array.length; i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	@Override
	public int getCardinality() {
		if(components.length == 0) return 0;
		
		int cardinality = 1;
		for(int i = 0; i < components.length; i++) {
			cardinality *= components[i].getCardinality();
		}
		return cardinality;
	}

	@Override
	public IDomain getComponent(int index) {
		if(index < 0 || index > components.length) return null;
		return components[index];
	}

	@Override
	public int getNumberOfComponents() {
		return components.length;
	}
	
	private class IteratorImpl implements Iterator<DomainElement> {
		private int current = 0;
		
		@Override
		public boolean hasNext() {
			if(current >= elements.size()) return false;
			return true;
		}

		@Override
		public DomainElement next() {
			return DomainElement.of(toIntArray(elements.get(current++)));
		}
	}
}
