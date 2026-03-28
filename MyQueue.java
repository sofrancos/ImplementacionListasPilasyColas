public class MyQueue<T> {

    private T[] data;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    
    @SuppressWarnings("unchecked")
    public MyQueue(int initialCapacity) {
        this.capacity = initialCapacity;
        this.data = (T[]) new Object[capacity];
        
    }
   
    public void enqueue(T x) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        data[rear] = x;
        rear = (rear + 1) % capacity;
        size++;
    }
    
    
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

    
    public T front() {
       if (isEmpty()) {
            System.out.println("Error: Cola vacía");
            return null;
        }
        return data[front];
    }

    
    public boolean isEmpty() {
        return size == 0;
    }

    
    public int size() {
        return size;
    }

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
