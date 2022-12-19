package junior.december;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

/*
Enter the cost of the bill [before tipping]: 25.00
Enter the tipping percentage: 20%
Enter the number of party members to split the bill on: 10
Cost per person [of party of 10] $3.00
 */
public class MSOE2015One {

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the cost of the bill [before tipping]: ");
            double cost = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter the tipping percentage: ");
            String entry = scanner.nextLine();
            if(!entry.endsWith("%")) { System.err.println("Invalid format, number must have a % at the end."); return; }
            double tippingRate = Double.parseDouble(entry.substring(0, entry.length()-1)) / 100D;

            System.out.print("Enter the number of party members to split the bill on: ");
            int partyMembers = Integer.parseInt(scanner.nextLine());

            double tipTotal = cost + (cost * tippingRate);
            double perPerson = tipTotal / partyMembers;

            DecimalFormat format = new DecimalFormat("$#.00");
            System.out.println("Cost per person [of party of " + partyMembers + "] " + format.format(perPerson));
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }

}