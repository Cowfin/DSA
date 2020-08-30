package ex3_3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class JosephusQueue {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number of soldiers:");
        int m = scan.nextInt();

        System.out.println("Enter the soldier number to kill:");
        int n = scan.nextInt();

        Queue<Integer> soldiers = new LinkedList<>();
        Queue<Integer> killed = new LinkedList<>();

        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= m; i++) {
            soldiers.add(i);
        }

        int counter = 1;
        while (!soldiers.isEmpty()) {
            if (counter == n) {
                counter = 0;
                killed.add(soldiers.poll());
            } else {
                soldiers.add(soldiers.poll());
            }
            counter++;
        }

        System.out.println("Killed order: ");
        while (!killed.isEmpty()) {
            System.out.print(killed.poll() + " ");
        }

        System.out.println("");
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " milliseconds");

    }
}
