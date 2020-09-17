package ex4_1;

/**
 * A class that implements a tree node using a list to store references to the
 * child nodes
 *
 * @author Andrew Ensor
 */
import java.util.Enumeration; // old version of an Iterator
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class LinkedBinaryTreeNode<E> implements MutableTreeNode {

    private E element;
    private MutableTreeNode parent;
    private MutableTreeNode leftChild;
    private MutableTreeNode rightChild;

    public LinkedBinaryTreeNode() {
        this(null);
    }

    public LinkedBinaryTreeNode(E element) {
        this.element = element;
        parent = null;
        leftChild = null;
        rightChild = null;
    }

    // returns an Enumeration of the child nodes
    @Override
    public Enumeration<MutableTreeNode> children() {
        List<MutableTreeNode> list = new LinkedList<>();
        if (rightChild != null) {
            list.add(rightChild);
        }
        if (leftChild != null) {
            list.add(leftChild);
        }
        return (Enumeration<MutableTreeNode>) new Enumerator(list.iterator());
    }

    // returns that this node allows children
    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    // return the child at specified index
    @Override
    public TreeNode getChildAt(int childIndex) throws IndexOutOfBoundsException {
        switch (childIndex) {
            case 1:
                return rightChild;
            case 0:
                return leftChild;
            default:
                throw new IndexOutOfBoundsException("Not in range");
        }
    }

    // returns the number of children of this node
    @Override
    public int getChildCount() {
        int count = 0;
        if (leftChild != null) {
            count++;
        }
        if (rightChild != null) {
            count++;
        }
        return count;
    }

    // returns the index of node or -1 if node not found
    @Override
    public int getIndex(TreeNode node) {
        if (node == null) {
            throw new IllegalArgumentException();
        } else if (leftChild != null && leftChild.equals(node)) {
            return 0;
        } else if (rightChild != null && rightChild.equals(node)) {
            return 1;
        } else {
            return -1;
        }
    }

    // return the parent node or null if this node is the root
    @Override
    public TreeNode getParent() {
        return parent;
    }

    // returns whether this node is a leaf   
    @Override
    public boolean isLeaf() {
        return (getChildCount() == 0);
    }

    // add the child node at specified index, updating this node 
    // and child node to reflect the change
    @Override
    public void insert(MutableTreeNode child, int index) throws IllegalArgumentException {
        if (index == 0 && leftChild != null) {
            throw new IllegalArgumentException("Left child not empty");
        } else if (index == 1 && rightChild != null) {
            throw new IllegalArgumentException("Right child not empty");
        } else if (index == 0) {
            child.removeFromParent();
            leftChild = child;
            leftChild.setParent(this);
        } else if (index == 0) {
            child.removeFromParent();
            rightChild = child;
            rightChild.setParent(this);
        } else {
            throw new IllegalArgumentException("Out of bounds");
        }
    }

    // removes the child at index from this node, updating this
    // node and child node to reflect the change
    @Override
    public void remove(int index) {
        if (index == 0 && leftChild != null) {
            leftChild.setParent(null);
        } else if (index == 1 && rightChild != null) {
            rightChild.setParent(null);
        }
    }

    // remove the specified child from this node, updating this
    // node and child node to reflect the change
    @Override
    public void remove(MutableTreeNode node) {
        if (node != null) {
            if (node.equals(leftChild)) {
                node.setParent(null);
                leftChild = null;
            } else if (node.equals(rightChild)) {
                node.setParent(null);
                rightChild = null;
            }
        }
    }

    // remove this node from its parent, updating this
    // node and its parent node
    @Override
    public void removeFromParent() {
        if (this.parent != null) {
            parent.remove(this);
            this.parent = null;
        }
    }

    // sets the parent of this node to be newParent
    // but does not update newParent
    @Override
    public void setParent(MutableTreeNode newParent) {
        this.parent = newParent;
    }

    // set the element held in this node
    @Override
    public void setUserObject(Object object) {
        this.element = (E) object; // unchecked, could throw exception
    }

    public E getUserObject() {
        return element;
    }

    // returns a string representation of the node and its child nodes
    // in preorder notation
    @Override
    public String toString() {
        String output = "" + this.element;
        if (!isLeaf()) {
            output += "[ ";
            output += leftChild;
            output += "," + rightChild;
            output += "]";
        }
        return output;
    }

    public static void main(String[] args) {  // create some sample nodes
        MutableTreeNode root = new LinkedBinaryTreeNode<>("A");
        MutableTreeNode nodeB = new LinkedBinaryTreeNode<>("B");
        MutableTreeNode nodeC = new LinkedBinaryTreeNode<>("C");
        MutableTreeNode nodeD = new LinkedBinaryTreeNode<>("D");
        MutableTreeNode nodeE = new LinkedBinaryTreeNode<>("E");
        MutableTreeNode nodeF = new LinkedBinaryTreeNode<>("F");
        // build the tree
        nodeB.insert(nodeD, 0);
        nodeB.insert(nodeE, 1);
        nodeC.insert(nodeF, 0);
        root.insert(nodeB, 0);
        root.insert(nodeC, 1);
        System.out.println("Original Tree: " + root);
        root.remove(nodeC);
        nodeB.insert(nodeC, 1);
        System.out.println("Modified Tree: " + root);
    }
}

// utility class to wrap an Iterator object as an Enumeration object
class Enumerator<E> implements Enumeration<E> {

    private Iterator<E> iterator;

    public Enumerator(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasMoreElements() {
        return iterator.hasNext();
    }

    @Override
    public E nextElement() {
        return iterator.next();
    }
}
