package ex5_2;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Enrolment {

    public static void main(String[] args) {
        Map<Integer, Student> map = new LinkedHashMap<>();

        /*map.put(1000, new Student("Kelvin", 20));
        map.put(1001, new Student("John", 22));
        map.put(1002, new Student("Michael", 29));
        map.put(1003, new Student("Lily", 21));
        map.put(1004, new Student("Jess", 19));
        map.put(1005, new Student("Dave", 27));*/
        Scanner scan = new Scanner(System.in);
        boolean looper = true;
        String uin = "";

        int id = 1000;

        while (looper) {
            System.out.println("Search for a student by ID: ('quit' to exit)");

            uin = scan.nextLine();

            if (uin.toLowerCase().equals("quit")) {
                looper = false;
            } else {
                int uid = Integer.valueOf(uin);
                if (map.containsKey(uid)) {
                    System.out.println("ID: " + uid + " Name: " + map.get(uid).getName() + " Age: " + map.get(uid).getAge());
                } else {
                    System.out.println("ID invalid");
                }
            }
        }

        String name;
        int age;

        System.out.println("Input student's name:");
        name = scan.nextLine();

        System.out.println("Input student's age:");
        age = scan.nextInt();

        if (!map.containsKey(id)) {
            map.put(id, new Student(name, age));
            id++;
            System.out.println("");
        }

    }

}
