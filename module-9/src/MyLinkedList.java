import java.util.LinkedList;

public class MyLinkedList<T> {

    Node<T> node;

    public void add(T value) {
        new Node(node.getPrev(), value, node.getNext());
    }
