package src.tpe;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String pathProcesadores = "./datasets/Procesadores.csv";
        String pathTareas = "./datasets/Tareas.csv";
        Servicios servicios = new Servicios(pathProcesadores, pathTareas);
        int tiempoX = 100; // tiempo límite

        // PRIMERA PARTE
        // Servicio 1: Obtener informacion de una tarea dado su id
        String idTarea = "T2"; 
        Tarea tarea = servicios.servicio1(idTarea);
        System.out.println("Información de la tarea con ID " + idTarea + ":");
        System.out.println(tarea); 
        // Servicio 2: Obtener todas las tareas criticas o no criticas
        boolean esCritica = true; //
        List<Tarea> tareasCriticas = servicios.servicio2(esCritica);
        System.out.println("Tareas " + (esCritica ? "criticas" : "no criticas") + ":");
        for (Tarea t : tareasCriticas) {
            System.out.println(t);
        }
        // Servicio 3: Obtener todas las tareas entre dos niveles de prioridad
        int prioridadInferior = 20; 
        int prioridadSuperior = 60; 
        List<Tarea> tareasEntrePrioridades = servicios.servicio3(prioridadInferior, prioridadSuperior);
        System.out.println("Tareas entre prioridad " + prioridadInferior + " y " + prioridadSuperior + ":");
        for (Tarea t : tareasEntrePrioridades) {
            System.out.println(t);
        }



        // SEGUNDA PARTE
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
        System.out.println("Solucion obtenida:");
        System.out.println(solucionGreedy);
        System.out.println("Metrica para analizar el costo de la solucion (cantidad de candidatos considerados): "
                + solucionGreedy.getMetricaCandidatos());
    }
}
