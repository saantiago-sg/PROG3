package src.tpe;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("workingggg");

        // Ruta a los archivos CSV
        String pathProcesadores = "./datasets/procesadores.csv";
        String pathTareas = "./datasets/tareas.csv";

        // Crear instancia de Servicios
        Servicios servicios = new Servicios(pathProcesadores, pathTareas);

        // Servicio 1: Obtener información de una tarea por id
        Tarea tarea = servicios.servicio1("2");
        System.out.println("Servicio 1: " + tarea.getNombreTarea()); // Debe imprimir "Tarea1"

        // Servicio 2: Obtener todas las tareas críticas
        List<Tarea> tareasCriticas = servicios.servicio2(true);
        System.out.println("Servicio 2: Tareas críticas");
        for (Tarea t : tareasCriticas) {
            System.out.println(t.getNombreTarea()); // Debe imprimir "Tarea1", "Tarea3", "Tarea5"
        }

        // Servicio 2: Obtener todas las tareas no críticas
        List<Tarea> tareasNoCriticas = servicios.servicio2(false);
        System.out.println("Servicio 2: Tareas no críticas");
        for (Tarea t : tareasNoCriticas) {
            System.out.println(t.getNombreTarea()); // Debe imprimir "Tarea2", "Tarea4"
        }

        // Servicio 3: Obtener todas las tareas entre niveles de prioridad 20 y 70
        List<Tarea> tareasEntrePrioridades = servicios.servicio3(20, 70);
        System.out.println("Servicio 3: Tareas entre niveles de prioridad 20 y 70");
        for (Tarea t : tareasEntrePrioridades) {
            System.out.println(t.getNombreTarea()); // Debe imprimir "Tarea1", "Tarea2", "Tarea3", "Tarea4"
        }

    }
}
