package junior.january;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
/usr/lib/jvm/zulu-19-amd64/bin/java -javaagent:/snap/intellij-idea-community/405/lib/idea_rt.jar=36243:/snap/intellij-idea-community/405/bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath |-[redacted]-| junior.january.BigArrayB
Please select a file to read.
Reading file...
[IGNORED] Error: Caught exception occured. Either the file is not found, or an entry was not a valid type.
Part 2.1: [{Inky, 6.0, 15.69, 2}, {Panda, 35.79, 14.3, 6}, {Rascal, 15.03, 21.1, 21}, {Blacky, 0.0, 13.99, 3}, {Taffy, 26.89, 24.5, 10}, {Toby, 56.89, 17.2, 10}]

Part 2.2: Rascal

Part 2.3: 66.89

Part 2.7:
{Inky, 6.0, 15.69, 2}
{Angel, 3.6, 25.99, 1}
{Panda, 35.79, 14.3, 6}
{Blacky, 0.0, 13.99, 3}
{Taffy, 26.89, 24.5, 10}
{Toby, 66.89, 17.2, 10}
{Gimpy, 14.3, 29.99, 10}

Part 2.10: [{Inky, 6.0, 15.69, 2}, {Blacky, 0.0, 13.99, 3}, {Sugar, 23.6, 33.25, 7}, {Angel, 3.6, 25.99, 1}, {Taffy, 26.89, 24.5, 10}, {Toby, 66.89, 17.2, 10}, {Gimpy, 14.3, 29.99, 10}, {Panda, 35.79, 14.3, 6}]

Part 2.11: [{Blacky, 0.0, 13.99, 3}, {Sugar, 23.6, 33.25, 7}, {Taffy, 26.89, 24.5, 10}, {Gimpy, 14.3, 29.99, 10}]

Part 2.12: [{Sugar, 23.6, 33.25, 7}, {Taffy, 26.89, 24.5, 10}]

Process finished with exit code 0
 */
public class BigArrayB {

    public static class Cat {

        private String name;
        private double weight, cost;
        private int age;

        public Cat(String name, double weight, double cost, int age) {
            this.name = name;
            this.weight = weight;
            this.cost = cost;
            this.age = age;
        }

        public String getName() { return name; }
        public double getWeight() { return weight; }
        public double getCost() { return cost; }
        public int getAge() { return age; }

        public void setName(String name) { this.name = name; }
        public void setWeight(double weight) { this.weight = weight; }
        public void setCost(double cost) { this.cost = cost; }
        public void setAge(int age) { this.age = age; }
        public void addWeight(double amount) { this.weight += amount; }
        public void loseWeight(double amount) { this.weight -= amount; }

        @Override public String toString() {
            return "{" + getName() + ", " + getWeight() + ", " + getCost() + ", " + getAge() + "}";
        }
    }

    private final static List<Cat> cats = new ArrayList<>();

    private static Cat getCat(String name) {
        for (Cat temp : cats)
            if (temp.getName().equals(name)) return temp;
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Please select a file to read.");
        JFileChooser chooser = new JFileChooser("M:/Getka/Langdat");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Textual based data files (.txt, .dat)", "txt", "dat");
        chooser.setFileFilter(filter);

        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();

        if (file == null) {
            System.err.println("No file given, terminating.");
            return;
        }

        System.out.println("Reading file...");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                double weight = Double.parseDouble(scanner.nextLine());
                String name = scanner.nextLine();
                double cost = Double.parseDouble(scanner.nextLine());
                int age = Integer.parseInt(scanner.nextLine());

                cats.add(new Cat(name, weight, cost, age));
            }
        } catch (NumberFormatException | FileNotFoundException e) {
            System.err.println(
                    "Error: Caught exception occured. Either the file is not found, or an entry was not a valid type.");
        }

        // Part 2.1
        System.out.println("Part 2.1: " + cats.toString());

        // Part 2.2
        System.out.println("\nPart 2.2: " + cats.get(2).getName());

        // Part 2.3
        cats.get(cats.size() - 1).addWeight(10);
        System.out.println("\nPart 2.3: " + cats.get(cats.size() - 1).getWeight());

        // Part 2.4
        cats.remove(getCat("Rascal"));

        // Part 2.5
        cats.add(1, new Cat("Angel", 3.6, 25.99, 1));

        // Part 2.6
        cats.add(new Cat("Gimpy", 14.3, 29.99, 10));

        // Part 2.7
        System.out.println("\nPart 2.7: ");
        for (Cat temp : cats) System.out.println(temp.toString());

        // Part 2.8
        Cat holding = cats.get(2);
        cats.set(2, new Cat("Sugar", 23.6, 33.25, 7));
        cats.add(holding);

        // Part 2.9
        holding = cats.get(1);
        Cat holding2 = cats.get(3);
        cats.set(3, holding);
        cats.set(1, holding2);

        // Part 2.10
        System.out.println("\nPart 2.10: " + cats);

        // Part 2.11
        for (int i = 0; i < cats.size(); i++)
            if (cats.get(i).getCost() < 26) cats.remove(i);
        System.out.println("\nPart 2.11: " + cats);

        // Part 2.12
        List<Cat> diet = new ArrayList<>();
        for (int i = 0; i < cats.size(); i++)
            if (cats.get(i).getWeight() > 15) diet.add(cats.get(i));
        System.out.println("\nPart 2.12: " + diet);
    }
}
