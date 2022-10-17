package junior.october.msoe;

import java.util.Scanner;

/*
Enter sentance: I'd like a candy bar, but I'll settle for a carrot
I'd like a candy bar, but I will settle for a carrot
 */
public class MSOEOne {
	
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
				
			System.out.print("Enter sentance: ");
			String sentence = scanner.nextLine();
			
			sentence = sentence.replace("I'm", "I am");
			sentence = sentence.replace("I've", "I have");
			sentence = sentence.replace("I'll", "I will");
			
			System.out.println(sentence);
		} catch (NumberFormatException e) {
			System.err.println("Caught exception: Attempted to parse a number from a string and failed.");
		}
	}
}