package junior.september;

import java.util.Scanner;

public class LP3_5 {
	
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			
			System.out.print("Enter change in cents: ");
			int cents = Integer.parseInt(scanner.nextLine());
			
			System.out.println("The minimum number of coins is: ");
			System.out.println("Quarters: " + (cents / 25)); cents -= (cents / 25) * 25;
			System.out.println("Dimes: " + (cents / 10)); cents -= (cents / 10) * 10;
			System.out.println("Nickles: " + (cents / 5)); cents -= (cents / 5) * 5;
			System.out.println("Pennies: " + (cents / 1)); cents -= (cents / 1) * 1;
			
		} catch (NumberFormatException e) {
			System.err.println("Caught exception: Attempted to parse a number from a string and failed.");
		}
	}
	
}
