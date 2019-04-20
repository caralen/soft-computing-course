package hr.fer.zemris.neuralFuzzy.task6;

import static java.lang.Math.exp;

import java.util.Random;
import java.util.function.BiFunction;

public class Rule {
	
	private static BiFunction<Double, Double, Double> tNorm = (e1, e2) -> e1 * e2;

	private double a, b, c, d;
	private double p, q, r;
	private double da, db, dc, dd;
	private double dp, dq, dr;
	
	
	public Rule() {
		Random rand = new Random();
		
		a = rand.nextDouble()*2 - 1;
		b = rand.nextDouble()*2 - 1;
		c = rand.nextDouble()*2 - 1;
		d = rand.nextDouble()*2 - 1;
		p = rand.nextDouble()*2 - 1;
		q = rand.nextDouble()*2 - 1;
		r = rand.nextDouble()*2 - 1;
	}
	
	public double getF(double x, double y) {
		return p * x + q * y + r;
	}
	

	public double getW(double x, double y) {
		return tNorm.apply(membership(a, b, x), membership(c, d, y));
	}
	
	
	public void updateParams(double eta1, double eta2) {
		a += eta1 * da;
		b += eta1 * db; 
		c += eta1 * dc; 
		d += eta1 * dd;
		
		p += eta2 * dp; 
		q += eta2 * dq; 
		r += eta2 * dr;
		
		clearDerivs();
	}
	
	public void updateDerivs(Pattern sample, double o, double sumW, double sumWZ) {
		double x = sample.getX();
		double y = sample.getY();
		double z = sample.getZ();
		
		double alpha = membership(a, b, x);
		double beta = membership(c, d, y);
		double w = getW(x, y);
		
		double dif = z - o;
		
		da += dif * sumWZ / (sumW * sumW) * beta * b * alpha * (1 - alpha); 
		db += dif * sumWZ / (sumW * sumW) * beta * (a - x) * alpha * (1 - alpha); 
		dc += dif * sumWZ / (sumW * sumW) * alpha * d * beta * (1 - beta); 
		dd += dif * sumWZ / (sumW * sumW) * alpha * (c - y) * beta * (1 - beta);
		
		dp += dif * w / sumW * x; 
		dq += dif * w / sumW * y; 
		dr += dif * w / sumW;
	}
	
	private double membership(double a, double b, double x) {
		return 1.0 / (1 + exp(b * (x - a)));
	}
	
	private void clearDerivs() {
		da = db = dc = dd = dp = dq = dr = 0;
	}
}
