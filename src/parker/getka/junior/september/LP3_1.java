package parker.getka.junior.september;

import java.util.Scanner;

public class LP3_1 {
	
	public static void main(String[] args) {
		try(Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter time (less than 4.5): ");
			double entry = Double.parseDouble(scanner.nextLine());
			
			System.out.println("Object Height: " + fetchObjectHeight(entry));
		} catch(NumberFormatException e) {
			System.err.println("Number could not be formatted to a double.");
		}
	}
	
	private static double fetchObjectHeight(double query) {
		return 100 - 4.9 * Math.pow(query, 2);
	}
}
