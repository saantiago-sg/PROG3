package src.tpe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.tpe.utils.CSVReader;

public class Servicios {
    private Map<String, Tarea> tareasMap;
    private List<Tarea> tareasList;
    private List<Procesador> procesadoresList;
    
    // Complejidad O(n) donde n es el número de líneas en ambos archivo
    public Servicios(String pathProcesadores, String pathTareas) {
        this.tareasMap = new HashMap<>();
        this.tareasList = new ArrayList<>();
        this.procesadoresList = new ArrayList<>();
        
        CSVReader csvReader = new CSVReader();
        csvReader.readProcessors(pathProcesadores, procesadoresList);
        csvReader.readTasks(pathTareas, tareasMap, tareasList);;
    }

    // Servicio 1: Complejidad O(1)
    public Tarea servicio1(String id) {
        return tareasMap.get(id);
    }

    // Complejidad O(n) donde n es el número de tareas
    public List<Tarea> servicio2(boolean esCritica) {
        List<Tarea> result = new ArrayList<>();
        for (Tarea tarea : tareasMap.values()) {
            if (tarea.esCritica() == esCritica) {
                result.add(tarea);
            }
        }
        return result;
    }

    // Complejidad O(n) donde n es el número de tareas
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        List<Tarea> result = new ArrayList<>();
        for (Tarea tarea : tareasMap.values()) {
            if (tarea.getNivelPrioridad() >= prioridadInferior && tarea.getNivelPrioridad() <= prioridadSuperior) {
                result.add(tarea);
            }
        }
        return result;
    }


    public Solucion resolverConBacktracking(int x) {
        Backtracking backtracking = new Backtracking(procesadoresList, tareasList);
        return backtracking.resolver(x);
    }

    public Solucion resolverConGreedy(int x) {
        Greedy greedy = new Greedy(procesadoresList, tareasList);
        return greedy.resolver(x);
    }
}
