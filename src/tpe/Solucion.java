package src.tpe;

import java.util.ArrayList;
import java.util.HashMap;

public class Solucion {
    private HashMap<String, ArrayList<Tarea>> asignaciones;
    private int tiempoTotal;
    private int metricaEstadosGenerados; // metrica para Backtracking
    private int metricaCandidatos; // metrica para Greedy

    public Solucion(HashMap<String, ArrayList<Tarea>> asignaciones, int tiempoTotal, int metricaEstadosGenerados, int metricaCandidatos) {
        this.asignaciones = asignaciones;
        this.tiempoTotal = tiempoTotal;
        this.metricaEstadosGenerados = metricaEstadosGenerados;
        this.metricaCandidatos = metricaCandidatos;
    }

    public HashMap<String, ArrayList<Tarea>> getAsignaciones() {
        return asignaciones;
    }

    public int getTiempoTotal() {
        return tiempoTotal;
    }

    public int getMetricaEstadosGenerados() {
        return metricaEstadosGenerados;
    }

    public int getMetricaCandidatos() {
        return metricaCandidatos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String procesadorId : asignaciones.keySet()) {
            sb.append("Procesador ").append(procesadorId).append(":\n");
            for (Tarea tarea : asignaciones.get(procesadorId)) {
                sb.append("* Tarea: ").append(tarea.getIdTarea())
                        .append(", Tiempo de ejecución: ").append(tarea.getTiempoEjecucion()).append("\n");
            }
        }
        sb.append("Tiempo total de ejecución: ").append(tiempoTotal).append("\n");
        return sb.toString();
    }
}
