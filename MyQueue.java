public class MyQueue<T> {

    private T[] data;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    // Constructor
    @SuppressWarnings("unchecked")
    public MyQueue(int initialCapacity) {
        this.capacity = initialCapacity;
        this.data = (T[]) new Object[capacity];
        // inicializa los demás campos igual que el constructor por defecto
    }
   // Inserta al final
    public void enqueue(T x) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        data[rear] = x;
        rear = (rear + 1) % capacity;
        size++;
    }
    
    // Elimina y retorna el primero
    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Error: Cola vacía");
            return null;
        }
        T value = data[front];
        data[front] = null;
        front = (front + 1) % capacity;
        size--;
        return value;
    }

    // Retorna el primero sin eliminar
    public T front() {
       if (isEmpty()) {
            System.out.println("Error: Cola vacía");
            return null;
        }
        return data[front];
    }

    // Verifica si está vacía
    public boolean isEmpty() {
        return size == 0;
    }

    // Retorna el tamaño
    public int size() {
        return size;
    }

    // Elimina la primera ocurrencia de n
    public void delete(T n) {
        if (isEmpty()) return;
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            if (data[index].equals(n)) {

                for (int j = i; j < size - 1; j++) {
                    int curr = (front + j) % capacity;
                    int next = (front + j + 1) % capacity;
                    data[curr] = data[next];
                }
                int last = (front + size - 1) % capacity;
                data[last] = null;
                rear = (rear - 1 + capacity) % capacity;
                size--;
                return;
            }
        }
    }
    // Redimensiona el arreglo
    public void resize(int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % capacity];
        }
        data = newData;
        front = 0;
        rear = size;
        capacity = newCapacity;
    }

    // Limpia la cola
    public void clear() {
       for (int i = 0; i < size; i++) {
            data[(front + i) % capacity] = null;
        }
        front = 0;
        rear = 0;
        size = 0;
    }
    public int capacity(){
        return capacity;
    }
}
