import java.util.Arrays;

public class MyHashMap<K, V> {

    Node<K, V>[] entries = new Node[1];
    private int size = 0;
    private float capacityThreshold = entries.length * 0.75f;
    public void clear() {
        Arrays.fill(entries, null);
    }
    private void extendArray(){
        Node<K, V>[] currentHashTable = entries;
        entries = new Node[entries.length * 2];
        this.size = 0;
        for (Node<K, V> node : currentHashTable) {
            if (node != null) {
                this.put(node.getKey(), node.getValue());
                while (node.next != null) {
                    this.put(node.next.getKey(), node.next.getValue());
                    node = node.next;
                }
            }
        }
    }
    public void put(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);

        if (size + 1 >= capacityThreshold) {
            capacityThreshold *= 2;
            extendArray();
        }

        int index = getIndex(key);

        if (entries[index] == null) {
            entries[index] = newNode;
        } else {
            Node<K, V> existingNode = entries[index];

            while (existingNode.next != null) {
                if (existingNode.getKey().equals(newNode.getKey())) {
                    existingNode.setValue(newNode.getValue());
                    return;
                }
                existingNode = existingNode.next;
            }
            if (existingNode.getKey().equals(newNode.getKey())) {
                existingNode.setValue(newNode.getValue());
                return;
            }
            existingNode.setNext(newNode);
        }
        size++;
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        int hashIndex = this.getIndex(key);
        if (entries[hashIndex] == null) {
            return null;
        }
        /*if (entries[hashIndex].getKey().equals(key)) {
            return entries[hashIndex].getValue();
        }
        Node<K, V> currentNode = entries[hashIndex].next;
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return currentNode.getValue();
            }
            currentNode = currentNode.next;
        }
        return null;*/
        Node<K, V> currentNode = entries[hashIndex];

        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return currentNode.getValue();
            }
            currentNode = currentNode.next;
        }
        return null;
    }
    private int getIndex(K key) {
        return key.hashCode() % entries.length;

    }

    public void remove(K key) {
        int hashIndex = this.getIndex(key);
        Node<K, V> item = entries[hashIndex];
        if (item == null) {
            return;
        }
        //without tail
        if (item.getKey().equals(key) && item.next == null){
            entries[hashIndex] = null;
            size--;
            return;
        }
        //first element with tail
        if (item.getKey().equals(key) && item.next != null) {
            entries[hashIndex] = item.next;
            item.setNext(null);
            size--;
            return;
        }

        while (item.next != null) {
            if (item.next.getKey().equals(key)) {
                item.setNext(item.next.next);
                size--;
                return;
            }
            item = item.next;
        }
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

    @Override
    public String toString() {
        return Arrays.toString(entries);
    }

    public static void main(String[] args) {
        System.out.println(5 % 10);
    }
}