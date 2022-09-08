package junior.september;

import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class LP3_9 {

	public static void main(String[] args) {
		try(Scanner scanner = new Scanner(System.in)) {

			System.out.println("Enter birthday below: ");
			System.out.print("Year: ");
			int year = Integer.parseInt(scanner.nextLine());
			
			System.out.print("Month: ");
			int month = Integer.parseInt(scanner.nextLine());
			
			System.out.print("Day: ");
			int day = Integer.parseInt(scanner.nextLine());
			
			GregorianCalendar birthday = new GregorianCalendar(year, month, day);
			
			System.out.println("\nEnter today's date: ");
			System.out.print("Year: ");
			year = Integer.parseInt(scanner.nextLine());
			
			System.out.print("Month: ");
			month = Integer.parseInt(scanner.nextLine());
			
			System.out.print("Day: ");
			day = Integer.parseInt(scanner.nextLine());
			
			GregorianCalendar today = new GregorianCalendar(year, month, day);
			
			long difference = (today.getTimeInMillis() - birthday.getTimeInMillis());
			
			System.err.println("\nNOTE: This INCLUDES leap years and assume months are normal [not 30 days in each month regardless]");
			System.out.println("You have been alive for " + TimeUnit.MILLISECONDS.toDays(difference) + " days");
			System.out.println("You have been alive for " + TimeUnit.MILLISECONDS.toDays(difference) * 8 + " hours");
			
		} catch(NumberFormatException e) {
			System.err.println("Caught exception: Attempted to parse a number from a string and failed.");
		}	
	}
}
