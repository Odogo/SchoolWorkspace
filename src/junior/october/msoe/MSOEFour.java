package junior.october.msoe;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Enter simulations: 2500
-- Statistics --
Simulation took: 25ms
Total took: 26ms

Loss Majority? Yes [1246.0 vs 1254.0]
% won: 49.84%
% loss: 50.16%
Avg. Flips to End: 45.0
 */
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
			int moneyA = 10, moneyB = 10;
			
			int flipsWon = 0, flipsLoss = 0;
			while(!(moneyA == 20 || moneyB == 20)) {
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
			SimulationResult result = new SimulationResult((moneyA >= 20), flipsLoss, flipsWon);
			results.add(result);
		}
	}
	
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			
			System.out.print("Enter simulations: ");
			int simulations = Integer.parseInt(scanner.nextLine());
			
			long start = System.currentTimeMillis();
			Simulator sim = new Simulator();
			for(int i=0; i<simulations; i++) {
				boolean heads = (Math.round(Math.random()) == 1 ? false: true);
				sim.runSimulatorOnce(heads);
			}
			
			long endSims = System.currentTimeMillis();
			
			double won = 0, loss = 0, avgFlips = 0;
			for(int i=0; i<sim.getResults().size(); i++) {
				SimulationResult result = sim.getResults().get(i);
				
				if(result.didWin()) {
					won++;
					avgFlips += result.getFlipsLoss() + result.getFlipsLoss();
				} else {
					loss++;
				}
			}
			
			long end = System.currentTimeMillis();
			
			DecimalFormat format = new DecimalFormat("0.00%");
			System.out.println("-- Statistics --");
			System.out.println("Simulation took: " + (endSims - start) + "ms");
			System.out.println("Total took: " + (end - start) + "ms\n");
			System.out.println("Loss Majority? " + (loss > won ? "Yes": "No") + " [" + won + " vs " + loss + "]");
			System.out.println("% won: " + format.format(won / sim.getResults().size()));
			System.out.println("% loss: " + format.format(loss / sim.getResults().size()));
			System.out.println("Avg. Flips to End: " + Math.ceil(avgFlips / sim.getResults().size()));
		} catch (NumberFormatException e) {
			System.err.println("Caught exception: Attempted to parse a number from a string and failed.");
		}
	}
}
