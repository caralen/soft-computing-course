package hr.fer.zemris.neural.task5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws IOException {
		if(args.length != 4) {
			throw new IllegalArgumentException("Invalid number of arguments, should be 4.");
		}
		
		String file = args[0];
		if(!Files.isRegularFile(Paths.get(file))) throw new IllegalArgumentException("Invalid path given.");
		
		int M = Integer.parseInt(args[1]);
		
		String architecture = args[2];
		String[] parts = architecture.split("x");
		List<Integer> layersSize = new ArrayList<>();
		for(String part : parts) {
			layersSize.add(Integer.parseInt(part));
		}
		if(layersSize.get(0) != 2*M) throw new IllegalArgumentException("Input layer must be of size 2*M");
		if(layersSize.get(layersSize.size()-1) != 5) throw new IllegalArgumentException("Output layer must be of size 5");
		
		int algorithm = Integer.parseInt(args[3]);
		if(algorithm < 1 || algorithm  > 3) throw new IllegalArgumentException("Invalid algorithm selected.");
		
//		List<String> lines = Files.readAllLines(Paths.get(file));
		
//		Backpropagation net = new Batch(new NeuralNet(layersSize));
//		net.trainNet(lines);
//		System.out.println(net.classify(inputs));
	}
	
}
