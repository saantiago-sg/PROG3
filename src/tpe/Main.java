package src.tpe;

public class Main {

    public static void main(String[] args) {
        String pathProcesadores = "./datasets/Procesadores.csv";
        String pathTareas = "./datasets/Tareas.csv";
        Servicios servicios = new Servicios(pathProcesadores, pathTareas);
        int tiempoX = 10; // tiempo límite

        // Resolver con Backtracking
        Solucion solucionBacktracking = servicios.backtracking(tiempoX);
        System.out.println("Backtracking");
        System.out.println("Solucion obtenida:");
        System.out.println(solucionBacktracking);
        System.out.println("Metrica para analizar el costo de la solucion (cantidad de estados generados): "
                + solucionBacktracking.getMetricaEstadosGenerados());

        // Resolver con Greedy
        Solucion solucionGreedy = servicios.greedy(tiempoX);
        System.out.println("Greedy");
        System.out.println("Solución obtenida:");
        System.out.println(solucionGreedy);
        System.out.println("Métrica para analizar el costo de la solución (cantidad de candidatos considerados): "
                + solucionGreedy.getMetricaCandidatos());
    }
}
