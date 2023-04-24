import java.util.Arrays;

public class MyQueue {
    private Object[] array = new Object[]{};

    public void add(Object value) {
        if (this.array.length == 0) {
            array = new Object[]{value};
        } else {
            this.array = Arrays.copyOf(this.array, this.array.length + 1);
            this.array[this.array.length - 1] = value;
        }
    }

    public void clear() {
        this.array = new Object[]{};
    }

    public int size() {
        return this.array.length;
    }

    public Object peek() {
        if (this.array.length > 0) {
            return this.array[0];
        } else {
            return this.array;
        }
    }

    public Object poll() {

        if (this.array.length == 0) {
            return this.array;
        }

        Object toRemove = this.array[0];

        Object[] temp = this.array;
        this.clear();

        for (int i = 1; i < temp.length; i++) {
            add(temp[i]);
        }
        return toRemove;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
