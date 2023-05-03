import java.util.Arrays;

public class MyQueue<E> {
    private Object[] array = new Object[10];
    private float capacityThreshold = array.length * 0.75f;
    int elementCount = 0;

    public void add(E value) {

        if (elementCount > capacityThreshold) {
            this.array = Arrays.copyOf(this.array, this.array.length * 2);
            this.capacityThreshold *= 2;
        }

        this.array[elementCount] = value;
        elementCount++;
    }

    public void clear() {
        this.array = new Object[10];
    }

    public int size() {
        return this.elementCount;
    }

    public Object peek() {
        if (this.elementCount > 0) {
            return this.array[0];
        } else {
            return this.array;
        }
    }

    public Object poll() {

        if (this.array.length == 0 || this.elementCount == 0) {
            return this.array;
        }

        Object elementToRemove = this.array[0];

        if (this.elementCount == 1) {
            this.clear();
            elementCount--;
            return elementToRemove;
        }

        System.arraycopy(this.array, 1, this.array, 0, this.array.length - 1);
        elementCount--;

        return elementToRemove;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.array);
    }
}
