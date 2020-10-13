package ex21;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Kelvin
 */
public class SetTest extends ArraySet{
    
    public static void main(String[] args) {
         Set<Integer> set = new LinkedSet<Integer>(); //saves on memory but is slower
         //Set<Integer set = new ArraySet<Integer>(); //should be faster as it can find index faster. But with larger values it will take a long time
         //Set<Integer> set = new TreeSet<Integer>();
         //Set<Integer> set = new HashSet<Integer>();
         
         Random rand = new Random();
         
         long begin = System.currentTimeMillis();
         for (int i = 0; i < 1000; i++){
             set.add(rand.nextInt());
         }
         long end = System.currentTimeMillis();
         
         System.out.println("Set took " + ((double) end - begin) + " milliseconds.");
         
    }
    
}
