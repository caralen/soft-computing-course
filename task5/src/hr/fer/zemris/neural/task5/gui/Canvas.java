package hr.fer.zemris.neural.task5.gui;

import static hr.fer.zemris.neural.task5.utils.Util.findRepresentatives;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import hr.fer.zemris.neural.task5.utils.Point;

public class Canvas extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final int padding = 15;

	private List<CanvasListener> listeners = new ArrayList<>();
	private List<Point> points = new ArrayList<>();
	private List<Point> reps = new ArrayList<>();
	private int counter = 0;

	public Canvas() {
		setBackground(Color.WHITE);
		addMouseListeners();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString("New gestures: " + counter, padding, padding);
		for(Point p: points) {
			g.drawRect((int)p.getX(), (int)p.getY(), 1, 1);
		}
	}

	private void addMouseListeners() {
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				points.clear();
				reps.clear();
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				reps = findRepresentatives(points, 20);
				
				counter++;
				fire();
				repaint();
			}
		});
		
		addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				points.add(new Point(e.getX(), e.getY()));
				repaint();
			}
		});
	}
	
	
	public void addCanvasListener(CanvasListener listener) {
		listeners.add(listener);
	}
	
	public void removeCanvasListener(CanvasListener listener) {
		listeners.remove(listener);
	}
	
	public void resetCounter() {
		counter = 0;
		repaint();
	}
	
	public boolean hasNew() {
		return counter == 0 ? false : true;
	}
	
	private void fire() {
		for(CanvasListener l: listeners) {
			l.finishedDrawing(reps);
		}
	}
}
