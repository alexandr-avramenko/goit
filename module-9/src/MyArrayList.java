import java.util.Arrays;

public class MyArrayList {

    private Object[] array = new Object[]{};

    public void add(Object value) {
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
            add(temp[i]);
        }
    }

    public void clear() {
        this.array = new Object[]{};
    }

    public int size() {
        return this.array.length;
    }

    public Object get(int index) {
        return this.array[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}