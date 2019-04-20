package hr.fer.zemris.neural.task5.utils;

import javax.swing.JOptionPane;

import hr.fer.zemris.neural.task5.gui.Letter;

public class Dialog {

	public static Letter chooseLetter() {
		String[] options = {"alpha", "beta", "gamma", "delta", "eta"};
		Letter[] letters = {Letter.ALPHA, Letter.BETA, Letter.GAMMA, Letter.DELTA, Letter.ETA};
		
        int gestureNumber = JOptionPane.showOptionDialog(
        		null, 
        		"Choose letter",
                "Letter",
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                options, 
                options[0]);
        
		return letters[gestureNumber];
	}
	
	public static void guessLetter(String letter) {
		JOptionPane.showMessageDialog(null, "My guess is: " + letter, "Letter", JOptionPane.INFORMATION_MESSAGE);
	}
}
