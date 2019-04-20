package hr.fer.zemris.neural.task5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hr.fer.zemris.neural.task5.nn.Backpropagation;
import hr.fer.zemris.neural.task5.nn.Batch;
import hr.fer.zemris.neural.task5.utils.Dataset;

public class Demo {
	
	private static final String file = "./resources/quad.txt";

	public static void main(String[] args) throws IOException {
		Dataset dataset = Dataset.getDataset(file);
		Backpropagation net = new Batch(dataset);
		
		net.trainNet();
		
		List<Double> inputs;
		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.println("Upisi broj:");
			String line = sc.nextLine();
			if(line.equalsIgnoreCase("EXIT")) break;
			
			inputs = new ArrayList<>();
			inputs.add(Double.parseDouble(line));
			System.out.println(net.classify(inputs));
		}
		sc.close();
	}

}
