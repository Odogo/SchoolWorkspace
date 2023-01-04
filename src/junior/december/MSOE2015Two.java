package junior.december;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Enter number set: 5
8
5
2
5
0
Result: 3
 */
public class MSOE2015Two {

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter number set: ");
            List<Integer> list = new ArrayList<>();
            while(true) {
                int number = Integer.parseInt(scanner.next());
                list.add(number);
                if(number <= 0) break;
            }

            if(list.size() == 1 && list.get(0) <= 0) {
                System.out.println("Result: 0");
                return;
            }

            int look = list.get(0);

            int count = 0;
            for(int i=0; i<list.size(); i++) {
                if(list.get(i) == look) count++;
            }

            System.out.println("Result: " + count);
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
