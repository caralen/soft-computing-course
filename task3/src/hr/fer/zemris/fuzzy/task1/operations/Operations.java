package hr.fer.zemris.fuzzy.task1.operations;

import java.util.Objects;

import hr.fer.zemris.fuzzy.task1.domains.DomainElement;
import hr.fer.zemris.fuzzy.task1.sets.IFuzzySet;
import hr.fer.zemris.fuzzy.task1.sets.MutableFuzzySet;

public class Operations {

	private static IBinaryFunction ZADEH_AND;
	private static IBinaryFunction ZADEH_OR;
	private static IUnaryFunction ZADEH_NOT;
	
	public static IFuzzySet unaryOperation(IFuzzySet set, IUnaryFunction function) {
		Objects.requireNonNull(set);
		Objects.requireNonNull(function);
		
		MutableFuzzySet mutableSet = new MutableFuzzySet(set.getDomain());
		for(DomainElement element : set.getDomain()) {
			mutableSet.set(element, function.valueAt(set.getValueAt(element)));
		}
		return mutableSet;
	}
	
	public static IFuzzySet binaryOperation(IFuzzySet set1, IFuzzySet set2, IBinaryFunction function) {
		Objects.requireNonNull(set1);
		Objects.requireNonNull(set2);
		Objects.requireNonNull(function);
		
		MutableFuzzySet mutableSet = new MutableFuzzySet(set1.getDomain());
		for(DomainElement element : set1.getDomain()) {
			mutableSet.set(element, function.valueAt(set1.getValueAt(element), set2.getValueAt(element)));
		}
		return mutableSet;
	}
	
	public static IUnaryFunction zadehNot() {
		if(ZADEH_NOT == null) {
			ZADEH_NOT = new IUnaryFunction() {
				
				@Override
				public double valueAt(double value) {
					return 1 - value;
				}
			};
		}
		return ZADEH_NOT;
	}
	
	public static IBinaryFunction zadehAnd() {
		if(ZADEH_AND == null) {
			ZADEH_AND = new IBinaryFunction() {
				
				@Override
				public double valueAt(double first, double second) {
					return Math.min(first, second);
				}
			};
		}
		return ZADEH_AND;
	}
	
	public static IBinaryFunction zadehOr() {
		if(ZADEH_OR == null) {
			ZADEH_OR = new IBinaryFunction() {
				
				@Override
				public double valueAt(double first, double second) {
					return Math.max(first, second);
				}
			};
		}
		return ZADEH_OR;
	}
	
	public static IBinaryFunction algebraicProduct() {
		return new IBinaryFunction() {
				
				@Override
				public double valueAt(double first, double second) {
					return first * second;
				}
		};
	}
	
	public static IBinaryFunction hamacherTNorm(double parameter) {
		return new IBinaryFunction() {
			
			@Override
			public double valueAt(double a, double b) {
				return (a * b) / (double)(parameter + (1 - parameter) * (a + b - a * b));
			}
		};
	}
	
	public static IBinaryFunction hamacherSNorm(double parameter) {
		return new IBinaryFunction() {
			
			@Override
			public double valueAt(double a, double b) {
				return (a + b - (2 - parameter) * a * b) / (double)(1 - (1 - parameter) * a * b);
			}
		};	
	}
}
