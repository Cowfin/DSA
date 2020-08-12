package ex3_2;

import java.util.NoSuchElementException;

public class ArrayQueue<E> implements QueueADT<E> {

    private final int INITIAL_CAPACITY = 5;
    protected int numElements;
    protected E[] elements;
    private int front;
    private int back;

    public ArrayQueue() {
        elements = (E[]) (new Object[INITIAL_CAPACITY]);
        numElements = 0;
        front = 0;
        back = 0;
    }

    @Override
    public void enqueue(E element) {
        if (numElements == elements.length) {
            expandCapacity();
        }
        if (back >= elements.length) {
            back = 0;
        }
        elements[back] = element;
        numElements++;
        back++;
    }

    @Override
    public E dequeue() throws NoSuchElementException {
        E remove = elements[front];
        elements[front] = null;
        front++;
        numElements--;
        return remove;
    }

    @Override
    public E first() throws NoSuchElementException {
        return elements[front];
    }

    @Override
    public E last() throws NoSuchElementException {
        return elements[back - 1];
    }

    @Override
    public boolean isEmpty() {
        return numElements <= 0;
    }

    @Override
    public int size() {
        return numElements;
    }

    @Override
    public String toString() {
        String output = "[";
        for (int i = 0; i < elements.length; i++) {
            output += elements[i];
            if (i < elements.length - 1) {
                output += ",";
            }
        }
        output += "]";
        return output;
    }

    private void expandCapacity() {
        E[] expand = (E[]) (new Object[numElements + INITIAL_CAPACITY]);
        int index = 0;
        System.out.println("EXPANDING");
        System.out.println(elements[front]);
        System.out.println(elements[back - 1]);

        for (int i = front; i != back - 1; i++) {
            if (i == elements.length) {
                i = 0;
                System.out.println(elements[i]);

            }
            expand[index] = elements[i];
            System.out.println(elements[i]);
            index++;
        }
        expand[index] = elements[back - 1];

        front = 0;
        back = numElements;
        elements = (E[]) (new Object[numElements + INITIAL_CAPACITY]);
        elements = expand;
    }

    public static void main(String[] args) {
        QueueADT<Integer> arrQueue = new ArrayQueue<>();
        System.out.println(arrQueue.isEmpty());
        arrQueue.enqueue(1);
        System.out.println(arrQueue.isEmpty());
        arrQueue.enqueue(2);
        arrQueue.enqueue(3);
        arrQueue.enqueue(4);
        System.out.println(arrQueue);
        System.out.println(arrQueue.first());
        System.out.println(arrQueue.last());
        System.out.println("REMOVED: " + arrQueue.dequeue());
        arrQueue.enqueue(5);
        arrQueue.enqueue(6);
        System.out.println("REMOVED: " + arrQueue.dequeue());
        arrQueue.enqueue(7);
        System.out.println("SIZE: " + arrQueue.size());
        System.out.println(arrQueue);
        System.out.println(arrQueue.first());
        System.out.println(arrQueue.last());
        System.out.println("REMOVED: " + arrQueue.dequeue());
        System.out.println("SIZE: " + arrQueue.size());
        arrQueue.enqueue(8);
        arrQueue.enqueue(9);
        System.out.println(arrQueue);
        System.out.println(arrQueue.first());
        System.out.println(arrQueue.last());
        System.out.println("REMOVED: " + arrQueue.dequeue());
        arrQueue.enqueue(10);
        arrQueue.enqueue(11);
        System.out.println(arrQueue);
        System.out.println(arrQueue.first());
        System.out.println(arrQueue.last());
    }

}
