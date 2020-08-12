package ex3_1;

import java.util.NoSuchElementException;

public class ArrayStackIneff<E> implements StackADT<E> {

    private final int INITIAL_CAPACITY = 20;
    protected int numElements;
    protected E[] elements;

    public ArrayStackIneff() {
        numElements = 0;
        elements = (E[]) (new Object[INITIAL_CAPACITY]);
    }

    @Override
    public void push(E element) {
        if (numElements >= elements.length) {
            expandCapacity();
        }

        for (int i = numElements; i > 0; i--) {
            elements[i] = elements[i - 1];
        }

        elements[0] = element;
        numElements++;
    }

    @Override
    public E pop() throws NoSuchElementException {
        E pop = elements[0];

        for (int i = 0; i < numElements - 1; i++) {
            elements[i] = elements[i + 1];
        }
        numElements--;

        return pop;
    }

    @Override
    public E peek() throws NoSuchElementException {
        return elements[0];
    }

    @Override
    public boolean isEmpty() {
        return numElements <= 0;
    }

    @Override
    public int size() {
        return numElements;
    }

    private void expandCapacity() {
        E[] expand = (E[]) (new Object[numElements + INITIAL_CAPACITY]);

        System.arraycopy(elements, 0, expand, 0, numElements);

        elements = (E[]) (new Object[numElements + INITIAL_CAPACITY]);
        elements = expand;
    }

    @Override
    public String toString() {
        String output = "[";
        for (int i = 0; i < numElements; i++) {
            output += elements[i];
            if (i < numElements - 1) {
                output += ",";
            }
        }
        output += "]";
        return output;
    }

}
