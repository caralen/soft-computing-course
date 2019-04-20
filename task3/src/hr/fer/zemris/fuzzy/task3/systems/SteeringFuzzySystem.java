package hr.fer.zemris.fuzzy.task3.systems;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.fuzzy.task1.domains.DomainElement;
import hr.fer.zemris.fuzzy.task1.sets.IFuzzySet;
import hr.fer.zemris.fuzzy.task3.FuzzySystem;
import hr.fer.zemris.fuzzy.task3.Rules;
import hr.fer.zemris.fuzzy.task3.defuzzify.Defuzzifier;
import hr.fer.zemris.fuzzy.task3.machine.DecisionMachine;

public class SteeringFuzzySystem implements FuzzySystem {

	private Defuzzifier def;
	private DecisionMachine machine;
	private List<IFuzzySet> list;

	public SteeringFuzzySystem(Defuzzifier def, DecisionMachine machine) {
		this.def = def;
		this.machine = machine;
	}

	@Override
	public int zakljuci(int L, int D, int LK, int DK, int V, int S) {
		
		list = new ArrayList<>();
		
		rule(Rules.okretLijevo,
				Rules.kriviSmjer.getValueAt(DomainElement.of(S)), 
				1-Rules.kriticnoBlizu.getValueAt(DomainElement.of(LK))
			);
		
		rule(Rules.okretLijevo,
				Rules.kriviSmjer.getValueAt(DomainElement.of(S)), 
				1-Rules.kriticnoBlizu.getValueAt(DomainElement.of(LK))
			);
		
		rule(Rules.okretDesno,
				Rules.kriviSmjer.getValueAt(DomainElement.of(S)), 
				1-Rules.kriticnoBlizu.getValueAt(DomainElement.of(DK))
			);
		
		rule(Rules.oštroDesno,
				Rules.kriticnoBlizu.getValueAt(DomainElement.of(L)), 
				Rules.kriticnoBlizu.getValueAt(DomainElement.of(LK)),
				1-Rules.jakoDaleko.getValueAt(DomainElement.of(LK)),
				1-Rules.jakoDaleko.getValueAt(DomainElement.of(DK))
			);
		
		rule(Rules.blagoDesno,
				1-Rules.kriticnoBlizu.getValueAt(DomainElement.of(L)), 
				1-Rules.kriticnoBlizu.getValueAt(DomainElement.of(LK)),
				Rules.blizu.getValueAt(DomainElement.of(LK))
			);
		
		rule(Rules.lijevo, 
				1-Rules.kriticnoBlizu.getValueAt(DomainElement.of(D)), 
				1-Rules.kriticnoBlizu.getValueAt(DomainElement.of(DK)), 
				Rules.blizu.getValueAt(DomainElement.of(DK))
			);
		
		rule(Rules.oštroLijevo,
				Rules.kriticnoBlizu.getValueAt(DomainElement.of(D)), 
				Rules.kriticnoBlizu.getValueAt(DomainElement.of(DK)),
				1-Rules.jakoDaleko.getValueAt(DomainElement.of(LK)),
				1-Rules.jakoDaleko.getValueAt(DomainElement.of(DK))
			);
		
		rule(Rules.desno,
				Rules.daleko.getValueAt(DomainElement.of(DK)),
				1-Rules.blizu.getValueAt(DomainElement.of(LK))
			);
		
		rule(Rules.lijevo,
				Rules.daleko.getValueAt(DomainElement.of(LK)),
				1-Rules.blizu.getValueAt(DomainElement.of(DK))
			);
		
		//*******
		
//		rule(Rules.oštroDesno,
//				Rules.blizu.getValueAt(DomainElement.of(LK)),
//				1-Rules.blizu.getValueAt(DomainElement.of(DK)),
//				1-Rules.jakoDaleko.getValueAt(DomainElement.of(LK)),
//				1-Rules.jakoDaleko.getValueAt(DomainElement.of(DK))
//			);
//		
//		rule(Rules.oštroLijevo,
//				1-Rules.blizu.getValueAt(DomainElement.of(LK)),
//				Rules.blizu.getValueAt(DomainElement.of(DK)),
//				1-Rules.jakoDaleko.getValueAt(DomainElement.of(LK)),
//				1-Rules.jakoDaleko.getValueAt(DomainElement.of(DK))
//			);
		
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
}
