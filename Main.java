import java.time.Instant;
import java.time.Duration;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Seleccione la estructura:");
        System.out.println("1. List");
        System.out.println("2. MyStack");
        System.out.println("3. MyQueue");
        int estructura = sc.nextInt();

        System.out.println("Seleccione el tamaño de entrada (n):");
        System.out.println("1. 10^1");
        System.out.println("2. 10^2");
        System.out.println("3. 10^4");
        System.out.println("4. 10^6");
        System.out.println("5. 10^8");
        int opcionN = sc.nextInt();

        int n = switch (opcionN) {
            case 1 -> 10;
            case 2 -> 100;
            case 3 -> 10_000;
            case 4 -> 1_000_000;
            case 5 -> 100_000_000;
            default -> throw new IllegalArgumentException("Opción inválida");
        };

        switch (estructura) {
            case 1 -> { // List
                System.out.println("Seleccione implementación de List:");
                System.out.println("1. Sencilla sin cola");
                System.out.println("2. Sencilla con cola");
                System.out.println("3. Doble sin cola");
                System.out.println("4. Doble con cola");
                int implList = sc.nextInt();

                System.out.println("Seleccione el método:");
                System.out.println("1. PushFront");
                System.out.println("2. PushBack");
                System.out.println("3. PopFront");
                System.out.println("4. PopBack");
                System.out.println("5. Find");
                System.out.println("6. Erase");
                System.out.println("7. AddBefore");
                System.out.println("8. AddAfter");
                System.out.println("9. isEmpty");
                System.out.println("10. topFront");
                System.out.println("11. topBack");
                System.out.println("12. size");
                int metodoList = sc.nextInt();

                switch (implList) {
                    case 1 -> { // Sencilla sin cola
                        SinglyLinkedList list = new SinglyLinkedList();
                        for (int i = 0; i < n; i++) list.pushFront(rand.nextInt());
                        Node nodo = list.find(rand.nextInt());
                        switch (metodoList) {
                            case 1  -> list.pushFront(rand.nextInt());
                            case 2  -> list.pushBack(rand.nextInt());
                            case 3  -> list.popFront();
                            case 4  -> list.popBack();
                            case 5  -> list.find(rand.nextInt());
                            case 6  -> list.erase(nodo);
                            case 7  -> list.addBefore(nodo, rand.nextInt());
                            case 8  -> list.addAfter(nodo, rand.nextInt());
                            case 9  -> list.isEmpty();
                            case 10 -> list.topFront();
                            case 11 -> list.topBack();
                            case 12 -> list.size();
                            default -> System.out.println("Método inválido");
                        }
                    }
                    case 2 -> { // Sencilla con cola
                        SinglyLinkedListWithTail list = new SinglyLinkedListWithTail();
                        for (int i = 0; i < n; i++) list.pushFront(rand.nextInt());
                        Node nodo = list.find(rand.nextInt());
                        switch (metodoList) {
                            case 1  -> list.pushFront(rand.nextInt());
                            case 2  -> list.pushBack(rand.nextInt());
                            case 3  -> list.popFront();
                            case 4  -> list.popBack();
                            case 5  -> list.find(rand.nextInt());
                            case 6  -> list.erase(nodo);
                            case 7  -> list.addBefore(nodo, rand.nextInt());
                            case 8  -> list.addAfter(nodo, rand.nextInt());
                            case 9  -> list.isEmpty();
                            case 10 -> list.topFront();
                            case 11 -> list.topBack();
                            case 12 -> list.size();
                            default -> System.out.println("Método inválido");
                        }
                    }
                    case 3 -> { // Doble sin cola
                        DoublyLinkedList list = new DoublyLinkedList();
                        for (int i = 0; i < n; i++) list.pushFront(rand.nextInt());
                        Node nodo = list.find(rand.nextInt());
                        switch (metodoList) {
                            case 1  -> list.pushFront(rand.nextInt());
                            case 2  -> list.pushBack(rand.nextInt());
                            case 3  -> list.popFront();
                            case 4  -> list.popBack();
                            case 5  -> list.find(rand.nextInt());
                            case 6  -> list.erase(nodo);
                            case 7  -> list.addBefore(nodo, rand.nextInt());
                            case 8  -> list.addAfter(nodo, rand.nextInt());
                            case 9  -> list.isEmpty();
                            case 10 -> list.topFront();
                            case 11 -> list.topBack();
                            case 12 -> list.size();
                            default -> System.out.println("Método inválido");
                        }
                    }
                    case 4 -> { // Doble con cola
                        DoublyLinkedListWithTail list = new DoublyLinkedListWithTail();
                        for (int i = 0; i < n; i++) list.pushFront(rand.nextInt());
                        Node nodo = list.find(rand.nextInt());
                        switch (metodoList) {
                            case 1  -> list.pushFront(rand.nextInt());
                            case 2  -> list.pushBack(rand.nextInt());
                            case 3  -> list.popFront();
                            case 4  -> list.popBack();
                            case 5  -> list.find(rand.nextInt());
                            case 6  -> list.erase(nodo);
                            case 7  -> list.addBefore(nodo, rand.nextInt());
                            case 8  -> list.addAfter(nodo, rand.nextInt());
                            case 9  -> list.isEmpty();
                            case 10 -> list.topFront();
                            case 11 -> list.topBack();
                            case 12 -> list.size();
                            default -> System.out.println("Método inválido");
                        }
                    }
                    default -> System.out.println("Implementación inválida");
                }
            }

            case 2 -> { // MyStack
                System.out.println("Seleccione el método:");
                System.out.println("1. push");
                System.out.println("2. pop");
                System.out.println("3. peek");
                System.out.println("4. isEmpty");
                System.out.println("5. size");
                System.out.println("6. capacity");
                System.out.println("7. delete");
                System.out.println("8. resize");
                System.out.println("9. clear");
                int metodoStack = sc.nextInt();

                MyStack<Integer> stack = new MyStack<>();
                for (int i = 0; i < n; i++) stack.push(rand.nextInt());

                switch (metodoStack) {
                    case 1 -> stack.push(rand.nextInt());
                    case 2 -> stack.pop();
                    case 3 -> stack.peek();
                    case 4 -> stack.isEmpty();
                    case 5 -> stack.size();
                    case 6 -> stack.capacity();
                    case 7 -> stack.delete(rand.nextInt());
                    case 8 -> stack.resize(n * 2);
                    case 9 -> stack.clear();
                    default -> System.out.println("Método inválido");
                }
            }

            case 3 -> { // MyQueue
                System.out.println("Seleccione el método:");
                System.out.println("1. enqueue");
                System.out.println("2. dequeue");
                System.out.println("3. front");
                System.out.println("4. isEmpty");
                System.out.println("5. size");
                System.out.println("6. capacity");
                System.out.println("7. delete");
                System.out.println("8. resize");
                System.out.println("9. clear");
                int metodoQueue = sc.nextInt();

                MyQueue<Integer> queue = new MyQueue<>();
                for (int i = 0; i < n; i++) queue.enqueue(rand.nextInt());

                switch (metodoQueue) {
                    case 1 -> queue.enqueue(rand.nextInt());
                    case 2 -> queue.dequeue();
                    case 3 -> queue.front();
                    case 4 -> queue.isEmpty();
                    case 5 -> queue.size();
                    case 6 -> queue.capacity();
                    case 7 -> queue.delete(rand.nextInt());
                    case 8 -> queue.resize(n * 2);
                    case 9 -> queue.clear();
                    default -> System.out.println("Método inválido");
                }
            }
            default -> System.out.println("Estructura inválida");
        }

        sc.close();
    }
    public static void exec(int size, String method, Operation operation) {
        Instant start = Instant.now();        
        
        for (int i =0; i<size; i++)
            operation.apply(i);

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.printf("Se ejecutó %s de %d elementos en: %d milisegundos\n", method, size, timeElapsed);
    }

    public static void main(String[] args) {
        final int start = 100;
        final int end = 10000;
        
        for (int size = start; size <= end; size *= 10)
            for (int i =0; i<5; i++){
                ListaArrEsta lista = new ListaArrEsta(size);
                exec(size, "addFirst", lista::addFirst);
            }
    
    }
}
