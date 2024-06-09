package src.tpe;


public class Main {

    public static void main(String[] args) {
        String pathProcesadores = "./datasets/Procesadores.csv";
        String pathTareas = "./datasets/Tareas.csv";

        
        // instancio la clase Servicios
        Servicios servicios = new Servicios(pathProcesadores, pathTareas);
        
        // Valor de x (puede ser configurado segun la necesidad)
        int x = 100; 
        
        // Resolver con Backtracking
        Solucion solucionBacktracking = servicios.resolverConBacktracking(x);
        System.out.println("Resultado Backtracking:");
        System.out.println(solucionBacktracking);
        
        // Resolver con Greedy
        Solucion solucionGreedy = servicios.resolverConGreedy(x);
        System.out.println("Resultado Greedy:");
        System.out.println(solucionGreedy);
    }
}
