package hr.fer.zemris.neuralFuzzy.task6;

public class Main {
	
	public static void main(String[] args) {
		ANFIS anfis = new ANFIS(20_000, 5);
		anfis.batch();
	}

}
