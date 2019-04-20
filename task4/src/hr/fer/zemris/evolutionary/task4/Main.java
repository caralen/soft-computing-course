package hr.fer.zemris.evolutionary.task4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hr.fer.zemris.evolutionary.task4.algorithms.GenerationGA;
import hr.fer.zemris.evolutionary.task4.algorithms.GeneticAlgorithm;
import hr.fer.zemris.evolutionary.task4.system.CharacteristicFunctions;
import hr.fer.zemris.evolutionary.task4.system.Function;

public class Main {
	
	private static final String filename1 = "./resources/zad4-dataset1.txt";
	private static final String filename2 = "./resources/zad4-dataset2.txt";
	
	private static final int iterations = 100;
	private static final int population_size = 100;
	private static final double mutation_probability = 0.05;
	private static final int parameters_number = 5;
	private static final int lower_boundary = -4;
	private static final int upper_boundary = 4;
	
	private static Function f = CharacteristicFunctions.f;
	private static List<FunctionValues> values;

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		
		Path file1 = Paths.get(filename1);
		Path file2 = Paths.get(filename2);
		
		List<String> list1 = Files.readAllLines(file1);
		List<String> list2 = Files.readAllLines(file2);
		values = new ArrayList<>();
		
		parseFile(list1);
		
		List<Unit> population = new ArrayList<>();
		generateRandomPopulation(population);
		
		for(Unit unit : population) {
			calculateError(unit);
		}
		
		GeneticAlgorithm ga = new GenerationGA(true, mutation_probability);
//		GeneticAlgorithm ga = new EliminationGA(mutation_probability);
		for(int i = 0; i < iterations; i++) {
			Unit result = ga.iteration(population);
			System.out.println(result.getError());
		}
	}

	private static void parseFile(List<String> lines) {
		for(String line : lines) {
			String[] parts = line.split("\t");
			double x = Double.parseDouble(parts[0]);
			double y = Double.parseDouble(parts[1]);
			double out = Double.parseDouble(parts[2]);
			values.add(new FunctionValues(x, y, out));
		}
	}

	private static void generateRandomPopulation(List<Unit> population) {
		Random rand = new Random();
		for(int i = 0; i < population_size; i++) {
			
			double[] arr = new double[parameters_number];
			for(int j = 0; j < parameters_number; j++) {
				arr[j] = rand.nextDouble() * upper_boundary - lower_boundary;
				System.out.println(arr[j]);
			}
			population.add(new Unit(arr));
		}
	}

	public static void calculateError(Unit unit) {
		double sum = 0;
		for(FunctionValues v : values) {
			sum += Math.pow(f.evaluate(v.getX(), v.getY(), unit.getParameters()), 2);
		}
		unit.setError(sum / values.size()); 
	}
}
