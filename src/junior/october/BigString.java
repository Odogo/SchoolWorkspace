package junior.october;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
c, a, r
true
m
ssenlooc
true
3
2 vowels, 2 consonants
2
Java%20is%20Great
language programming best the is Java
false
true
bans
0
b
Charlie
Ccehikns
 */
public class BigString {
	
	public static void main(String[] args) {
		
		// #1
		System.out.println(duplicateCharacters("Character"));
		
		// #2
		System.out.println(anagramCheck("Army", "Mary"));
		
		// #3
		System.out.println(firstNonRepeating("Morning"));
		
		// #4
		System.out.println(reverse("Coolness"));
		
		// #5
		System.out.println(isOnlyNumbers("123"));
		
		// #6
		System.out.println(uniqueLettersFromAlphabet("Java"));
		
		// #7
		System.out.println(countVowelsAndConsonants("Java"));
		
		// #8
		System.out.println(countOccurances("Java", 'a'));
		
		// #9
		System.out.println(formatForURL("Java is Great"));
		
		// #10
		// I'd argue C#? but It's a solid language regardless
		System.out.println(reverseSentence("Java is the best programming language"));
		
		// #11
		System.out.println(isPallindrome("chicken"));
		System.out.println(isPallindrome("madam"));
		
		// #12
		System.out.println(removeDuplicates("Bananas"));
		
		// #13
		System.out.println(indexOf("temp", "te"));
		
		// #14
		System.out.println(findHighestCount("aaaaaaabbbbbbbbbbbbbcddddeeeeee"));
		
		// #15
		// System.out.println("stringgoeshere".replace("lookfor", "replaceWith"));
		System.out.println("Chicken".replace("icken", "arlie"));
		
		// #16 (this is question "20" in the doc)
		// 1 -> 9, A -> Z, a -> z
		System.out.println(sort("Chickens"));
	}
	
	private static String duplicateCharacters(String query) {
		if(query == null || query.isEmpty()) return null;
		query = query.toLowerCase();
		
		String dups = "";
		char[] chars = query.toLowerCase().toCharArray();
		for(int i=0; i<chars.length; i++) {
			if(query.lastIndexOf(chars[i]) != i) {
				if(!dups.isEmpty()) dups += ", ";  
				dups += chars[i];
			}
		}
		
		if(dups.isEmpty()) return "none";
		return dups;
	}
	
	private static String anagramCheck(String query1, String query2) {
		char[] q1 = query1.toLowerCase().toCharArray(), q2 = query2.toLowerCase().toCharArray();
		Arrays.sort(q1); Arrays.sort(q2);
		return (Arrays.equals(q1, q2)) + "";
	}
	
	private static String firstNonRepeating(String query) {
		if(query == null || query.isEmpty()) return null;
		query = query.toLowerCase();
		
		char[] chars = query.toCharArray();
		for(int i=0; i<chars.length; i++) {
			if(query.lastIndexOf(chars[i]) == i && query.indexOf(chars[i]) == i) {
				return chars[i] + "";
			}
		}
		
		return "no singletons, all repeats";
	}

	private static String reverse(String query) {
		query = query.toLowerCase();
		
		String reverse = "";
		for(int i=query.length()-1; i>=0; i--) reverse+=query.charAt(i);
		return reverse;
	}

	private static boolean isOnlyNumbers(String query) {
		try { Double.parseDouble(query); return true; } catch (NumberFormatException e) { return false; }
	}

	private static int uniqueLettersFromAlphabet(String query) {
		if(query == null || query.isBlank()) return -500;
		query = query.toLowerCase();
		
		List<Character> unique = new ArrayList<Character>();
		for(char item : query.toCharArray()) if(!unique.contains(item)) unique.add(item);
		return unique.size();
	}
	
	private static String countVowelsAndConsonants(String query) {
		if(query == null || query.isBlank()) return null;
		
		int vow = 0, con = 0;
		for(int i=0; i<query.toCharArray().length; i++) {
			if(isCharVowel(query.toCharArray()[i])) vow++; else con++;
		}
		
		return vow + " vowels, " + con + " consonants";
	}
	
	private static int countOccurances(String query, char lookFor) {
		if(query == null || query.isBlank()) return -999;
		
		int count = 0;
		for(char item : query.toCharArray()) if(item == lookFor) count++;
		return count;
	}
	
	private static String formatForURL(String query) {
		if(query == null || query.isBlank()) return null;
		return query.replace(" ", "%20");
	}
	
	private static String reverseSentence(String query) {
		if(query == null || query.isBlank()) return null;
		String[] split = query.split(" ");
		StringBuilder builder = new StringBuilder();
		
		for(int i=(split.length-1); i>=0; i--) builder.append(split[i]).append(" ");
		return builder.toString().substring(0, builder.toString().length()-1);
	}
	
	private static boolean isPallindrome(String query) {
		if(query == null || query.isBlank()) return false;
		return (query.equalsIgnoreCase(reverse(query)));
	}
	
	private static String removeDuplicates(String query) {
		if(query == null || query.isBlank()) return null;
		query = query.toLowerCase();
		
		List<Character> list = new ArrayList<Character>();
		String temp = "";
		for(char item : query.toCharArray()) {
			if(!list.contains(item)) { list.add(item); temp += item; }
		}
		return temp;
	}
	
	private static int indexOf(String indexing, String query) {
		if(indexing == null || indexing.isBlank()) return -99;
		if(query == null || query.isBlank()) return -99;
		
		if(query.length() > indexing.length()) throw new IndexOutOfBoundsException("Query cannot be larger than the indexing string.");
		if(query.length() == indexing.length() && query.equalsIgnoreCase(indexing)) return 0;
		
		int slot = indexing.indexOf(query.charAt(0));
		int querySlot = 0;
		for(int i=slot; i<indexing.length(); i++) {
			if(query.length() <= querySlot) break;
			if(indexing.charAt(i) != query.charAt(querySlot)) return -1;
			querySlot++;
		}
		
		if(querySlot < query.length()) return -1;
		return slot;
	}
	
	private static String findHighestCount(String query) {
		if(query == null || query.isBlank()) return null;
		
		Map<Character, Integer> letters = new HashMap<Character, Integer>();
		for(char item : query.toCharArray()) {
			if(letters.get(item) == null) letters.put(item, 1);
			else letters.put(item, letters.get(item) + 1);
		}
		
		char highest = 0;
		int highestNum = -1000;
		for(Entry<Character, Integer> entry : letters.entrySet()) {
			if(entry.getValue() > highestNum) {
				highest = entry.getKey();
				highestNum = entry.getValue();
			}
		}
		
		return highest + "";
	}
	
	private static String sort(String query) {
		if(query == null || query.isBlank()) return null;
		
		char[] array = query.toCharArray();
		char temp;
		
		int i=0;
		while(i<array.length) {
			int j = i+1;
			while(j < array.length) { if(array[j] < array[i]) {
				temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			} j++; }
			i++;
		}
		
		String str = "";
		for(char item : array) str += item;
		return str;
	}
	
	private static boolean isCharVowel(char query) {
		switch (query) {
		case 'a': return true;
		case 'e': return true;
		case 'i': return true;
		case 'o': return true;
		case 'u': return true;
		default: return false;
		}
	}
}