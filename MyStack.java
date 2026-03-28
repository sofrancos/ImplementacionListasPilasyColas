public class MyStack<T> {

    private T[] data;
    private int size;
    private int capacity;

    
   @SuppressWarnings("unchecked")
    public MyStack(int initialCapacity) {
        this.capacity = initialCapacity;
        this.data = (T[]) new Object[capacity];
        this.size = 0;
    }

    public void push(T value) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        data[size] = value;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("Error: Stack vacío");
            return null;
        }
        T value = data[size - 1];
        data[size - 1] = null;
        size--;
        return value;
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("Error: Stack vacío");
            return null;
        }
        return data[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    public void delete(T n) {
        for (int i = size - 1; i >= 0; i--) {
            if (data[i].equals(n)) {
                for (int j = i; j < size - 1; j++) {
                    data[j] = data[j + 1];
                }
                data[size - 1] = null;
                size--;
                return;
            }
        }
    }
    
    public void resize(int newCapacity) {
        @SuppressWarnings("unchecked")
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
        capacity = newCapacity;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }
}

