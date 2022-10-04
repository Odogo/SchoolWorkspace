package junior.september;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class FiveZeroSixTengo {
	
	public static class Player {
		private String name;
		private OffenseStats offense;
		private DefenseStats defense;
		
		public Player(String name, OffenseStats offense, DefenseStats defense) {
			this.name = name;
			this.offense = offense;
			this.defense = defense;
		}
		
		@Override public int hashCode() { return Objects.hash(defense, name, offense); }

		@Override public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			Player other = (Player) obj;
			
			return Objects.equals(defense, other.defense) &&
					Objects.equals(name, other.name) && 
					Objects.equals(offense, other.offense);
		}

		public String fetchName() { return name; }
		public OffenseStats fetchStatsOffense() { return offense; }
		public DefenseStats fetchStatsDefense() { return defense; }
	}
	
	public static class OffenseStats {
		private int atBats, walks, 
			hitByPitches,
			sacrifices, hits,
			singles, doubles, 
			triples, homeRuns;

		public OffenseStats(int atBats, int walks, int hitByPitches, int sacrifices,
				int hits, int singles, int doubles, int triples, int homeRuns) {
			this.atBats = atBats;
			this.walks = walks;
			this.hitByPitches = hitByPitches;
			this.sacrifices = sacrifices;
			this.hits = hits;
			this.singles = singles;
			this.doubles = doubles;
			this.triples = triples;
			this.homeRuns = homeRuns;
		}
		
		@Override public int hashCode() { return Objects.hash(atBats, doubles, hitByPitches, hits, homeRuns, sacrifices, singles, triples, walks); }

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			
			OffenseStats other = (OffenseStats) obj;
			return atBats == other.atBats && doubles == other.doubles && hitByPitches == other.hitByPitches
					&& hits == other.hits && homeRuns == other.homeRuns && sacrifices == other.sacrifices
					&& singles == other.singles && triples == other.triples && walks == other.walks;
		}

		public int fetchAtBats() { return atBats; }
		public int fetchWalks() { return walks; }
		public int fetchHitByPitches() { return hitByPitches; }
		public int fetchSacrifices() { return sacrifices; }
		public int fetchHits() { return hits; }
		public int fetchSingles() { return singles; }
		public int fetchDoubles() { return doubles; }
		public int fetchTriples() { return triples; }
		public int fetchHomeRuns() { return homeRuns; }
		
		public double fetchAverage() { return (double) fetchHits() / fetchAtBats(); }
		
		public double fetchSlugging() {
			// Calculate total number of bases
			int first = singles, second = doubles * 2, third = triples * 3, fourth = homeRuns * 4;
			int total = first + second + third + fourth;
			return (double) total / atBats;
		}
		
		public double fetchOnBasePercent() {
			int top = hits + walks + hitByPitches;
			int bottom = atBats + walks + hitByPitches + sacrifices;
			return (double) top / bottom;
		}
	}
	
	public static class DefenseStats {
		private int assists, putouts, errors;
		
		public DefenseStats(int assists, int putouts, int errors) {
			this.assists = assists;
			this.putouts = putouts;
			this.errors = errors;
		}
		
		@Override public int hashCode() { return Objects.hash(assists, errors, putouts); }

		@Override public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			DefenseStats other = (DefenseStats) obj;
			return assists == other.assists && errors == other.errors && putouts == other.putouts;
		}

		public int fetchAssists() { return assists; }
		public int fetchPutouts() { return putouts; }
		public int fetchErrors() { return errors; }
		
		public double fetchFieldingPercent() {
			return (double) (assists + putouts) / (assists + putouts + errors);
		}
	}

	public static void main(String[] args) throws IllegalAccessException, FileNotFoundException {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		
		File selection = chooser.getSelectedFile();
		if(selection == null)
			throw new FileNotFoundException("File from selection could not be found.");
		if(selection.isDirectory())
			throw new IllegalAccessException("Cannot access a directory as a file.");
	
		List<FiveZeroSixTengo.Player> players = new ArrayList<FiveZeroSixTengo.Player>();
		try(Scanner scanner = new Scanner(selection)) {
			
			// we do not read a line until in the loop.
			while(scanner.hasNextLine()) {
				// this resets the line to become the next line after everything is done
				// we do this at the start to make sure there is a line in the while loop.
				String line = scanner.nextLine();
				String[] split = line.split(" ");
				
				String name = split[0] + " " + split[1];
				OffenseStats oStats = new OffenseStats(Integer.parseInt(split[2]), Integer.parseInt(split[3]), 
						Integer.parseInt(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[6]), 
						Integer.parseInt(split[7]), Integer.parseInt(split[8]), Integer.parseInt(split[9]),
						Integer.parseInt(split[10]));
				DefenseStats dStats = new DefenseStats(Integer.parseInt(split[11]), Integer.parseInt(split[12]), Integer.parseInt(split[13]));
				
				players.add(new Player(name, oStats, dStats));
			}
		} catch (NoSuchElementException e) {
			System.err.println("Caught exception: Unexpected end of data file, program will continue running.");
			System.err.println("Given stack trace [CAN BE IGNORED]: ");
			e.printStackTrace();
		}
		
		DecimalFormat percentFormat = new DecimalFormat("0.00%");
		
		System.out.println("The player with the most hits: " + fetchHighestHitter(players).fetchName());
		
		Player[] highest = fetchTwoHighestAverages(players);
		System.out.println("Two highest hitters: " + highest[0].fetchName() + " and " + highest[1].fetchName());
		
		Player[] lowest = fetchLowestFielding(players);
		System.out.println("Two lowest fielders: " + lowest[0].fetchName() + " and " + lowest[1].fetchName());
	
		List<Player> clone = new ArrayList<FiveZeroSixTengo.Player>(players);
		clone.sort(new Comparator<FiveZeroSixTengo.Player>() {
			@Override public int compare(Player o1, Player o2) { return Double.compare(o1.fetchStatsOffense().fetchOnBasePercent(), o2.fetchStatsOffense().fetchOnBasePercent()); }
		});
		System.out.println("\n-- Sorted list by on base % [low -> high] --");
		for(int i=0; i<clone.size(); i++) {
			System.out.println(clone.get(i).fetchName() + " with " + percentFormat.format(clone.get(i).fetchStatsOffense().fetchOnBasePercent()));
		}
		System.out.println("-- Sorted list by on base % [high <- low] --\n");

		
		double slugPercent = 0;
		for(Player player : players) slugPercent += player.fetchStatsOffense().fetchSlugging();
		slugPercent /= players.size();
		System.out.println("Teams slugging average: " + percentFormat.format(slugPercent));
	
		int hits = 0;
		for(Player player : players) hits += player.fetchStatsOffense().fetchHits();
		System.out.println("Total hits: " + hits);
		
		Player player = fetchClosestAverage(players);
		System.out.println("Closest fielding average " + player.fetchName() + " with " + percentFormat.format(player.fetchStatsDefense().fetchFieldingPercent()));
		System.out.println("Compared with: [Team Avg] " + percentFormat.format(fetchFieldingAvg(players)));
	}
	
	private static Player fetchHighestHitter(List<Player> players) {
		Player high = null;
		for(int i=0;i<players.size(); i++) {
			if(high == null) { high = players.get(i); continue; }
			high = (players.get(i).fetchStatsOffense().fetchHits() > high.fetchStatsOffense().fetchHits() ? players.get(i): high);
		}
		return high;
	}
	
	private static Player[] fetchTwoHighestAverages(List<Player> players) {
		Player highest = null;
		for(Player player : players) {
			if(highest == null) { highest = player; continue; }
			highest = (player.fetchStatsOffense().fetchAverage() > highest.fetchStatsOffense().fetchAverage() ? player: highest);
		}
		
		Player secondHigh = null;
		for(Player player : players) {
			if(highest.equals(player)) { continue; }
			if(secondHigh == null) { secondHigh = player; continue; }
			secondHigh = (player.fetchStatsOffense().fetchAverage() > secondHigh.fetchStatsOffense().fetchAverage() ? player: secondHigh);
		}
		
		return new Player[] { highest, secondHigh };
	}
	
	private static Player[] fetchLowestFielding(List<Player> players) {
		Player lowest = null;
		for(Player player : players) {
			if(lowest == null) { lowest = player; continue; }
			lowest = (player.fetchStatsOffense().fetchAverage() < lowest.fetchStatsOffense().fetchAverage() ? player: lowest);
		}
		
		Player secondLow = null;
		for(Player player : players) {
			if(lowest.equals(player)) { continue; }
			if(secondLow == null) { secondLow = player; continue; }
			secondLow = (player.fetchStatsOffense().fetchAverage() > secondLow.fetchStatsOffense().fetchAverage() ? player: secondLow);
		}
		
		return new Player[] { lowest, secondLow }; 
	}
	
	private static double fetchFieldingAvg(List<Player> players) {
		double avg = 0;
		for(Player player : players) avg += player.fetchStatsDefense().fetchFieldingPercent();
		avg /= players.size();
		return avg;
	}
	
	private static Player fetchClosestAverage(List<Player> players) {
		double avg = fetchFieldingAvg(players);
		
		double difference = 0;
		Player holder = null;
		for(Player player : players) {
			if(holder == null) {
				holder = player;
				difference = Math.abs((avg - player.fetchStatsDefense().fetchFieldingPercent()));
				continue;
			}
			
			if(Math.min(difference, Math.abs(avg - player.fetchStatsDefense().fetchFieldingPercent())) == Math.abs(avg - player.fetchStatsDefense().fetchFieldingPercent())) {
				holder = player;
				difference = Math.abs((avg - player.fetchStatsDefense().fetchFieldingPercent()));
			}
		}
		
		return holder;
	}
}
