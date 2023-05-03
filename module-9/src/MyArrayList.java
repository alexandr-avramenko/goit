import java.util.Arrays;

public class MyArrayList<E> {
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

    public void remove(int index) {
        if (index >= this.array.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Seems like the index is out of List range");
        }

        Object[] temp = this.array;
        this.array = new Object[this.array.length - 1];

        System.arraycopy(temp, 0, this.array, 0, index);
        System.arraycopy(temp, index + 1, this.array, index, this.array.length - index);
        elementCount--;

    }

    public void clear() {
        this.array = new Object[10];
    }

    public int size() {
        return this.elementCount;
    }

    public E get(int index) {
        return (E) this.array[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}