package junior.global;

import java.util.Objects;

public class SoftballClasses {

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

}
