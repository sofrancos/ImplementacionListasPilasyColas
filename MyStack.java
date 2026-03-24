public class MyStack<T> {

    private T[] data;
    private int size;
    private int capacity;

    // Constructor
    @SuppressWarnings("unchecked")
    public MyStack() {
        this.capacity = 10; // capacidad inicial
        this.data = (T[]) new Object[capacity];
        this.size = 0;
    }

    // Inserta elemento en el tope
    public void push(T value) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        data[size] = value;
        size++;
    }

    // Elimina el elemento del tope
    public void pop() {
        if (!empty()) {
            size--;
        }
    }

    // Retorna el elemento del tope
    public T top() {
        if (!empty()) {
            return data[size - 1];
        }
        return null;
    }

    // Verifica si está vacío
    public boolean empty() {
        return size == 0;
    }

    // Retorna el tamaño actual
    public int size() {
        return size;
    }

    // Retorna la capacidad actual
    public int capacity() {
        return capacity;
    }

    // Redimensiona el arreglo (por ejemplo, duplicando capacidad)
    private void resize(int newCapacity) {
        @SuppressWarnings("unchecked")
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
        capacity = newCapacity;
    }

    // Limpia el stack
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }
}

