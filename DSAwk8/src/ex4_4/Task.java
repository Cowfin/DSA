package ex4_4;

/**
 *
 * @param <E>
 */
public class Task<E extends Comparable> implements Comparable<Task> {

    private E task;
    private int taskPriority;

    public Task(E element, int priority) {
        this.task = element;
        this.taskPriority = priority;
    }

    public Task(E element) {
        this(element,1);
    }
    
    @Override
    public String toString(){
        return (this.task + " " + this.taskPriority);
    }

    @Override
    public int compareTo(Task o) {
        if (this.taskPriority < o.taskPriority) {
            return -1;
        } else if (this.taskPriority > o.taskPriority) {
            return 1;
        }
        return 0;
    }

}
