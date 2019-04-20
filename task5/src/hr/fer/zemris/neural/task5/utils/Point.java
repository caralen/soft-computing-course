package hr.fer.zemris.neural.task5.utils;

public class Point {

	private double x;
	private double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void add(Point other) {
		this.x += other.x;
		this.y += other.y;
	}
	
	public void subtract(Point other) {
		this.x -= other.x;
		this.y -= other.y;
	}
	
	public void scale(double scaler) {
		x *= scaler;
		y *= scaler;
	}
	
	public double distance(Point other) {
		return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
	}

	@Override
	public String toString() {
		return x + "," + y;
	}
	
}
