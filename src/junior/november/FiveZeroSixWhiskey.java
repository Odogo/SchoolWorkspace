package junior.november;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
		public boolean isPlayer(Player player) { return players.contains(player); }

		// -- Setters --
		public void setTeamName(String teamName) { this.teamName = teamName; }

		public void addPlayer(Player... players) { this.players.addAll(Arrays.asList(players)); }
		public void removePlayer(Player... players) { this.players.removeAll(Arrays.asList(players)); }
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
			System.err.println("Caught exception: Unexpected end of data file, program will continue running.");
			System.err.println("Given stack trace [CAN BE IGNORED]: ");
			e.printStackTrace();
		}
	}
}