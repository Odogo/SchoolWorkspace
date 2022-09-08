package junior.september;

import java.util.Scanner;

public class LP3_7 {
	
	public static void main(String[] args) {
		try(Scanner scanner = new Scanner(System.in)) {
			
			System.out.print("Enter an integer: ");
			int entryA = Integer.parseInt(scanner.nextLine());
			
			System.out.print("Enter a secondary integer: ");
			int entryB = Integer.parseInt(scanner.nextLine());
			
			System.out.println(entryA + " / " + entryB + " = " + (entryA / entryB));
			System.out.println(entryA + " / " + entryB + " = " + (entryA % entryB));
			
			System.out.println(entryB + " / " + entryA + " = " + (entryB / entryA));
			System.out.println(entryB + " / " + entryA + " = " + (entryB % entryA));
			
		} catch(NumberFormatException e) {
			System.err.println("Caught exception: Attempted to parse a number from a string and failed.");
		}
	}
	
}
