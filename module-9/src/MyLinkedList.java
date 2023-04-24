import java.util.LinkedList;

public class MyLinkedList<T> {

    Node<T> first;
    Node<T> last;
    int size = 0;

    public void add(T value) {
        if (first == null) {
            first = new Node<T>(value, null);
            last = first;
        } else {
            Node<T> newNode = new Node<T>(value, null);
            last.setNext(newNode);
            last = newNode;
        }
        size++;
    }

    public T get(int index) {
        if (index > size - 1) {
            throw new IllegalArgumentException("Index doesn't exist");
        }
        Node<T> node = this.first;

        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node.getValue();
    }

    public void remove(int index) {
        if (index > size - 1) {
            throw new IllegalArgumentException("Index doesn't exist");
        }
        if (index == 0) {
            this.first = this.first.getNext();
        } else {

            Node<T> node = this.first;

            for (int i = 0; i < index - 1; i++) {
                node = node.getNext();
            }
            if (node.getNext().getNext() == null) {
                node.setNext(null);
                this.last = node;
            } else {
                node.setNext(node.getNext().getNext());
            }
        }
        size--;
    }

    public void clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public int size(){
        return this.size;
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> my = new MyLinkedList<>();
        my.add(5);
        my.add(3);
        my.add(76);

        System.out.println("my.size = " + my.size);
        my.remove(2);
        System.out.println("my.get(1) = " + my.get(1));
        System.out.println("my.size = " + my.size);
        my.clear();
        System.out.println("my.get(0) = " + my.get(0));

    }
}