package hr.fer.zemris.neural.task5.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Dataset {
	
	private static Dataset reference;
	private List<Sample> samples;
	private List<Sample> outputs;
	private String filename;
	private Sample testSample;
	


	private Dataset(String filename) throws IOException {
		samples = new ArrayList<>();
		outputs = new ArrayList<>();
		this.filename = filename;
		readFile();
	}
	
	
	public static Dataset getDataset(String filename) throws IOException {
		if(reference == null) 
			reference = new Dataset(filename);
		return reference;
	}

	public double[] getTestSample() {
		return testSample.getArray();
	}
	
	public void setTestSample(double[] sampleArr) {
		this.testSample = new Sample(sampleArr);
	}
	
	public double[] getSample(int index) {
		return samples.get(index).getArray();
	}
	
	public double[] getOutput(int index) {
		return outputs.get(index).getArray();
	}
	
	public int size() {
		return samples.size();
	}
	
	
	public void readFile() throws IOException {
		samples.clear();
		outputs.clear();
		
		List<String> lines = Files.readAllLines(Paths.get(filename));
		
		for(String line : lines) {
			String[] parts = line.split("\t");
			String[] ins = parts[0].split(",");
			String[] outs = parts[1].split(",");
			
			double[] input = new double[ins.length];
			double[] output = new double[outs.length];
			
			for(int i = 0; i < ins.length; i++) {
				input[i] = Double.parseDouble(ins[i]);
			}
			for(int i = 0; i < outs.length; i++) {
				output[i] = Double.parseDouble(outs[i]);
			}
			samples.add(new Sample(input));
			outputs.add(new Sample(output));
		}
	}
}
