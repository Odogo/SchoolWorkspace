package junior.december;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Scanner;

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
            if(list.get(i).getPriorityCode() == 1)
                list.remove(i);
        }

        System.out.println("\n--- split ---\n");
        for(CompanyAccount account : list) {
            System.out.println(account.getAccountNumber() + " spent " + account.getHoursUsed() + " hours (priority: " + account.getPriorityCode() + ") totalling: " + format.format((account.fetchCost() + account.fetchSurcharge())));
        }
    }
}