package ex3_4;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class BinarySearchListIterator<E extends Comparable> {

    private final List<E> elements;

    public BinarySearchListIterator(List<E> elements) {
        this.elements = elements;
    }

    public int search(E target) {
        if (target == null) {
            throw new NullPointerException("search target is null");
        }
        ListIterator<E> iterator = elements.listIterator();
        return search(target, 0, elements.size(), iterator);
    }

    private int search(E target, int start, int end, ListIterator<E> iterator) {
        if (start >= end) {
            return -start - 1;
        }

        int midpoint = (start + end) / 2;

        if (midpoint > iterator.nextIndex()) {
            for (int i = iterator.nextIndex(); i < midpoint; i++) {
                iterator.next();
            }
        } else {
            for (int i = iterator.previousIndex(); i >= midpoint; i--) {
                iterator.previous();
            }
        }
        int comparison = target.compareTo(iterator.next());

        if (comparison == 0) {
            return midpoint;
        } else if (comparison < 0) {
            return search(target, start, midpoint, iterator);
        } else {
            return search(target, midpoint + 1, end, iterator);
        }

    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("ant");
        list.add("bat");
        list.add("cat");
        list.add("cow");
        list.add("dog");
        list.add("eel");
        list.add("fly");
        list.add("fox");
        list.add("owl");
        list.add("pig");
        list.add("rat");
        BinarySearchListIterator<String> binarySearch = new BinarySearchListIterator<>(list);
        String target = "dog";
        int index = binarySearch.search(target);
        if (index >= 0) {
            System.out.println(target + " found at index " + index);
        } else {
            System.out.println(target + " not at index " + (-index - 1));
        }
    }

}
