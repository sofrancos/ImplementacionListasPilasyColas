import java.time.Instant;
import java.time.Duration;
import java.util.Scanner;
import java.util.Random;

public class Main {

    @FunctionalInterface
    interface Operation {
        void apply();
    }

    // Para métodos O(1): ejecuta 'size' veces y retorna el tiempo total
    // Se usa con métodos cuyo costo no depende del tamaño de la estructura
    public static long execConstant(Operation operation, int repeticiones) {
    long total = 0;
    int exitosas = 0;
    for (int i = 0; i < repeticiones; i++) {
        try {
            Instant start = Instant.now();
            operation.apply();
            Instant finish = Instant.now();
            total += Duration.between(start, finish).toNanos();
            exitosas++;
        } catch (Exception e) {
            break; // la estructura se vació, no tiene sentido seguir
        }
    }
    return exitosas > 0 ? total / exitosas : 0;
}
    // Para métodos O(n): la estructura ya viene llena con n elementos,
    // ejecuta UNA SOLA vez y retorna el tiempo de esa única operación
    public static long execLinear(Operation operation) {
        Instant start = Instant.now();
        operation.apply();
        Instant finish = Instant.now();
        return Duration.between(start, finish).toNanos();
    }

    public static void printResult(String estructura, String metodo, int n, long tiempoNs) {
        System.out.println("====================================================");
        System.out.printf("%-15s | %-15s | %-12s | %-15s%n",
                "ESTRUCTURA", "MÉTODO", "ELEMENTOS", "TIEMPO (ns)");
        System.out.println("----------------------------------------------------");
        System.out.printf("%-15s | %-15s | %-12d | %-15d%n",
                estructura, metodo, n, tiempoNs);
        System.out.println("====================================================");
    }

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
                        int val = rand.nextInt();
                        long t = switch (metodoList) {
                            // O(1)
                            case 1  -> execConstant(1000, () -> list.pushFront(val));
                            case 3  -> execConstant(1000, () -> list.popFront());
                            case 8  -> execConstant(1000, () -> list.addAfter(nodo, val));
                            case 9  -> execConstant(1000, () -> list.isEmpty());
                            case 10 -> execConstant(1000, () -> list.topFront());
                            case 12 -> execConstant(1000, () -> list.size());// O(n)
                            case 2  -> execLinear(() -> list.pushBack(val));
                            case 4  -> execLinear(() -> list.popBack());
                            case 5  -> execLinear(() -> list.find(val));
                            case 6  -> execLinear(() -> list.erase(nodo));
                            case 7  -> execLinear(() -> list.addBefore(nodo, val));
                            case 11 -> execLinear(() -> list.topBack());
                            default -> throw new IllegalArgumentException("Método inválido");
                        };
                        printResult("List-SinCola", metodoList + "", n, t);
                    }
                    case 2 -> { // Sencilla con cola
                        SinglyLinkedListWithTail list = new SinglyLinkedListWithTail();
                        for (int i = 0; i < n; i++) list.pushFront(rand.nextInt());
                        Node nodo = list.find(rand.nextInt());
                        int val = rand.nextInt();
                        long t = switch (metodoList) {
                            // O(1)
                            case 1  -> execConstant(1000, () -> list.pushFront(val));
                            case 2  -> execConstant(1000, () -> list.pushBack(val));
                            case 3  -> execConstant(1000, () -> list.popFront());
                            case 8  -> execConstant(1000, () -> list.addAfter(nodo, val));
                            case 9  -> execConstant(1000, () -> list.isEmpty());
                            case 10 -> execConstant(1000, () -> list.topFront());
                            case 11 -> execConstant(1000, () -> list.topBack());
                            case 12 -> execConstant(1000, () -> list.size());// O(n)
                            case 4  -> execLinear(() -> list.popBack());
                            case 5  -> execLinear(() -> list.find(val));
                            case 6  -> execLinear(() -> list.erase(nodo));
                            case 7  -> execLinear(() -> list.addBefore(nodo, val));
                            
                            default -> throw new IllegalArgumentException("Método inválido");
                        };
                        printResult("List-ConCola", metodoList + "", n, t);
                    }
                    case 3 -> { // Doble sin cola
                        DoublyLinkedList list = new DoublyLinkedList();
                        for (int i = 0; i < n; i++) list.pushFront(rand.nextInt());
                        Node nodo = list.find(rand.nextInt());
                        int val = rand.nextInt();
                        long t = switch (metodoList) {
                            // O(1)
                            case 1  -> execConstant(1000, () -> list.pushFront(val));
                            case 3  -> execConstant(1000, () -> list.popFront());
                            case 6  -> execConstant(1000, () -> list.erase(nodo));
                            case 7  -> execConstant(1000, () -> list.addBefore(nodo, val));
                            case 8  -> execConstant(1000, () -> list.addAfter(nodo, val));
                            case 9  -> execConstant(1000, () -> list.isEmpty());
                            case 10 -> execConstant(1000, () -> list.topFront());
                            case 12 -> execConstant(1000, () -> list.size());// O(n)
                            case 2  -> execLinear(() -> list.pushBack(val));
                            case 4  -> execLinear(() -> list.popBack());
                            case 5  -> execLinear(() -> list.find(val));
                            case 11 -> execLinear(() -> list.topBack());
                            default -> throw new IllegalArgumentException("Método inválido");
                        };
                        printResult("List-DobleSinCola", metodoList + "", n, t);
                    }
                    case 4 -> { // Doble con cola
                        DoublyLinkedListWithTail list = new DoublyLinkedListWithTail();
                        for (int i = 0; i < n; i++) list.pushFront(rand.nextInt());
                        Node nodo = list.find(rand.nextInt());
                        int val = rand.nextInt();
                        long t = switch (metodoList) {
                            // O(1)
                            case 1  -> execConstant(1000, () -> list.pushFront(val));
                            case 2  -> execConstant(1000, () -> list.pushBack(val));
                            case 3  -> execConstant(1000, () -> list.popFront());
                            case 4  -> execConstant(1000, () -> list.popBack());
                            case 6  -> execConstant(1000, () -> list.erase(nodo));
                            case 7  -> execConstant(1000, () -> list.addBefore(nodo, val));
                            case 8  -> execConstant(1000, () -> list.addAfter(nodo, val));
                            case 9  -> execConstant(1000, () -> list.isEmpty());
                            case 10 -> execConstant(1000, () -> list.topFront());
                            case 11 -> execConstant(1000, () -> list.topBack());
                            case 12 -> execConstant(1000, () -> list.size());
                            // O(n)
                            case 5  -> execLinear(() -> list.find(val));
                            default -> throw new IllegalArgumentException("Método inválido");
                        };
                        printResult("List-DobleConCola", metodoList + "", n, t);
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
                int val = rand.nextInt();

                long t = switch (metodoStack) {
                    // O(1) amortizado / O(1)
                    case 1 -> execConstant(1000, () -> stack.push(val));
                    case 2 -> execConstant(1000, () -> stack.pop());
                    case 3 -> execConstant(1000, () -> stack.peek());
                    case 4 -> execConstant(1000, () -> stack.isEmpty());
                    case 5 -> execConstant(1000, () -> stack.size());
                    case 6 -> execConstant(1000, () -> stack.capacity());
                    case 9 -> execConstant(1000, () -> stack.clear());
                    
                    // O(n)
                    case 7 -> execLinear(() -> stack.delete(val));
                    case 8 -> execLinear(() -> stack.resize(n * 2));
                    default -> throw new IllegalArgumentException("Método inválido");
                };
                printResult("MyStack", metodoStack + "", n, t);
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
                int val = rand.nextInt();

                long t = switch (metodoQueue) {
                    // O(1) amortizado / O(1)
                    case 1 -> execConstant(1000, () -> queue.enqueue(val));
                    case 2 -> execConstant(1000, () -> queue.dequeue());
                    case 3 -> execConstant(1000, () -> queue.front());
                    case 4 -> execConstant(1000, () -> queue.isEmpty());
                    case 5 -> execConstant(1000, () -> queue.size());
                    case 6 -> execConstant(1000, () -> queue.capacity());
                    case 9 -> execConstant(1000, () -> queue.clear());
                    // O(n)
                    case 7 -> execLinear(() -> queue.delete(val));
                    case 8 -> execLinear(() -> queue.resize(n * 2));
                    default -> throw new IllegalArgumentException("Método inválido");
                };
                printResult("MyQueue", metodoQueue + "", n, t);
            }
            default -> System.out.println("Estructura inválida");
        }

        sc.close();
    }
}
