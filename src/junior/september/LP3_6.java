package junior.september;

import java.util.Scanner;

public class LP3_6 {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			
			System.out.print("Enter a 3-digit number: ");
			int number = Integer.parseInt(scanner.nextLine());
			
			System.out.println("Hundreds place: " + (number / 100));
			System.out.println("Tens place: " + ((number-((number/100)*100)) / 10));
			System.out.println("Ones place: " + ((number-((number/100)*100) - ((number-((number/100)*100)) / 10) * 10) / 1));
			
		} catch (NumberFormatException e) {
			System.err.println("Caught exception: Attempted to parse a number from a string and failed.");
		}
	}
	
}
