package src.tpe;

public class Main {

    public static void main(String[] args) {
        String pathProcesadores = "./datasets/Procesadores.csv";
        String pathTareas = "./datasets/Tareas.csv";

        Servicios servicios = new Servicios(pathProcesadores, pathTareas);

        // Resolver el problema con Backtracking
        int x = 50; 
        Solucion solucionBacktracking = servicios.backtracking(x);
        System.out.println("Solución con Backtracking:");
        System.out.println(solucionBacktracking);

        // Resolver el problema con Greedy
        Solucion solucionGreedy = servicios.greedy(x);
        System.out.println("Solución con Greedy:");
        System.out.println(solucionGreedy);
    }
}
