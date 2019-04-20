package hr.fer.zemris.neural.task5.gui;

import java.util.List;

import hr.fer.zemris.neural.task5.utils.Point;

public class Gesture {

	private Letter letter;
	private List<Point> points;
	
	public Gesture(Letter letter, List<Point> points) {
		this.letter = letter;
		this.points = points;
	}

	public Letter getLetter() {
		return letter;
	}

	public List<Point> getPoints() {
		return points;
	}

}
