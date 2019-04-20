package hr.fer.zemris.fuzzy.task2;

public class StandardFuzzySets {

	public static IIntUnaryFunction lFunction(int alfa, int beta) {

		return new IIntUnaryFunction() {
			@Override
			public double valueAt(int index) {
				if (index < alfa)
					return 1;
				else if (index >= beta)
					return 0;
				else
					return (beta - index) / (double)(beta - alfa);
			}
		};
	}
	
	public static IIntUnaryFunction gammaFunction(int alfa, int beta) {
		
		return new IIntUnaryFunction() {
			@Override
			public double valueAt(int index) {
				if (index < alfa)
					return 0;
				else if (index >= beta)
					return 1;
				else 
					return (index - alfa) / (double)(beta - alfa);
			}
		};
	}
	
	public static IIntUnaryFunction lambdaFunction(int alfa, int beta, int gamma) {
		
		return new IIntUnaryFunction() {
			@Override
			public double valueAt(int index) {
				if (index < alfa)
					return 0;
				else if (index >= gamma)
					return 0;
				else if (index >= alfa && index < beta)
					return (index - alfa) / (double)(beta - alfa);
				else
					return (gamma - index) / (double)(gamma - beta);
			}
		};
	}
}
