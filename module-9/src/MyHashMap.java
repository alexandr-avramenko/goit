import java.util.ArrayList;

public class MyHashMap<K, V> {
    ArrayList<Node<K, V>> entries = new ArrayList<>(150);
    int size = 0;

    public void put(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        int index = key.hashCode() % entries.size();
        if (entries.get(index) == null) {
            entries.set(index, newNode);
        } else {
            Node<K, V> existingNode = entries.get(index);
            while (existingNode.next != null) {
//                if (existingNode.getKey().equals(newNode.getKey())) {
//                    existingNode.setValue(newNode.getValue());
//                    size--;
//                    break;
//                }
                existingNode = existingNode.next;
            }
            existingNode.setNext(newNode);
        }
        size++;
    }

    static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public void setNext(Node<K, V> nextNode) {
            this.next = nextNode;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }


    }

    public static void main(String[] args) {
        MyHashMap<Integer, String> hashMap = new MyHashMap<>();
        hashMap.put(0, "Dima");
    }
}