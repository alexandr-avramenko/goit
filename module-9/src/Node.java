public class Node<T> {
    public Node<T> getCurrent() {
        return current;
    }

    public void setCurrent(Node<T> current) {
        this.current = current;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    private Node<T> current;
    private Node<T> next;
    private Node<T> prev;

    public Node(Node<T> prev, Node<T> current, Node<T> next) {
        this.current = current;
        this.next = next;
        this.prev = prev;
    }
}