package junior.september;

import java.text.DecimalFormat;
import java.util.Scanner;

public class LP3_10 {
	
	private static final double 
		burgerCost = 1.69, 
		fryCost = 1.09, 
		sodaCost = 0.99,
		taxRate = 0.065;
	
	private static final DecimalFormat format = new DecimalFormat("0.00");
	
	public static void main(String[] args) {
		try(Scanner scanner = new Scanner(System.in)) {
			
			System.out.print("Ordered burgers: ");
			int burgers = Integer.parseInt(scanner.nextLine());
			
			System.out.print("Ordered fries: ");
			int fries = Integer.parseInt(scanner.nextLine());
			
			System.out.print("Ordered sodas: ");
			int sodas = Integer.parseInt(scanner.nextLine());
			
			double subtotal = (burgerCost*burgers) + (fryCost*fries) + (sodaCost*sodas);
			System.out.println("Subtotal: $" + format.format(subtotal));
			
			double tax = subtotal * taxRate;
			System.out.println("Tax: $" + format.format(tax));
			
			double total = subtotal+tax;
			System.out.println("Total: $" + format.format(total));
			
			System.out.println("\n=== PAYMENT ===\n");
			
			System.out.print("Amount tendered: $");
			double entry = Double.parseDouble(scanner.nextLine());
			if((total-entry) * -1 < 0) {
				System.err.println("Requires: $" + format.format((total-entry)));
				return;
			}
			
			System.out.println("Change: $" + format.format((total - entry) * -1));
		} catch(NumberFormatException e) {
			System.err.println("Caught exception: Attempted to parse a number from a string and failed.");
		}
	}
	
}
