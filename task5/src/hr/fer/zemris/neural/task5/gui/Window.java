package hr.fer.zemris.neural.task5.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import hr.fer.zemris.neural.task5.nn.Backpropagation;
import hr.fer.zemris.neural.task5.nn.Stochastic;
import hr.fer.zemris.neural.task5.utils.Consts;
import hr.fer.zemris.neural.task5.utils.Dataset;
import hr.fer.zemris.neural.task5.utils.Dialog;
import hr.fer.zemris.neural.task5.utils.Point;
import hr.fer.zemris.neural.task5.utils.Util;

public class Window extends JFrame implements CanvasListener {
	
	private static final long serialVersionUID = 1L;
	
	private Canvas canvas;
	
	private JRadioButton train;
	
	private JRadioButton test;
	
	private Dataset dataset;
	
	private Backpropagation net;
	
	
	public Window() throws IOException {
		this.dataset = Dataset.getDataset(Consts.filename);
		this.net = new Stochastic(dataset);
		
		initGUI();
	}

	private void initGUI() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Prozor");
		setLocation(300, 200);
		setSize(800, 500);
		
		Container cp = getContentPane();
		createToolbar(cp);
		createCanvas(cp);
	}
	
	private void createToolbar(Container cp) {
		ButtonGroup group = new ButtonGroup();
		train = new JRadioButton("Trening");
		test = new JRadioButton("Test");
		group.add(train);
		group.add(test);
		train.setSelected(true);
		
		JButton trainButton = new JButton("Treniraj");
		trainButton.addActionListener(e -> train());
		
		JToolBar toolbar = new JToolBar();
		toolbar.add(trainButton);
		toolbar.add(test);
		toolbar.add(train);
		cp.add(toolbar, BorderLayout.NORTH);
	}
	
	private void createCanvas(Container cp) {
		canvas = new Canvas();
		canvas.addCanvasListener(this);
		cp.add(canvas, BorderLayout.CENTER);
	}

	@Override
	public void finishedDrawing(List<Point> reps) {
		if(train.isSelected()) {
			Letter letter = Dialog.chooseLetter();
			Gesture gesture = new Gesture(letter, reps);
			Util.writeGestureToFile(gesture);
			
		} else if (test.isSelected() && net != null) {
			List<Double> outs = net.classify(Util.pointsToDoubles(reps));
			String gesture = Util.likeliestGesture(outs);
			Dialog.guessLetter(gesture);
		}
	}
	
	private void train() {
		try {
			dataset.readFile();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		net.trainNet();
	}
	
	
	public static void main(String[] args) throws IOException {
		SwingUtilities.invokeLater(() -> {
			try {
				new Window().setVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
}
