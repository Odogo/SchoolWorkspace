package junior.november;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import junior.global.SoftballClasses.DefenseStats;
import junior.global.SoftballClasses.OffenseStats;
import junior.global.SoftballClasses.Player;

public class FiveZeroSixWhiskey {

	public static class SoftballTeam {
		private String teamName;
		private List<Player> players;

		public SoftballTeam(String teamName) {
			this.teamName = teamName;
			this.players = new ArrayList<Player>();
		}

		// -- Getters --
		public String fetchTeamName() { return teamName; }

		public List<Player> fetchPlayers() { return players; }
		public int fetchTeamSize() { return players.size(); }
		public boolean hasPlayer(Player player) { return players.contains(player); }

		public int fetchTotalHits() {
			int total = 0;
			for(Player player : players) total += player.fetchStatsOffense().fetchHits();
			return total;
		}

		// -- Setters --
		public void setTeamName(String teamName) { this.teamName = teamName; }

		public void addPlayer(Player... players) { this.players.addAll(Arrays.asList(players)); }
		public void removePlayer(Player... players) { this.players.removeAll(Arrays.asList(players)); }
	}

	private static class PlayerContainer {
		public SoftballTeam team;
		public Player player;

		public PlayerContainer(SoftballTeam team, Player player) { this.team = team; this.player = player; } 
	}

	public static void main(String[] args) throws FileNotFoundException, IllegalAccessException {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);

		File selection = chooser.getSelectedFile();
		if (selection == null)
			throw new FileNotFoundException("File from selection could not be found.");
		if (selection.isDirectory())
			throw new IllegalAccessException("Cannot access a directory as a file.");

		List<SoftballTeam> teams = new ArrayList<SoftballTeam>();
		try (Scanner scanner = new Scanner(selection)) {
			// No need for a while loop, as this does most of the work for us anyway.

			int teamCount = Integer.parseInt(scanner.nextLine());
			for(int i=0; i<teamCount; i++) {
				SoftballTeam team = new SoftballTeam(scanner.nextLine());
				int playerCount = Integer.parseInt(scanner.nextLine());
				for(int x=0; x<playerCount; x++) {
					String line = scanner.nextLine();
					String[] split = line.split(" ");

					String name = split[0] + " " + split[1];
					OffenseStats oStats = new OffenseStats(Integer.parseInt(split[2]), Integer.parseInt(split[3]), 
							Integer.parseInt(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[6]), 
							Integer.parseInt(split[7]), Integer.parseInt(split[8]), Integer.parseInt(split[9]),
							Integer.parseInt(split[10]));
					DefenseStats dStats = new DefenseStats(Integer.parseInt(split[11]), Integer.parseInt(split[12]), Integer.parseInt(split[13]));

					team.addPlayer(new Player(name, oStats, dStats));
				}

				teams.add(team);
			}
		} catch (NoSuchElementException | NumberFormatException e) {
			System.err.println("Caught exception: Unexpected end of data file / Unable to parse integer, program will continue running.");
			System.err.println("Given stack trace [CAN BE IGNORED]: ");
			e.printStackTrace();
		}

		DecimalFormat percent = new DecimalFormat("00.##%");

		System.out.println("Highest hitting team: " + fetchHighestHittingTeam(teams).fetchTeamName() + " [" + fetchHighestHittingTeam(teams).fetchTotalHits() + "]");

		System.out.println("\n");

		PlayerContainer[] twoHighestHitters = fetchTwoHighestBattingAVG(teams);
		System.out.println("The two highest hitters are");
		System.out.println(" - " + twoHighestHitters[0].player.fetchName() + " on " + twoHighestHitters[0].team.fetchTeamName() + " with " + percent.format(twoHighestHitters[0].player.fetchStatsOffense().fetchAverage()));
		System.out.println(" - " + twoHighestHitters[1].player.fetchName() + " on " + twoHighestHitters[1].team.fetchTeamName() + " with " + percent.format(twoHighestHitters[1].player.fetchStatsOffense().fetchAverage()));

		System.out.println("\n");

		PlayerContainer[] twoLowestHitters = fetchTwoLowestFieldingAVG(teams);
		System.out.println("The two lowest fielders are");
		System.out.println(" - " + twoLowestHitters[0].player.fetchName() + " on " + twoLowestHitters[0].team.fetchTeamName() + " with " + percent.format(twoLowestHitters[0].player.fetchStatsOffense().fetchAverage()));
		System.out.println(" - " + twoLowestHitters[1].player.fetchName() + " on " + twoLowestHitters[1].team.fetchTeamName() + " with " + percent.format(twoLowestHitters[1].player.fetchStatsOffense().fetchAverage()));

		System.out.println("\n");

		List<SoftballTeam> sortedByOnBase = fetchTeamsSortedByBasePercent(teams);
		for(SoftballTeam team : sortedByOnBase) 
			System.out.println(team.fetchTeamName() + " with a on base percent of " + percent.format(fetchOnBaseAverage(team)));

		System.out.println("\n");

		List<PlayerContainer> allPlayerSlug = sortAllPlayersBySlugging(teams);
		for(PlayerContainer container : allPlayerSlug)
			System.out.println(container.player.fetchName() + " on team " + container.team.fetchTeamName() + " has " + percent.format(container.player.fetchStatsOffense().fetchSlugging()) + " in slugging");

		System.out.println("\n");

		List<SoftballTeam> sortedByHits = fetchSortedTeamsByHits(teams);
		for(SoftballTeam team : sortedByHits)
			System.out.println("Team " + team.fetchTeamName() + " has a total of " + fetchAllTotalHits(team) + " hits");

		System.out.println("\n");

		PlayerContainer container = fetchPlayerClosestToAllAverage(teams);
		System.out.println(container.player.fetchName() + " has a fielding percentage of " + percent.format(container.player.fetchStatsDefense().fetchFieldingPercent()));
		System.out.println("Compared to the average of " + percent.format(fetchAverageFielding(teams)) + "\n");

		System.out.println("\n");

		try(Scanner scanner = new Scanner(System.in)) {

			System.out.println("What would you like to see?");
			System.out.println("a/1) the most of");
			System.out.println("b/2) the least of");
			System.out.print("Enter option: ");
			String result = scanner.nextLine();
			if(!(result.equalsIgnoreCase("a") || result.equalsIgnoreCase("b") || result.equalsIgnoreCase("1") || result.equalsIgnoreCase("2"))) {
				System.err.println("Invalid response.");
				return;
			}

			boolean least = result.equalsIgnoreCase("b") || result.equalsIgnoreCase("2") ? true: false;

			System.out.println("\nWhat would you like to see?");
			System.out.println("a) singles");
			System.out.println("b) doubles");
			System.out.println("c) triples");
			System.out.println("d) home runs");
			System.out.print("Enter option: ");
			String result2 = scanner.nextLine();

			List<Player> players = fetchAllPlayers(teams);
			String type = "";
			if(result2.equalsIgnoreCase("a")) {
				// singles
				type = "singles";
				players.sort(new Comparator<Player>() {
					@Override public int compare(Player o1, Player o2) {
						if(!least) return Double.compare(o2.fetchStatsOffense().fetchSingles(), o1.fetchStatsOffense().fetchSingles());
						else return Double.compare(o1.fetchStatsOffense().fetchSingles(), o2.fetchStatsOffense().fetchSingles());
					}
				});
			} else if(result2.equalsIgnoreCase("b")) {
				// doubles
				type = "doubles";
				players.sort(new Comparator<Player>() {
					@Override public int compare(Player o1, Player o2) {
						if(!least) return Double.compare(o2.fetchStatsOffense().fetchDoubles(), o1.fetchStatsOffense().fetchDoubles());
						else return Double.compare(o1.fetchStatsOffense().fetchDoubles(), o2.fetchStatsOffense().fetchDoubles());
					}
				});
			} else if(result2.equalsIgnoreCase("c")) {
				// triples
				type = "triples";
				players.sort(new Comparator<Player>() {
					@Override public int compare(Player o1, Player o2) {
						if(!least) return Double.compare(o2.fetchStatsOffense().fetchTriples(), o1.fetchStatsOffense().fetchTriples());
						else return Double.compare(o1.fetchStatsOffense().fetchTriples(), o2.fetchStatsOffense().fetchTriples());
					}
				});
			} else if(result2.equalsIgnoreCase("d")) {
				// home run
				type = "home runs";
				players.sort(new Comparator<Player>() {
					@Override public int compare(Player o1, Player o2) {
						if(!least) return Double.compare(o2.fetchStatsOffense().fetchHomeRuns(), o1.fetchStatsOffense().fetchHomeRuns());
						else return Double.compare(o1.fetchStatsOffense().fetchHomeRuns(), o2.fetchStatsOffense().fetchHomeRuns());
					}
				});
			} else {
				System.err.println("Invalid response");
				return;
			}

			System.out.println("\n-- TOP OF LIST --");

			for(Player player : players) {
				System.out.print(player.fetchName() + " on " + fetchPlayerTeam(teams, player).fetchTeamName() + " has ");
				if(type.equalsIgnoreCase("singles")) System.out.print(player.fetchStatsOffense().fetchSingles());
				else if(type.equalsIgnoreCase("doubles")) System.out.print(player.fetchStatsOffense().fetchDoubles());
				else if(type.equalsIgnoreCase("triples")) System.out.print(player.fetchStatsOffense().fetchTriples());
				else if(type.equalsIgnoreCase("home runs")) System.out.print(player.fetchStatsOffense().fetchHomeRuns());
				System.out.println(" " + type);
			}

			System.out.println("-- BOTTOM OF LIST --\n");

			System.out.println("\n");

			sortedByHits = fetchSortedTeamsByHits(teams);
			for(SoftballTeam team : sortedByHits)
				System.out.println("Team " + team.fetchTeamName() + " has a total of " + fetchAllTotalHits(team) + " hits");

			System.out.println("\n");

			container = fetchPlayerClosestToAllAverage(teams);
			System.out.println(container.player.fetchName() + " has a fielding percentage of " + percent.format(container.player.fetchStatsDefense().fetchFieldingPercent()));
			System.out.println("Compared to the average of " + percent.format(fetchAverageFielding(teams)) + "\n");

			System.out.println("\n");
		}
	}

	private static List<Player> fetchAllPlayers(List<SoftballTeam> teams) {
		List<Player> players = new ArrayList<>();
		for(SoftballTeam team : teams) players.addAll(team.fetchPlayers());
		return players;
	}

	private static SoftballTeam fetchPlayerTeam(List<SoftballTeam> teams, Player query) {
		for(SoftballTeam team : teams)
			if(team.hasPlayer(query)) return team;
		return null;
	}

	private static SoftballTeam fetchHighestHittingTeam(List<SoftballTeam> teams) {
		SoftballTeam temp = null;
		for(SoftballTeam team : teams) {
			if(temp == null) { temp = team; continue; }
			if(team.fetchTotalHits() > temp.fetchTotalHits()) { temp = team; continue; }
		}
		return temp;
	}

	private static PlayerContainer[] fetchTwoHighestBattingAVG(List<SoftballTeam> teams) {
		List<Player> players = fetchAllPlayers(teams);

		Player highest = null;
		for(Player player : players) {
			if(highest == null) { highest = player; continue; }
			if(player.fetchStatsOffense().fetchAverage() > highest.fetchStatsOffense().fetchAverage()) highest = player;
		}

		Player secondary = null;
		for(Player player : players) {
			if(player.equals(highest)) continue;
			if(secondary == null) { secondary = player; continue; }
			if(player.fetchStatsOffense().fetchAverage() > secondary.fetchStatsOffense().fetchAverage()) secondary = player;
		}

		return new PlayerContainer[] { new PlayerContainer(fetchPlayerTeam(teams, highest), highest), new PlayerContainer(fetchPlayerTeam(teams, secondary), secondary) };
	}

	private static PlayerContainer[] fetchTwoLowestFieldingAVG(List<SoftballTeam> teams) {
		List<Player> players = fetchAllPlayers(teams);

		Player lowest = null;
		for(Player player : players) {
			if(lowest == null) { lowest = player; continue; }
			if(player.fetchStatsOffense().fetchAverage() < lowest.fetchStatsOffense().fetchAverage()) lowest = player;
		}

		Player secondary = null;
		for(Player player : players) {
			if(player.equals(lowest)) continue;
			if(secondary == null) { secondary = player; continue; }
			if(player.fetchStatsOffense().fetchAverage() < secondary.fetchStatsOffense().fetchAverage()) secondary = player;
		}

		return new PlayerContainer[] { new PlayerContainer(fetchPlayerTeam(teams, lowest), lowest), new PlayerContainer(fetchPlayerTeam(teams, secondary), secondary) };
	}

	private static double fetchOnBaseAverage(SoftballTeam team) {
		double onBase = 0;
		for(Player player : team.fetchPlayers()) onBase += player.fetchStatsOffense().fetchOnBasePercent();
		return onBase / team.fetchTeamSize();
	}

	private static List<SoftballTeam> fetchTeamsSortedByBasePercent(List<SoftballTeam> teams) {
		List<SoftballTeam> sortable = new ArrayList<SoftballTeam>(teams);
		sortable.sort(new Comparator<SoftballTeam>() { 
			@Override public int compare(SoftballTeam o1, SoftballTeam o2) {
				return Double.compare(fetchOnBaseAverage(o2), fetchOnBaseAverage(o1));
			}
		});
		return sortable;
	}

	private static List<PlayerContainer> sortAllPlayersBySlugging(List<SoftballTeam> teams) {
		List<Player> players = fetchAllPlayers(teams);
		players.sort(new Comparator<Player>() {
			@Override public int compare(Player o1, Player o2) {
				return Double.compare(o2.fetchStatsOffense().fetchSlugging(), o1.fetchStatsOffense().fetchSlugging());
			}
		});

		List<PlayerContainer> containers = new ArrayList<PlayerContainer>();
		for(Player player : players)
			containers.add(new PlayerContainer(fetchPlayerTeam(teams, player), player));
		return containers;
	}

	private static int fetchAllTotalHits(SoftballTeam team) {
		int total = 0;
		for(Player player : team.fetchPlayers()) total += player.fetchStatsOffense().fetchHits();
		return total;
	}

	private static List<SoftballTeam> fetchSortedTeamsByHits(List<SoftballTeam> teams) {
		List<SoftballTeam> sortable = new ArrayList<SoftballTeam>(teams);
		sortable.sort(new Comparator<SoftballTeam>() {
			@Override public int compare(SoftballTeam o1, SoftballTeam o2) {
				return Integer.compare(fetchAllTotalHits(o2), fetchAllTotalHits(o1));
			}
		});
		return sortable;
	}

	private static double fetchAverageFielding(List<SoftballTeam> teams) {
		List<Player> players = fetchAllPlayers(teams);
		double average = 0;
		for(Player player : players) average += player.fetchStatsDefense().fetchFieldingPercent();
		average /= players.size();
		return average;
	}

	private static PlayerContainer fetchPlayerClosestToAllAverage(List<SoftballTeam> teams) {
		List<Player> players = fetchAllPlayers(teams);
		double average = fetchAverageFielding(teams);

		Player closestPlayer = null;
		double difference = Double.MAX_VALUE;
		for(Player player : players) {
			double tempDiff = Math.abs(average - player.fetchStatsDefense().fetchFieldingPercent());
			if(closestPlayer == null || tempDiff < difference) {
				closestPlayer = player;
				difference = tempDiff;
			}
		}

		return new PlayerContainer(fetchPlayerTeam(teams, closestPlayer), closestPlayer);
	}

}