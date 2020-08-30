package ex3_6;

import java.util.Random;

/**
 * Class which demonstrates how bin sort can be used to sort distinct integer
 * numbers between 0 and MAX_VALUE
 */
public class BinSort {

    /*public static void main(String[] args) {
        int[] list = {17, 2, 23, 7, 41, 29, 19, 43, 31, 5, 11, 47, 13, 3, 37}; // distinct integer values between 0 and MAX_VALUE
        final int MAX_VALUE = 50;
        boolean[] flags = new boolean[MAX_VALUE + 1]; //initially all false
        // determine which bins should be set to true
        for (int i = 0; i < list.length; i++) {
            flags[list[i]] = true;
        }
        // use the flags to put the numbers back in the list sorted
        int flagNo = 0;
        for (int i = 0; i < list.length; i++) {  // find the next flag that is true
            while (flagNo < flags.length && !flags[flagNo]) {
                flagNo++;
            }
            list[i] = flagNo++;
        }
        // output the results
        for (int i = 0; i < list.length; i++) {
            System.out.print(((i > 0) ? ", " : "") + list[i]);
        }
        System.out.println();
    }*/
    public static void binSort(int[] list, int MAX_VALUE) {
        int[] checker = new int[MAX_VALUE + 1];
        int index = 0;

        for (int i = 0; i < list.length; i++) {
            checker[list[i]] = checker[list[i]] + 1;
        }

        for (int i = 0; i < checker.length; i++) {
            if (checker[i] > 0) {
                for (int j = 0; j < checker[i]; j++) {
                    list[index] = i;
                    index++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();
        final int MAX_VALUE = 100;
        int[] list = {17, 2, 23, 7, 41, 29, 19, 5, 43, 17, 31, 5, 11, 47, 13, 3, 37};
        /*int[] list = new int[100000];

        for (int i = 0; i < list.length; i++) {
            list[i] = rand.nextInt(MAX_VALUE + 1);
        }*/
        long startTime = System.currentTimeMillis();

        binSort(list, MAX_VALUE);

        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " milliseconds");

        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
    }
}

