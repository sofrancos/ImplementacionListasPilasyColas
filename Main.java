import java.time.Instant;
import java.time.Duration;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void exec(int size, String method, Operation operation) {
        Instant start = Instant.now();        
        
        for (int i =0; i<size; i++)
            operation.apply(i);
        }
        Instant finish = Instant.now();
        return Duration.between(start, finish).toMillis();
    }

    public static void main(String[] args) {
        // Probaremos con tres escalas de tamaño
        int[] sizes = {1000, 10000, 100000};

        System.out.println("====================================================");
        System.out.printf("%-15s | %-12s | %-15s%n", "ESTRUCTURA", "ELEMENTOS", "TIEMPO (ms)");
        System.out.println("----------------------------------------------------");

        for (int size : sizes) {
            // Creamos una nueva instancia de la Pila para cada prueba
            MyStack<Integer> pila = new MyStack<>();

            // Medimos el tiempo del método push
            long tiempo = exec(size, pila::push);

            // Imprimimos el resultado en la tabla
            System.out.printf("%-15s | %-12d | %-15d%n", "Pila (Stack)", size, tiempo);
        }

        System.out.println("====================================================");
    }
}
