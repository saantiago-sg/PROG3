package src.tpe;

import java.util.ArrayList;
import java.util.HashMap;

public class Solucion {
    private HashMap<String, ArrayList<Tarea>> asignaciones;
    private int tiempoTotal;

    public Solucion(HashMap<String, ArrayList<Tarea>> asignaciones, int tiempoTotal) {
        this.asignaciones = asignaciones;
        this.tiempoTotal = tiempoTotal;
    }

    public HashMap<String, ArrayList<Tarea>> getAsignaciones() {
        return asignaciones;
    }

    public int getTiempoTotal() {
        return tiempoTotal;
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
