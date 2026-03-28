import java.util.Scanner;
import java.util.Random;

public class Main {


    @FunctionalInterface
    interface Operation {
        void apply();
    }
    
    public static void warmup(Operation operation) {
    for (int i = 0; i < 50_000; i++) {
        try { operation.apply(); } catch (Exception e) {}
    }
}

   public static double execConstant(int repeticiones, Operation operation) {
        warmup(operation); // <-- calienta el JIT ANTES de medir

        long start = System.nanoTime();
        for (int i = 0; i < repeticiones; i++) {
            operation.apply();
        }
        long finish = System.nanoTime();

        return (double)(finish - start) / repeticiones;
    }


    // Devuelve double y usa nanoTime para ultra precisión
    /*public static double execConstant(int repeticiones, Operation operation) {
       int exitosas = 0;
        long start = System.nanoTime();
        for (int i = 0; i < repeticiones; i++) {
            try {
                operation.apply();
                exitosas++;
            } catch (Exception e) {
                break;
            }
        }
        long finish = System.nanoTime();

        long totalTimeNs = finish - start;
        return exitosas > 0 ? (double) totalTimeNs / exitosas : 0.0;
    }
*/
    // Devuelve double y usa nanoTime para ser mas preciso
    public static double execLinear(Operation operation) {
        int repeticiones = 50;
        int exitosas = 0;
        long start = System.nanoTime();
        for (int i = 0; i < repeticiones; i++) {
            try {
                operation.apply();
                exitosas++;
            } catch (Exception e) {
                break;
            }
        }
        long finish = System.nanoTime();

        long totalTimeNs = finish - start;
        return exitosas > 0 ? (double) totalTimeNs / exitosas : 0.0;
    }

    public static void printResult(String estructura, String metodo, int n, double tiempoNs) {
        System.out.println("=======================================================");
        System.out.printf("%-15s | %-15s | %-12s | %-15s%n",
                "ESTRUCTURA", "MÉTODO", "ELEMENTOS", "TIEMPO (ns)");
        System.out.println("-------------------------------------------------------");
        // %.2f le dice a Java que imprima el double con 2 decimales
        System.out.printf("%-15s | %-15s | %-12d | %-15.2f%n",
                estructura, metodo, n, tiempoNs);
        System.out.println("=======================================================");
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
                        double t = switch (metodoList) {
                            // O(1)
                            case 1  -> execConstant(100000, () -> list.pushFront(val));
                            case 3  -> execConstant(100000, () -> list.popFront());
                            case 8  -> execConstant(100000, () -> list.addAfter(nodo, val));
                            case 9  -> execConstant(100000, () -> list.isEmpty());
                            case 10 -> execConstant(100000, () -> list.topFront());
                            case 12 -> execConstant(100000, () -> list.size());
                            // O(n)
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
                        double t = switch (metodoList) {
                            // O(1)
                            case 1  -> execConstant(100000, () -> list.pushFront(val));
                            case 2  -> execConstant(100000, () -> list.pushBack(val));
                            case 3  -> execConstant(100000, () -> list.popFront());
                            case 8  -> execConstant(100000, () -> list.addAfter(nodo, val));
                            case 9  -> execConstant(100000, () -> list.isEmpty());
                            case 10 -> execConstant(100000, () -> list.topFront());
                            case 11 -> execConstant(100000, () -> list.topBack());
                            case 12 -> execConstant(100000, () -> list.size());
                            // O(n)
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
                        double t = switch (metodoList) {
                            // O(1)
                            case 1  -> execConstant(100000, () -> list.pushFront(val));
                            case 3  -> execConstant(100000, () -> list.popFront());
                            case 6  -> execConstant(100000, () -> list.erase(nodo));
                            case 7  -> execConstant(100000, () -> list.addBefore(nodo, val));
                            case 8  -> execConstant(100000, () -> list.addAfter(nodo, val));
                            case 9  -> execConstant(100000, () -> list.isEmpty());
                            case 10 -> execConstant(100000, () -> list.topFront());
                            case 12 -> execConstant(100000, () -> list.size());
                            // O(n)
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
                        double t = switch (metodoList) {
                            // O(1)
                            case 1  -> execConstant(100000, () -> list.pushFront(val));
                            case 2  -> execConstant(100000, () -> list.pushBack(val));
                            case 3  -> execConstant(100000, () -> list.popFront());
                            case 4  -> execConstant(100000, () -> list.popBack());
                            case 6  -> execConstant(100000, () -> list.erase(nodo));
                            case 7  -> execConstant(100000, () -> list.addBefore(nodo, val));
                            case 8  -> execConstant(100000, () -> list.addAfter(nodo, val));
                            case 9  -> execConstant(100000, () -> list.isEmpty());
                            case 10 -> execConstant(100000, () -> list.topFront());
                            case 11 -> execConstant(100000, () -> list.topBack());
                            case 12 -> execConstant(100000, () -> list.size());
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

                // stack compartido para casos 3-9
                MyStack<Integer> stack = new MyStack<>(n + 200_000);
                for (int i = 0; i < n; i++) stack.push(rand.nextInt());
                int val = rand.nextInt();

                double t = switch (metodoStack) {
                    case 1 -> {
                        // stack propio sin resize para push
                        MyStack<Integer> s = new MyStack<>(n + 200_000);
                        for (int i = 0; i < n; i++) s.push(rand.nextInt());
                        yield execConstant(100_000, () -> s.push(val));
                    }
                    case 2 -> {
                        MyStack<Integer> s = new MyStack<>(n + 200_000);
                        for (int i = 0; i < n; i++) s.push(rand.nextInt());
                        yield execConstant(100_000, () -> {
                            if (s.isEmpty()) s.push(val);
                            s.pop();
                        });
                    }
                    case 3  -> execConstant(100_000, () -> stack.peek());
                    case 4  -> execConstant(100_000, () -> stack.isEmpty());
                    case 5  -> execConstant(100_000, () -> stack.size());
                    case 6  -> execConstant(100_000, () -> stack.capacity());
                    case 9  -> execConstant(100_000, () -> stack.clear());
                    case 7  -> execLinear(() -> stack.delete(val));
                    case 8  -> execLinear(() -> stack.resize(n * 2));
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

                // queue compartido para casos 3-9
                MyQueue<Integer> queue = new MyQueue<>(n + 200_000);
                for (int i = 0; i < n; i++) queue.enqueue(rand.nextInt());
                int val = rand.nextInt();

                double t = switch (metodoQueue) {
                    case 1 -> {
                        MyQueue<Integer> q = new MyQueue<>(n + 200_000);
                        for (int i = 0; i < n; i++) q.enqueue(rand.nextInt());
                        yield execConstant(100_000, () -> q.enqueue(val));
                    }
                    case 2 -> {
                        MyQueue<Integer> q = new MyQueue<>(n + 200_000);
                        for (int i = 0; i < n; i++) q.enqueue(rand.nextInt());
                        yield execConstant(100_000, () -> {
                            if (q.isEmpty()) q.enqueue(val);
                            q.dequeue();
                        });
                    }
                    case 3  -> execConstant(100_000, () -> queue.front());
                    case 4  -> execConstant(100_000, () -> queue.isEmpty());
                    case 5  -> execConstant(100_000, () -> queue.size());
                    case 6  -> execConstant(100_000, () -> queue.capacity());
                    case 9  -> execConstant(100_000, () -> queue.clear());
                    case 7  -> execLinear(() -> queue.delete(val));
                    case 8  -> execLinear(() -> queue.resize(n * 2));
                    default -> throw new IllegalArgumentException("Método inválido");
                };
                printResult("MyQueue", metodoQueue + "", n, t);
            }
            
            default -> System.out.println("Estructura inválida");
        }

        sc.close();
    }
}
