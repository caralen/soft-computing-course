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
import hr.fer.zemris.fuzzy.task3.systems.SteeringFuzzySystem;

public class Simulator {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		Defuzzifier def = new COADefuzzifier();
		DecisionMachine machine = new MinimumBasedMachine();
		
		FuzzySystem fsAkcel = new AccelerationFuzzySystem(def, machine);
		FuzzySystem fsKormilo = new SteeringFuzzySystem(def, machine);
		
		int L = 0, D = 0, LK = 0, DK = 0, V = 0, S = 0;
	    String line = null;
	    
		while(true) {
			if((line = input.readLine())!=null){
				if(line.charAt(0)=='K') break;
				Scanner sc = new Scanner(line);
				L = sc.nextInt();
				D = sc.nextInt();
				LK = sc.nextInt();
				DK = sc.nextInt();
				V = sc.nextInt();
				S = sc.nextInt();
	        }
			
			int A = fsAkcel.zakljuci(L, D, LK, DK, V, S);
			int K = fsKormilo.zakljuci(L, D, LK, DK, V, S);
			
			
			System.out.println(A + " " + K);
			System.out.flush();
		}
		
	}
}
