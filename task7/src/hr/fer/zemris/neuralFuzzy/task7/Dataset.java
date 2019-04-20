package hr.fer.zemris.neuralFuzzy.task7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Dataset {
	
	private static Dataset reference;
	private List<Sample> samples;
	private String filename;
	
	private Dataset(String filename) throws IOException {
		samples = new ArrayList<>();
		this.filename = filename;
		readFile();
	}
	
	
	public static Dataset getDataset(String filename) throws IOException {
		if(reference == null) 
			reference = new Dataset(filename);
		return reference;
	}

	
	public double[] getSample(int index) {
		return samples.get(index).getArray();
	}
	
	public int size() {
		return samples.size();
	}
	
	
	private void readFile() throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(filename));
		
		for(String line : lines) {
			String[] parts = line.split("\\s");
			double[] arr = new double[parts.length];
			
			for(int i = 0; i < parts.length; i++) {
				arr[i] = Double.parseDouble(parts[i]);
			}
			samples.add(new Sample(arr));
		}
	}
}
