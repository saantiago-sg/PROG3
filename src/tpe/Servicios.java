package src.tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.tpe.utils.CSVReader;

public class Servicios {
    private Map<String, Tarea> tareasMap;
    private ArrayList<Tarea> tareasList;
    private ArrayList<Procesador> procesadoresList;

    // Complejidad O(n) donde n es el número de líneas en ambos archivo
    public Servicios(String pathProcesadores, String pathTareas) {
        this.tareasMap = new HashMap<>();
        this.tareasList = new ArrayList<>();
        this.procesadoresList = new ArrayList<>();

        CSVReader csvReader = new CSVReader();
        csvReader.readProcessors(pathProcesadores, procesadoresList);
        csvReader.readTasks(pathTareas, tareasMap, tareasList);
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

    public Solucion backtracking(int tiempoLimite) {
        Backtracking backtracking = new Backtracking(tareasList, procesadoresList);
        HashMap<String, ArrayList<Tarea>> solucion = backtracking.resolverBacktracking(tiempoLimite);
        int metricaEstadosGenerados = backtracking.getMetrica();
        int tiempoMaximo = backtracking.procesadorMasTarda(solucion);
        return new Solucion(solucion, tiempoMaximo, metricaEstadosGenerados, 0); 
    }

    public Solucion greedy(int tiempoLimite) {
        Greedy greedy = new Greedy(tareasList, procesadoresList);
        HashMap<String, ArrayList<Tarea>> solucion = greedy.resolverGreedy(tiempoLimite);
        int metricaCandidatos = greedy.getCandidatos();
        int tiempoMaximo = greedy.procesadorMasTarda(solucion);
        return new Solucion(solucion, tiempoMaximo, 0, metricaCandidatos);
    }

    public ArrayList<Tarea> getTareasList(){
        return this.tareasList;
    }

    public ArrayList<Procesador> getProcesadoresList(){
        return this.procesadoresList;
    }
}
