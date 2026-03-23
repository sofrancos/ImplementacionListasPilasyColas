public class MyQueue<T> {

    private T[] data;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    // Constructor
    @SuppressWarnings("unchecked")
    public MyQueue() {
        this.capacity = 10;
        this.data = (T[]) new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    // Inserta al final
    public void enqueue(T x) {
        // TODO
    }

    // Elimina y retorna el primero
    public T dequeue() {
        // TODO
        return null;
    }

    // Retorna el primero sin eliminar
    public T front() {
        // TODO
        return null;
    }

    // Verifica si está vacía
    public boolean isEmpty() {
        // TODO
        return false;
    }

    // Retorna el tamaño
    public int size() {
        return size;
    }

    // Elimina la primera ocurrencia de n
    public void delete(T n) {
        // TODO
    }

    // Redimensiona el arreglo
    private void resize(int newCapacity) {
        // TODO
    }

    // Limpia la cola
    public void clear() {
        // TODO
    }
}


//Estructura tipo buffer circular:
//front → índice del primer elemento
//rear → posición donde se inserta el siguiente
//Operaciones clave:
//enqueue: usa (rear + 1) % capacity
//dequeue: usa (front + 1) % capacity
//resize debe:
//reordenar elementos desde front a rear
//resetear front = 0 y rear = size
