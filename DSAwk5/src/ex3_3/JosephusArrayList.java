package ex3_3;

import java.util.ArrayList;
import java.util.Scanner;

public class JosephusArrayList {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number of soldiers:");
        int m = scan.nextInt();

        System.out.println("Enter the soldier number to kill:");
        int n = scan.nextInt();

        ArrayList<Integer> soldiers = new ArrayList<>();
        ArrayList<Integer> killed = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= m; i++) {
            soldiers.add(i);
        }

        int counter = 1;
        while (!soldiers.isEmpty()) {
            if (counter == n) {
                counter = 0;
                killed.add(soldiers.get(0));
                soldiers.remove(0);
            } else {
                soldiers.add(soldiers.get(0));
                soldiers.remove(0);
            }
            counter++;
        }

        System.out.println("Killed order: ");
        for (int i = 0; i < killed.size(); i++) {
            System.out.print(killed.get(i) + " ");
        }

        System.out.println("");
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " milliseconds");
    }

}
