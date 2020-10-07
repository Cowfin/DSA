package ex5_1;

public class HashTable<E> {

    private final int INITIAL_CAPACITY = 10;
    protected Node<E>[] table;
    private int count;

    public HashTable() {
        table = new Node[INITIAL_CAPACITY];
        count = 0;
    }

    public void add(Student stu) {
        Node studentNode = new Node(stu);
        int index = stu.hashCode() % 9;

        if (0.75 >= count / table.length) {
            expandCapacity();
        }

        if (table[index].nextNode == null) {
            table[index].nextNode = studentNode;
        } else if (table[index].nextNode != null) {
            studentNode.nextNode = table[index].nextNode;
            table[index].nextNode = studentNode;
        }
        count++;
    }

    private void expandCapacity() {
        System.out.println("EXPANDING CAPACITY");

        Node<E>[] newTable = new Node[table.length + INITIAL_CAPACITY];

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                newTable[i].nextNode = table[i].nextNode;
            }
        }
        table = new Node[newTable.length];
        table = newTable;
    }

    protected class Node<E> {

        public Node nextNode;
        public E element;

        public Node(E element) {
            this.element = element;
            this.nextNode = null;
        }
    }

    public static void main(String[] args) {
        HashTable<Student> ht = new HashTable<>();

        ht.add(new Student("Kelvin", 20));
        ht.add(new Student("David", 120));
        ht.add(new Student("Jess", 25));
        ht.add(new Student("Michael", 20));
        ht.add(new Student("Dave", 15));
        ht.add(new Student("John", 40));

    }

}
