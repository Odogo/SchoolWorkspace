package junior.september;

import java.util.Scanner;

public class LP3_14 {
	
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			
			System.out.println("Election Results for NY: ");
			System.out.print("Awbrey: ");
			int NYAwbrey = Integer.parseInt(scanner.nextLine());
			
			System.out.print("Martinez: ");
			int NYMart = Integer.parseInt(scanner.nextLine());
			
			System.out.println("Election Results for NJ: ");
			System.out.print("Awbrey: ");
			int NJAwbrey = Integer.parseInt(scanner.nextLine());
			
			System.out.print("Martinez: ");
			int NJMart = Integer.parseInt(scanner.nextLine());
			
			System.out.println("Election Results for CT: ");
			System.out.print("Awbrey: ");
			int CTAwbrey = Integer.parseInt(scanner.nextLine());
			
			System.out.print("Martinez: ");
			int CTMart = Integer.parseInt(scanner.nextLine());
			
			int awbrey = CTAwbrey + NJAwbrey + NYAwbrey;
			int martinez = CTMart + NJMart + NYMart;
			int total = awbrey + martinez;
			
			System.out.println("Candidate | Votes | Percentage");
			System.out.println("Awbrey - " + awbrey + " - " + ((double) awbrey / total) * 100);
			System.out.println("Martinez - " + martinez + " - " + ((double) martinez / total) * 100);
			System.out.println("TOTAL: " + total);
			
		} catch (NumberFormatException e) {
			System.err.println("Caught exception: Attempted to parse a number from a string and failed.");
		}
	}
	
}
