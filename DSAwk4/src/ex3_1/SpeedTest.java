package ex3_1;


public class SpeedTest {
    
    public static void main(String[] args) {
        /*StackADT<String> stackIneff = new ArrayStackIneff<>();
        System.out.println(stackIneff.isEmpty());
        stackIneff.push("hello");
        System.out.println(stackIneff.isEmpty());
        System.out.println(stackIneff);
        stackIneff.push("world");
        stackIneff.push("1");
        stackIneff.push("2");
        stackIneff.push("3");
        System.out.println(stackIneff);
        stackIneff.push("4");
        System.out.println(stackIneff);
        stackIneff.push("5");
        stackIneff.push("6");
        stackIneff.push("7");
        System.out.println(stackIneff);
        System.out.println(stackIneff.pop());
        System.out.println(stackIneff);*/

        StackADT<Integer> stackIneff = new ArrayStackIneff<>();
        StackADT<Integer> stack = new ArrayStack<>();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            stack.pop();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Stack took " + (endTime - startTime) + " milliseconds");

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            stackIneff.push(i);
        }
        while (!stack.isEmpty()) {
            stackIneff.pop();
        }
        endTime = System.currentTimeMillis();
        System.out.println("StackInEff took " + (endTime - startTime) + " milliseconds");

    }
}
