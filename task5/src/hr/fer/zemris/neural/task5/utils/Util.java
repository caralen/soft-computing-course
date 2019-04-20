package hr.fer.zemris.neural.task5.utils;

import static java.lang.Math.abs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.zemris.neural.task5.gui.Gesture;
import hr.fer.zemris.neural.task5.gui.Letter;

public class Util {
	
	public static List<Point> findRepresentatives(List<Point> points, int M) {
		subtractMean(points);
		scale(points);
		double length = calculateLength(points);
		
		if(points.size() < M) throw new IllegalArgumentException("Should be more points");
		List<Point> reps = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			double factor = i * length / M - 1;
			double x = points.get(0).getX();
			double y = points.get(0).getY();
			Point closestPoint = findClosest(points, new Point(x + factor, y + factor));
			reps.add(closestPoint);
		}
		return reps;
	}

	private static double calculateLength(List<Point> points) {
		if(points.size() == 0 || points.size() == 1) return 0;
		double length = 0;

		for(int i = 1; i < points.size(); i++) {
			length += points.get(i-1).distance(points.get(i));
		}
		return length;
	}

	private static void scale(List<Point> points) {
		double max = 0;
		for(Point p: points) {
			max = max < abs(p.getX()) ? abs(p.getX()) : max;
			max = max < abs(p.getY()) ? abs(p.getY()) : max;
		}
		
		for(Point p: points) {
			p.scale(1 / max);
		}
	}
	
	public static Point findClosest(List<Point> points, Point point) {
		double minDistance = Double.MAX_VALUE;
		int index = 0;
		
		for(Point p: points) {
			double dist = point.distance(p);
			if(dist < minDistance) {
				minDistance = dist;
				index = points.indexOf(p);
			}
		}
		return points.get(index);
	}
	
	private static void subtractMean(List<Point> points) {
		double sumX = 0, sumY = 0;
		for(Point p: points) {
			sumX += p.getX();
			sumY += p.getY();
		}
		double averageX = sumX / points.size();
		double averageY = sumY / points.size();
		Point mean = new Point(averageX, averageY);
		
		for(Point p: points) {
			p.subtract(mean);
		}
	}
	
	private static String gestureToString(Letter g) {
		if(g == Letter.ALPHA) return "1,0,0,0,0";
		if(g == Letter.BETA) return "0,1,0,0,0";
		if(g == Letter.GAMMA) return "0,0,1,0,0";
		if(g == Letter.DELTA) return "0,0,0,1,0";
		if(g == Letter.ETA) return "0,0,0,0,1";
		return "";
	}
	
	public static String likeliestGesture(List<Double> outs) {
		if(outs.size() != 5) throw new IllegalArgumentException("Invalid number of outputs on the last layer.");
		String[] gestures = {"alpha", "beta", "gamma", "delta", "eta"};
		return gestures[outs.indexOf(Collections.max(outs))];
	}
	
	public static void writeGestureToFile(Gesture gesture) {
		
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(Consts.filename, true)))) {
			
			StringBuilder sb = new StringBuilder();
			for(Point p: gesture.getPoints()) {
				sb.append(p);
				sb.append(",");
			}
			sb.setLength(sb.length()-1);
			sb.append("\t");
			sb.append(gestureToString(gesture.getLetter()));
			out.println(sb.toString());
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static double sigmoid(double x) {
		return (1 / (1 + Math.exp(-x)));
	}
	
	public static List<Double> pointsToDoubles(List<Point> points) {
		List<Double> values = new ArrayList<>();
		
		for(Point p : points) {
			values.add(p.getX());
			values.add(p.getY());
		}
		return values;
	}
	
	public static List<Double> arrayToList(String[] arr){
		List<Double> list = new ArrayList<>();
		for(int i = 0; i < arr.length; i++) {
			list.add(Double.parseDouble(arr[i]));
		}
		return list;
	}
	
	public static double[] listToArray(List<Double> list){
		double[] arr = new double[list.size()];
		for(int i = 0; i < list.size(); i++) {
			arr[i] = list.get(i);
		}
		return arr;
	}
}
