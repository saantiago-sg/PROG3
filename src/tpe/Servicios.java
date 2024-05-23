package src.tpe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Servicios {
    // Estructuras privadas para almacenar datos
    private Map<String, Tarea> tareasMap;
    private List<Tarea> tareasList;
    
    // Complejidad O(n) donde n es el número de líneas en ambos archivo
    public Servicios(String pathProcesadores, String pathTareas) {
        tareasMap = new HashMap<>();
        tareasList = new ArrayList<>();
        cargarDatos(pathTareas);
    }

       // Método para cargar datos de tareas
       private void cargarDatos(String pathTareas) {
        try (BufferedReader br = new BufferedReader(new FileReader(pathTareas))) {
            String line;
            // Saltar la primera línea (encabezado)
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String idTarea = parts[0];
                String nombreTarea = parts[1];
                int tiempoEjecucion = Integer.parseInt(parts[2]);
                boolean esCritica = Boolean.parseBoolean(parts[3]);
                int nivelPrioridad = Integer.parseInt(parts[4]);
                Tarea tarea = new Tarea(idTarea, nombreTarea, tiempoEjecucion, esCritica, nivelPrioridad);
                tareasMap.put(idTarea, tarea);
                tareasList.add(tarea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Servicio 1: Complejidad O(1)
    public Tarea servicio1(String id) { 
        return tareasMap.get(id);
    }
 
    // Complejidad O(n) donde n es el número de tareas
    public List<Tarea> servicio2(boolean esCritica) {
        List<Tarea> result = new ArrayList<>();
        for (Tarea tarea : tareasList) {
            if (tarea.esCritica() == esCritica) {
                result.add(tarea);
            }
        }
        return result;
    }

    // Complejidad O(n) donde n es el número de tareas
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        List<Tarea> result = new ArrayList<>();
        for (Tarea tarea : tareasList) {
            if (tarea.getNivelPrioridad() >= prioridadInferior && tarea.getNivelPrioridad() <= prioridadSuperior) {
                result.add(tarea);
            }
        }
        return result;
    }

}
