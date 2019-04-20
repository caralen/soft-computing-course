package hr.fer.zemris.fuzzy.task1.domains;

import hr.fer.zemris.fuzzy.task1.sets.IFuzzySet;

public class Debug {

	public static void print(IDomain domain, String headingText) {
		if (headingText != null) {
			System.out.println(headingText);
		}
		for (DomainElement e : domain) {
			System.out.println("Element domene: " + e.toString());
		}
		System.out.println("Kardinalitet domene je: " + domain.getCardinality());
		System.out.println();
	}
	
	public static void print(IFuzzySet set, String headingText) {
		if (headingText != null) {
			System.out.println(headingText);
		}
		for (DomainElement e : set.getDomain()) {
			System.out.println("d(" + e.toString() + ")=" + String.format("%.6f", set.getValueAt(e)));
		}
		System.out.println();
	}
}
