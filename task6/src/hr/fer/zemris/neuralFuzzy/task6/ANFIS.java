package hr.fer.zemris.neuralFuzzy.task6;

import static java.lang.Math.pow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ANFIS {
	
	static int domainStart = -4;
	static int domainEnd = 4;
	
	static double eta1 = 0.001;
	static double eta2 = 0.00005;
	
	private List<Pattern> patterns;
	private List<Rule> rules;
	private List<Double> errors;
	
	private double maxEpoch;
	private double sumW;
	private double sumWZ; 

	public ANFIS(int maxEpoch, int numberOfRules) {
		this.maxEpoch = maxEpoch;
		errors = new ArrayList<>();
		patterns = new ArrayList<>();
		rules = new ArrayList<>();
		
		createPatterns();
		createRules(numberOfRules);
	}
	
	private void createPatterns() {
		for(int i = domainStart; i <= domainEnd; i++) {
			for(int j = domainStart; j <= domainEnd; j++) {
				patterns.add(new Pattern(i, j));
			}
		}
	}

	private void createRules(int numberOfRules) {
		for(int i = 0; i < numberOfRules; i++) {
			rules.add(new Rule());
		}
	}


	public void batch() {
		
		for (int epoch = 0; epoch <= maxEpoch; ++epoch) {
			
			for(Pattern s : patterns) {
				double o = output(s.getX(), s.getY());
				
				for(Rule r : rules) {
					double z = r.getF(s.getX(), s.getY());
					r.updateDerivs(s, o, sumW, sumW * z - sumWZ);
				}
			}
			for(Rule r: rules)
				r.updateParams(eta1, eta2);
			
			if (epoch % 1000 == 0)
				print(epoch);
		}
	}
	

	public void stohastic() {
		
		for (int epoch = 0; epoch <= maxEpoch; ++epoch) {
			
			Collections.shuffle(patterns);
			
			for(int iter = 0; iter < patterns.size(); ++iter) {
				
				Pattern currentSample = patterns.get(iter);
				double o = output(currentSample.getX(), currentSample.getY());
				
				for (Rule r : rules) {
					double z = r.getF(currentSample.getX(), currentSample.getY());
					r.updateDerivs(currentSample, o, sumW, sumW * z - sumWZ);
					r.updateParams(eta1, eta2);
				}
			}
			if (epoch % 1000 == 0)
				print(epoch);
		
		}
	}
	
	private double output(double x, double y) {
		sumWZ = 0;
		sumW = 0;
		
		for(Rule r : rules) {
			double w = r.getW(x, y);
			sumWZ += w * r.getF(x, y);
			sumW += w;
		}
		return sumWZ / sumW;
	}

	
	private double error() {
		double errorSum = 0;
		for (Pattern s : patterns)
			errorSum += pow(output(s.getX(), s.getY()) - s.getZ(), 2);
		return errorSum / patterns.size();
	}
	

	public List<Double> getErrors() {
		return errors;
	}

	private void print(int i) {
		double error = error();
		System.out.println(i + ". epoch: error = " + error);
		errors.add(error);
	}
}
