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

   // Elimina y retorna el elemento en la cima
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

   // Retorna el elemento en la cima sin eliminarlo
    public T peek() {
        if (isEmpty()) {
            System.out.println("Error: Stack vacío");
            return null;
        }
        return data[size - 1];
    }

    // Verifica si está vacío
    public boolean isEmpty() {
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

    // Elimina el primer valor n que encuentra
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

