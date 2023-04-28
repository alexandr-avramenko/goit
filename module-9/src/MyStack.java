import java.util.Arrays;

public class MyStack {
    private Object[] array = new Object[]{};

    public void push(Object value) {
        if (this.array.length == 0) {
            array = new Object[]{value};
        } else {
            this.array = Arrays.copyOf(this.array, this.array.length + 1);
            this.array[this.array.length - 1] = value;
        }
    }

    public void remove(int index) {
        if (index >= this.array.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Object[] temp = this.array;
        this.array = new Object[]{};

        for (int i = 0; i < temp.length; i++) {
            if (i == index) {
                continue;
            }
            push(temp[i]);
        }
    }

    public void clear() {
        this.array = new Object[]{};
    }

    public int size() {
        return this.array.length;
    }

    public Object peek(int index) {
        if (this.array.length > 0) {
            return this.array[0];
        } else {
            return this.array;
        }
    }

    public Object pop() {

        if (this.array.length == 0) {
            return this.array;
        }

        Object toRemove = this.array[0];

        Object[] temp = this.array;
        this.clear();

        for (int i = 1; i < temp.length; i++) {
            push(temp[i]);
        }
        return toRemove;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
