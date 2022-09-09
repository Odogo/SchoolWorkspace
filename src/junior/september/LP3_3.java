package junior.september;

import java.text.DecimalFormat;
import java.util.Scanner;

public class LP3_3 {
	
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			
			System.out.print("Rent costs: $");
			double rent = Double.parseDouble(scanner.nextLine());

			System.out.print("Tutition costs: $");
			double tutition = Double.parseDouble(scanner.nextLine());
			
			System.out.print("Books/Textbooks costs: $");
			double books = Double.parseDouble(scanner.nextLine());
			
			System.out.print("Misc. costs: $");
			double misc = Double.parseDouble(scanner.nextLine());
			
			double subtotal = rent + tutition + books + misc;
			
			System.out.print("Scholarship covers: $");
			double scholarship = Double.parseDouble(scanner.nextLine());
			
			System.out.print("Misc. coverage: $");
			double miscCov = Double.parseDouble(scanner.nextLine());
			
			double total = subtotal - (scholarship + miscCov);
			System.out.println("You will need to pay: $" + new DecimalFormat("0.00").format(total));
			
		} catch (NumberFormatException e) {
			System.err.println("Caught exception: Attempted to parse a number from a string and failed.");
		}
	}
	
}
