package ex22;

import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Kelvin
 * @param <E>
 */
public class LinkedSortedSet<E extends Comparable<E>> extends LinkedSet<E> {

    public static void main(String[] args) {
        Set<String> set = new LinkedSortedSet<>();
        set.add("Bb");
        set.add("Xx");
        set.add("Aa");
        set.add("Dd");
        set.add("Gg");
        set.add("Tt");
        set.add("Bb");
        set.add("Ba");
        set.add("Xx");
        set.add("Cc");
        set.add("Aa");

        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public LinkedSortedSet() {
        super();
    }

    @Override
    public boolean add(E o) {
        Node<E> newNode = new Node<>(o);

        if (firstNode == null || newNode.element.compareTo(firstNode.element) <= 0) { // if first does not exist or new is before first node
            if (firstNode != null && newNode.element.compareTo(firstNode.element) == 0) { // if new is same as first
                return false;
            }
            newNode.next = firstNode;
            firstNode = newNode;
            numElements++;
            return true;
        }
        /*else if (newNode.element == null) { // if new element is null
            return false;
        }*/

        Node<E> current = firstNode;

        for (int i = 0; i <= numElements; i++) {
            if (current.element.compareTo(o) == 0) { // if node already exists stop checks
                return false;
            } else if (current.next == null || current.next.element.compareTo(o) > 0) { // if next is null or new is before current
                newNode.next = current.next;
                current.next = newNode;
                numElements++;
                return true;
            }
            current = current.next;
        }
        return false;
    }

}
