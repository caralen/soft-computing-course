package hr.fer.zemris.fuzzy.task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import hr.fer.zemris.fuzzy.task3.defuzzify.COADefuzzifier;
import hr.fer.zemris.fuzzy.task3.defuzzify.Defuzzifier;
import hr.fer.zemris.fuzzy.task3.machine.DecisionMachine;
import hr.fer.zemris.fuzzy.task3.machine.MinimumBasedMachine;
import hr.fer.zemris.fuzzy.task3.systems.AccelerationFuzzySystem;

public class Main1 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		Defuzzifier def = new COADefuzzifier();
		DecisionMachine machine = new MinimumBasedMachine();
		
		AccelerationFuzzySystem fsAkcel = new AccelerationFuzzySystem(def, machine);
		
		int L = 0, D = 0, LK = 0, DK = 0, V = 0, S = 0;
	    String line = null;
	    
	    if((line = input.readLine())!=null){
	    	
			Scanner sc = new Scanner(line);
			L = sc.nextInt();
			D = sc.nextInt();
			LK = sc.nextInt();
			DK = sc.nextInt();
			V = sc.nextInt();
			S = sc.nextInt();
			
			
			sc.close();
        }
		fsAkcel.zakljuci(L, D, LK, DK, V, S);
		fsAkcel.print();
	}
}
