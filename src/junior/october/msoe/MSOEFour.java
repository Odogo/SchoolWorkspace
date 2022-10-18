package junior.october.msoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MSOEFour {
	
	public static class SimulationResult {
		private boolean didWin;
		private int flipsLoss, flipsWon;
		
		public SimulationResult(boolean didWin, int flipsLoss, int flipsWon) {
			this.didWin = didWin;
			this.flipsLoss = flipsLoss;
			this.flipsWon = flipsWon;
		}
		
		public boolean didWin() { return didWin; }
		public int getFlipsLoss() { return flipsLoss; }
		public int getFlipsWon() { return flipsWon; }
	}
	
	public static class Simulator {
		
		private List<SimulationResult> results;
		public List<SimulationResult> getResults() { return results; }
		
		public Simulator() { results = new ArrayList<SimulationResult>(); }
		
		// if heads = true, the user picks "heads" as their selection (or > 0.5)
		// if heads = false, the user picks "tails" as their selection (or < 0.5)
		public void runSimulatorOnce(boolean heads) {
			System.out.println("[SIM] Starting simulation " + (results.size() + 1) + " now");
			long start = System.currentTimeMillis();
			
			int moneyA = 10, moneyB = 10;
			
			int flipsWon = 0, flipsLoss = 0;
			while(moneyA < 20 || moneyB < 20) {
				long flip = Math.round(Math.random());
				if((flip == 1 && heads) || (flip == 0 && !heads)) { 
					flipsWon++;
					moneyA++;
					moneyB--;
				} else {
					flipsLoss++;
					moneyA--;
					moneyB++;
				}
			}
			
			// we assume it's done
			long end = System.currentTimeMillis();
			SimulationResult result = new SimulationResult((moneyA >= 20), flipsLoss, flipsWon);
			results.add(result);
			
			System.out.println("[SIM] Completed simulation " + (results.size()) + " in " + (end - start) + "ms");
		}
	}
	
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			
			System.out.print("Enter simulations: ");
			int simulations = Integer.parseInt(scanner.nextLine());
			
			
		} catch (NumberFormatException e) {
			System.err.println("Caught exception: Attempted to parse a number from a string and failed.");
		}
	}
}
