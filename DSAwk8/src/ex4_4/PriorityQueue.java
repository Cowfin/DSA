package ex4_4;

import java.util.NoSuchElementException;

public class PriorityQueue implements QueueADT<Task<?>> { // forcing the generics to only be Task

    private final ArrayHeap<Task<?>> heap;

    public PriorityQueue() {
        heap = new ArrayHeap<>();
    }

    @Override
    public void enqueue(Task<?> element) {
        heap.add(element);
    }

    @Override
    public Task<?> dequeue() throws NoSuchElementException {
        return heap.removeMin();
    }

    @Override
    public Task<?> first() throws NoSuchElementException {
        return heap.getMin();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public int size() {
        return heap.size();
    }

    public static void main(String[] args) {
        //QueueADT queue = new LinkedQueue();
        QueueADT<Task<?>> queue = new PriorityQueue();
        
        queue.enqueue(new Task("aaaaaa", 1));
        queue.enqueue(new Task("bbbbbb", 2));
        queue.enqueue(new Task("aaaaab", 1));
        queue.enqueue(new Task("ddddd", 4));
        queue.enqueue(new Task("eeeeee", 5));
        queue.enqueue(new Task("cccccc", 3));
        
        while(!queue.isEmpty()){
            System.out.println(queue.dequeue());
        }

    }

}
