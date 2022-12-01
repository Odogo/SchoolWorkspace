package junior.october.msoe2018;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

/*
Enter sentence: Either it is science or just weird that I received sufficient caffeine
0: either
3: science
6: weird
10: sufficient
 */
public class MSOE2018Two {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
				
			System.out.print("Enter sentence: ");
			String sentence = scanner.nextLine();
			String[] split = sentence.split(" ");
			
			Map<Integer, String> storage = new HashMap<Integer, String>();
			for(int i=0; i<split.length; i++) {
				String item = split[i].toLowerCase();
				
				if(!isPlausibleWord(item) && isOppPlausibleWord(item))
					storage.put(i, item);
			}
			
			for(Entry<Integer, String> entry : storage.entrySet()) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}
		} catch (NumberFormatException e) {
			System.err.println("Caught exception: Attempted to parse a number from a string and failed.");
		}
	}
	
	private static boolean isPlausibleWord(String item) {
		if(!item.contains("c") && item.contains("ie")) return true;
		else if(item.contains("cei")) return true;
		else return false;
	}
	
	private static boolean isOppPlausibleWord(String item) {
		if(!item.contains("c") && item.contains("ei")) return true;
		else if(item.contains("cie")) return true;
		else return false;
	}
}