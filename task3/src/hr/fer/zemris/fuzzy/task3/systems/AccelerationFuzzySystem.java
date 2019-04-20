package hr.fer.zemris.fuzzy.task3.systems;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.fuzzy.task1.domains.Debug;
import hr.fer.zemris.fuzzy.task1.domains.DomainElement;
import hr.fer.zemris.fuzzy.task1.sets.IFuzzySet;
import hr.fer.zemris.fuzzy.task3.FuzzySystem;
import hr.fer.zemris.fuzzy.task3.Rules;
import hr.fer.zemris.fuzzy.task3.defuzzify.Defuzzifier;
import hr.fer.zemris.fuzzy.task3.machine.DecisionMachine;

public class AccelerationFuzzySystem implements FuzzySystem {
	
	private Defuzzifier def;
	private DecisionMachine machine;
	private List<IFuzzySet> list;

	public AccelerationFuzzySystem(Defuzzifier def, DecisionMachine machine) {
		this.def = def;
		this.machine = machine;
	}

	@Override
	public int zakljuci(int L, int D, int LK, int DK, int V, int S) {
		
		list = new ArrayList<>();
		
		rule(Rules.ubrzaj, 
				Rules.sporo.getValueAt(DomainElement.of(V))
			);
		
		rule(Rules.jaceUspori, 
				Rules.brzo.getValueAt(DomainElement.of(V))
			);
		
		
		rule(Rules.jaceUbrzaj, 
				Rules.kriticnoBlizu.getValueAt(DomainElement.of(L)),
				Rules.kriticnoBlizu.getValueAt(DomainElement.of(LK))
			);
		
		rule(Rules.jaceUbrzaj, 
				Rules.kriticnoBlizu.getValueAt(DomainElement.of(D)), 
				Rules.kriticnoBlizu.getValueAt(DomainElement.of(DK))
			);
		
		rule(Rules.uspori,
				Rules.daleko.getValueAt(DomainElement.of(DK)),
				1-Rules.kriticnoBlizu.getValueAt(DomainElement.of(LK))
			);
		
		rule(Rules.uspori,
				Rules.daleko.getValueAt(DomainElement.of(LK)),
				1-Rules.kriticnoBlizu.getValueAt(DomainElement.of(DK))
			);
		
		rule(Rules.jaceUbrzaj,
				1-Rules.blizu.getValueAt(DomainElement.of(LK)),
				1-Rules.blizu.getValueAt(DomainElement.of(L)),
				1-Rules.blizu.getValueAt(DomainElement.of(DK)),
				1-Rules.blizu.getValueAt(DomainElement.of(D))
			);
		
		rule(Rules.topAcc,
				Rules.jakoDaleko.getValueAt(DomainElement.of(DK)),
				Rules.jakoDaleko.getValueAt(DomainElement.of(LK))
			);
		
		
		IFuzzySet rules = machine.union(listToArray(list));
		return def.defuzzify(rules);
		
	}
	
	private IFuzzySet[] listToArray(List<IFuzzySet> list) {
		IFuzzySet[] array = new IFuzzySet[list.size()];
		for(int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		return array;
	}
	
	private void rule(IFuzzySet set, double... values) {
		list.add(machine.cut(set, machine.mandani(values)));
	}
	
	public void print() {
		Debug.print(list.get(0), "Ako je sporo, ubrzaj.");
		System.out.println("Vrijednost je: " + def.defuzzify(list.get(0)));
		
		Debug.print(list.get(1), "Ako je brzo, jace uspori.");
		System.out.println("Vrijednost je: " + def.defuzzify(list.get(1)));
		
		Debug.print(list.get(2), "Ako je sporo, ubrzaj.");
		System.out.println("Vrijednost je: " + def.defuzzify(list.get(2)));
		
		Debug.print(list.get(3), "Ako je L i LK kriticno blizu, jace ubrzaj.");
		System.out.println("Vrijednost je: " + def.defuzzify(list.get(3)));
		
		Debug.print(list.get(4), "Ako je D i DK kriticno blizu, jace ubrzaj.");
		System.out.println("Vrijednost je: " + def.defuzzify(list.get(4)));
		
		Debug.print(list.get(5), "Ako je DK daleko i LK nije kriticno blizu, uspori.");
		System.out.println("Vrijednost je: " + def.defuzzify(list.get(5)));
		
		Debug.print(list.get(6), "Ako je LK daleko i DK nije kriticno blizu, uspori.");
		System.out.println("Vrijednost je: " + def.defuzzify(list.get(6)));
		
		Debug.print(list.get(6), "Ako je LK nije blizu i DK nije blizu i L nije blizu i D nije blizu, jace ubrzaj.");
		System.out.println("Vrijednost je: " + def.defuzzify(list.get(6)));
		
		Debug.print(list.get(6), "Ako je LK je jako daleko i DK je jako daleko, maksimalna akceleracija.");
		System.out.println("Vrijednost je: " + def.defuzzify(list.get(6)));
	}
}
