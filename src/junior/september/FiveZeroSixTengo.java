package junior.september;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import junior.global.SoftballClasses.DefenseStats;
import junior.global.SoftballClasses.OffenseStats;
import junior.global.SoftballClasses.Player;

/*
The player with the most hits: Nariyah Bolly

Two highest hitters: Nariyah Bolly and Kennedi Keller

Two lowest fielders: Ava Nelson and Nariyah Bolly

-- Sorted list by on base % [low -> high] --
Addison Minich with 27.06%
Ava Nelson with 27.17%
Alyssa Zimmermann with 28.43%
Emily Ayers with 28.70%
Lilly Rogula with 32.22%
Kaelyn Pryne with 32.26%
Hannah Reed with 33.33%
Gentry Pel with 34.62%
Khushi Lot with 36.08%
Karlie Patel with 37.14%
Kennedi Keller with 38.64%
Nariyah Bolly with 39.78%
-- Sorted list by on base % [high <- low] --

Teams slugging average: 41.86%

Total hits: 282

Closest fielding average Addison Minich with 91.67%
Compared with: [Team Avg] 92.44%

Would you like to see the [1] least of or [2] most of? 1
Would you like to see [1] singles, [2] doubles, [3] triples, [4] home runs? 1
Viewing the list of players with the least amount of singles
Ava Nelson - 10
Alyssa Zimmermann - 13
Emily Ayers - 15
Addison Minich - 15
Kaelyn Pryne - 15
Kennedi Keller - 16
Khushi Lot - 18
Hannah Reed - 18
Lilly Rogula - 18
Karlie Patel - 20
Nariyah Bolly - 20
Gentry Pel - 20

-- Sorted list by on base % [low -> high] --
Addison Minich with 27.06%
Ava Nelson with 27.17%
Alyssa Zimmermann with 28.43%
Emily Ayers with 28.70%
Jordan Guzman with 30.00%
Lilly Rogula with 32.22%
Kaelyn Pryne with 32.26%
Hannah Reed with 33.33%
Gentry Pel with 34.62%
Khushi Lot with 36.08%
Karlie Patel with 37.14%
Kennedi Keller with 38.64%
Nariyah Bolly with 39.78%
-- Sorted list by on base % [high <- low] --

Teams slugging average: 42.38%

Total hits: 309
 */
public class FiveZeroSixTengo {

	public static void main(String[] args) throws IllegalAccessException, FileNotFoundException {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);

		File selection = chooser.getSelectedFile();
		if(selection == null)
			throw new FileNotFoundException("File from selection could not be found.");
		if(selection.isDirectory())
			throw new IllegalAccessException("Cannot access a directory as a file.");

		List<Player> players = new ArrayList<Player>();
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

		List<Player> clone = new ArrayList<Player>(players);
		clone.sort(new Comparator<Player>() {
			@Override public int compare(Player o1, Player o2) { return Double.compare(o1.fetchStatsOffense().fetchOnBasePercent(), o2.fetchStatsOffense().fetchOnBasePercent()); }
		});
		System.out.println("\n-- Sorted list by on base % [low -> high] --");
		for(int i=0; i<clone.size(); i++)
			System.out.println(clone.get(i).fetchName() + " with " + percentFormat.format(clone.get(i).fetchStatsOffense().fetchOnBasePercent()));
		System.out.println("-- Sorted list by on base % [high <- low] --\n");


		double slugPercent = 0;
		for(Player player : players) slugPercent += player.fetchStatsOffense().fetchSlugging();
		slugPercent /= players.size();
		System.out.println("Teams slugging average: " + percentFormat.format(slugPercent));

		int hits = 0;
		for(Player player : players) hits += player.fetchStatsOffense().fetchHits();
		System.out.println("Total hits: " + hits);

		Player closestPlayer = fetchClosestAverage(players);
		System.out.println("Closest fielding average " + closestPlayer.fetchName() + " with " + percentFormat.format(closestPlayer.fetchStatsDefense().fetchFieldingPercent()));
		System.out.println("Compared with: [Team Avg] " + percentFormat.format(fetchFieldingAvg(players)));

		try (Scanner scanner = new Scanner(System.in)) {

			System.out.print("Would you like to see the [1] least of or [2] most of? ");
			int num = Integer.parseInt(scanner.nextLine());
			if(!(num == 1 || num == 2)) System.err.println("Number is invalid, skipping..");
			else {
				boolean least = num == 1 ? true : false;

				System.out.print("Would you like to see [1] singles, [2] doubles, [3] triples, [4] home runs? ");
				num = Integer.parseInt(scanner.nextLine());

				Map<Player, Integer> map = new HashMap<Player, Integer>();
				List<Player> sortable = new ArrayList<>(players);
				if(!(num == 1 || num == 2 || num == 3 || num == 4)) System.err.println("Number is invalid, skipping..");
				else { 
					if(num == 1) {
						for(Player temp : players) map.put(temp, temp.fetchStatsOffense().fetchSingles());

						Collections.sort(sortable, new Comparator<Player>() {
							@Override public int compare(Player o1, Player o2) {
								return Integer.compare(o1.fetchStatsOffense().fetchSingles(), o2.fetchStatsOffense().fetchSingles());
							}
						});

						if(!least) Collections.reverse(sortable);

						System.out.println("Viewing the list of players with the " + (least ? "least" : "most") + " amount of singles");
					} else if(num == 2) {
						for(Player temp : players) map.put(temp, temp.fetchStatsOffense().fetchDoubles());

						Collections.sort(sortable, new Comparator<Player>() {
							@Override public int compare(Player o1, Player o2) {
								return Integer.compare(o1.fetchStatsOffense().fetchDoubles(), o2.fetchStatsOffense().fetchDoubles());
							}
						});

						if(!least) Collections.reverse(sortable);

						System.out.println("Viewing the list of players with the " + (least ? "least" : "most") + " amount of doubles");
					} else if(num == 3) {
						for(Player temp : players) map.put(temp, temp.fetchStatsOffense().fetchTriples());

						Collections.sort(sortable, new Comparator<Player>() {
							@Override public int compare(Player o1, Player o2) {
								return Integer.compare(o1.fetchStatsOffense().fetchTriples(), o2.fetchStatsOffense().fetchTriples());
							}
						});

						if(!least) Collections.reverse(sortable);

						System.out.println("Viewing the list of players with the " + (least ? "least" : "most") + " amount of triples");
					} else if(num == 4) {
						for(Player temp : players) map.put(temp, temp.fetchStatsOffense().fetchHomeRuns());

						Collections.sort(sortable, new Comparator<Player>() {
							@Override public int compare(Player o1, Player o2) {
								return Integer.compare(o1.fetchStatsOffense().fetchHomeRuns(), o2.fetchStatsOffense().fetchHomeRuns());
							}
						});

						if(!least) Collections.reverse(sortable);

						System.out.println("Viewing the list of players with the " + (least ? "least" : "most") + " amount of home runs");
					}

					for(int i=0; i<sortable.size(); i++)
						System.out.println(sortable.get(i).fetchName() + " - " + map.get(sortable.get(i)));
				}
			}
		} catch (NumberFormatException e) {
			System.err.println("Caught exception: Attempted to parse a number from a string and failed.");
		}

		OffenseStats oStats = new OffenseStats(105, 4, 5, 6, 27, 15, 4, 4, 4);
		DefenseStats dStats = new DefenseStats(18, 19, 3);
		players.add(new Player("Jordan Guzman", oStats, dStats));

		clone = new ArrayList<Player>(players);
		clone.sort(new Comparator<Player>() {
			@Override public int compare(Player o1, Player o2) { return Double.compare(o1.fetchStatsOffense().fetchOnBasePercent(), o2.fetchStatsOffense().fetchOnBasePercent()); }
		});
		System.out.println("\n-- Sorted list by on base % [low -> high] --");
		for(int i=0; i<clone.size(); i++)
			System.out.println(clone.get(i).fetchName() + " with " + percentFormat.format(clone.get(i).fetchStatsOffense().fetchOnBasePercent()));
		System.out.println("-- Sorted list by on base % [high <- low] --\n");


		slugPercent = 0;
		for(Player player : players) slugPercent += player.fetchStatsOffense().fetchSlugging();
		slugPercent /= players.size();
		System.out.println("Teams slugging average: " + percentFormat.format(slugPercent));

		hits = 0;
		for(Player player : players) hits += player.fetchStatsOffense().fetchHits();
		System.out.println("Total hits: " + hits);
	}

	private static Player fetchHighestHitter(List<Player> players) {
		Player high = null;
		for(int i=0;i<players.size(); i++) {
			if(high == null) { high = players.get(i); continue; }
			high = players.get(i).fetchStatsOffense().fetchHits() > high.fetchStatsOffense().fetchHits() ? players.get(i): high;
		}
		return high;
	}

	private static Player[] fetchTwoHighestAverages(List<Player> players) {
		Player highest = null;
		for(Player player : players) {
			if(highest == null) { highest = player; continue; }
			highest = player.fetchStatsOffense().fetchAverage() > highest.fetchStatsOffense().fetchAverage() ? player: highest;
		}

		Player secondHigh = null;
		for(Player player : players) {
			if(highest.equals(player))
				continue;
			if(secondHigh == null) { secondHigh = player; continue; }
			secondHigh = player.fetchStatsOffense().fetchAverage() > secondHigh.fetchStatsOffense().fetchAverage() ? player: secondHigh;
		}

		return new Player[] { highest, secondHigh };
	}

	private static Player[] fetchLowestFielding(List<Player> players) {
		Player lowest = null;
		for(Player player : players) {
			if(lowest == null) { lowest = player; continue; }
			lowest = player.fetchStatsOffense().fetchAverage() < lowest.fetchStatsOffense().fetchAverage() ? player: lowest;
		}

		Player secondLow = null;
		for(Player player : players) {
			if(lowest.equals(player))
				continue;
			if(secondLow == null) { secondLow = player; continue; }
			secondLow = player.fetchStatsOffense().fetchAverage() > secondLow.fetchStatsOffense().fetchAverage() ? player: secondLow;
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
				difference = Math.abs(avg - player.fetchStatsDefense().fetchFieldingPercent());
				continue;
			}

			if(Math.min(difference, Math.abs(avg - player.fetchStatsDefense().fetchFieldingPercent())) == Math.abs(avg - player.fetchStatsDefense().fetchFieldingPercent())) {
				holder = player;
				difference = Math.abs(avg - player.fetchStatsDefense().fetchFieldingPercent());
			}
		}

		return holder;
	}
}
