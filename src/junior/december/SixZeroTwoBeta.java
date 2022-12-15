package junior.december;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
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

        public double fetchTotalCost() {
            if(hoursUsed >= 0 && hoursUsed <= 5) return 200;
            else if(hoursUsed > 5 && hoursUsed <= 15) return 200 + (50*(hoursUsed-5));
            else if(hoursUsed > 15) return 550 + (30*(hoursUsed-15));
            else return -1;
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

        try(Scanner scanner = new Scanner(file)) {

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
