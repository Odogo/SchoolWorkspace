package junior.december;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Scanner;

/*
1825 spent 3.25 hours (priority: 0) totalling: $200.00
14063 spent 17.06 hours (priority: 1) totalling: $661.80
17185 spent 7.93 hours (priority: 1) totalling: $396.50
19111 spent 12.0 hours (priority: 2) totalling: $700.00
20045 spent 5.0 hours (priority: 1) totalling: $250.00
21352 spent 5.84 hours (priority: 0) totalling: $242.00
22841 spent 27.9 hours (priority: 2) totalling: $1087.00
23051 spent 1.55 hours (priority: 2) totalling: $350.00
29118 spent 15.02 hours (priority: 0) totalling: $550.60

--- split --- [after killing the 1s]

1825 spent 3.25 hours (priority: 0) totalling: $200.00
19111 spent 12.0 hours (priority: 2) totalling: $700.00
21352 spent 5.84 hours (priority: 0) totalling: $242.00
22841 spent 27.9 hours (priority: 2) totalling: $1087.00
23051 spent 1.55 hours (priority: 2) totalling: $350.00
29118 spent 15.02 hours (priority: 0) totalling: $550.60
 */
public class SixZeroTwoBeta {

    public static class CompanyAccount {

        private int accountNumber;
        private double hoursUsed;
        private int priorityCode;

        public CompanyAccount(int accountNumber, double hoursUsed, int priorityCode) {
            this.accountNumber = accountNumber;
            this.hoursUsed = hoursUsed;
            this.priorityCode = priorityCode;
        }

        public int getAccountNumber() { return accountNumber; }
        public double getHoursUsed() { return hoursUsed; }
        public int getPriorityCode() { return priorityCode; }

        public double fetchCost() {
            if(hoursUsed >= 0 && hoursUsed <= 5) return 200;
            else if(hoursUsed > 5 && hoursUsed <= 15) return 200 + (50*(hoursUsed-5));
            else if(hoursUsed > 15) return 550 + (30*(hoursUsed-15));
            else return -1;
        }

        public double fetchSurcharge() {
            switch(getPriorityCode()) {
                case 1: return 50;
                case 2: return 150;
                default: return 0;
            }
        }
    }

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);

        File file = chooser.getSelectedFile();
        if(file == null) {
            System.err.println("Selected file does not exist.");
            return;
        } else if(!file.isFile()) {
            System.err.println("Selected file is not a readable file.");
            return;
        }

        LinkedList<CompanyAccount> list = new LinkedList<>();
        try(Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(" ");

                int account = Integer.parseInt(split[0]);
                double hours = Double.parseDouble(split[1]);
                int priority = Integer.parseInt(split[2]);

                list.add(new CompanyAccount(account, hours, priority));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        DecimalFormat format = new DecimalFormat("$#.00");
        for(CompanyAccount account : list) {
            System.out.println(account.getAccountNumber() + " spent " + account.getHoursUsed() + " hours (priority: " + account.getPriorityCode() + ") totalling: " + format.format((account.fetchCost() + account.fetchSurcharge())));
        }

        for(int i=0; i<list.size(); i++) {
            if(list.get(i).getPriorityCode() == 1) {
                list.remove(i);
                i--;
            }
        }

        System.out.println("\n--- split ---\n");
        for(CompanyAccount account : list) {
            System.out.println(account.getAccountNumber() + " spent " + account.getHoursUsed() + " hours (priority: " + account.getPriorityCode() + ") totalling: " + format.format((account.fetchCost() + account.fetchSurcharge())));
        }
    }
}