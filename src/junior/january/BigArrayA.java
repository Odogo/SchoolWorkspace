package junior.january;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
/usr/lib/jvm/zulu-19-amd64/bin/java -javaagent:/snap/intellij-idea-community/405/lib/idea_rt.jar=44375:/snap/intellij-idea-community/405/bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath |-[redacted]-| junior.january.BigArrayA
Part 1.1: [53.0, 33.0, 25.0, 89.0, 66.0, 51.0, 38.0, 65.0, 42.0, 69.0, 34.0, 55.0, 68.0, 47.0, 68.0, 69.0, 47.0, 31.0, 43.0, 77.0]

Part 1.2: [53.0, 33.0, 25.0, 89.0, 66.0, 51.0, 38.0, 65.0, 42.0, 69.0, 34.0, 55.0, 68.0, 47.0, 68.0, 69.0, 47.0, 31.0, 43.0, 77.0, ]

Part 1.3: 69.0

Part 1.4: 66.33333333333333

Part 1.5: Min: 25.0 (Slot: 2) | Max: 89.0 (Slot: 3)

Part 1.6: [53.0, 33.0, 89.0, 25.0, 66.0, 51.0, 38.0, 65.0, 42.0, 69.0, 34.0, 55.0, 68.0, 47.0, 68.0, 69.0, 47.0, 31.0, 43.0, 77.0]

Part 1.7: [53.0, 33.0, 89.0, 25.0, 66.0, 51.0, 38.0, 65.0, 42.0, 6.0, 34.0, 55.0, 68.0, 47.0, 68.0, 69.0, 47.0, 31.0, 43.0, 77.0]

Part 1.8: [63.0, 43.0, 99.0, 35.0, 76.0, 61.0, 48.0, 75.0, 52.0, 16.0, 44.0, 65.0, 78.0, 57.0, 78.0, 79.0, 57.0, 41.0, 53.0, 87.0]

Part 1.9: 99.0

Part 1.10:
52.0
57.0
57.0
53.0

Part 1.11:
76.0
48.0
52.0
16.0
44.0

Process finished with exit code 0
 */
public class BigArrayA {

    public static void main(String[] args) {
        List<Double> doubles = new ArrayList<>();
        for(int i=0; i<20; i++) doubles.add((double) (new Random().nextInt(70) + 21));

        // Part 1.1
        System.out.println("Part 1.1: " + doubles.toString());

        // Part 1.2
        System.out.print("\nPart 1.2: [");
        for(double temp : doubles) System.out.print(temp + ", ");
        System.out.print("]");

        // Part 1.3
        System.out.print("\n\nPart 1.3: " + doubles.get((doubles.size() - 1) / 2));

        // Part 1.4
        double avg = (doubles.get(0) + doubles.get(doubles.size() - 1) + doubles.get((doubles.size() - 1) / 2)) / 3;
        System.out.println("\n\nPart 1.4: " + avg);

        // Part 1.5
        double min = 250, max = 0;
        int minSlot = 0, maxSlot = 0;
        for(int i=0; i<doubles.size(); i++) {
            double target = doubles.get(i);

            if(target < min) { min = target; minSlot = i; }
            if(target > max) { max = target; maxSlot = i; }
        }
        System.out.println("\nPart 1.5: Min: " + min + " (Slot: " + minSlot + ") | Max: " + max + " (Slot: " + maxSlot + ")");

        // Part 1.6
        doubles.set(minSlot, max);
        doubles.set(maxSlot, min);
        System.out.println("\nPart 1.6: " + doubles.toString());

        // Part 1.7
        double random = new Random().nextInt(9) + 1;
        doubles.set((doubles.size() - 1) / 2, random);
        System.out.println("\nPart 1.7: " + doubles.toString());

        // Part 1.8
        for(int i=0; i<doubles.size(); i++) doubles.set(i, doubles.get(i) + 10);
        System.out.println("\nPart 1.8: " + doubles.toString());

        // Part 1.9
        double third = doubles.get(2); doubles.set(2, 5D);
        System.out.println("\nPart 1.9: " + third);

        // Part 1.10
        System.out.println("\nPart 1.10: ");
        for(int i=0; i<doubles.size(); i++)
            if(doubles.get(i) >= 50 && doubles.get(i) < 60)
                System.out.println(doubles.get(i));

        // Part 1.11
        System.out.println("\nPart 1.11: ");
        for(int i=0; i<doubles.size(); i++)
            if(doubles.get(i) % 4 == 0)
                System.out.println(doubles.get(i));
    }
}
