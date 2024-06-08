package src.tpe;
import java.util.List;
import java.util.Map;

public class Solucion {
    Map<Procesador, List<Tarea>> asignaciones;
    int tiempoMaximoEjecucion;
    int costo;

    public Solucion(Map<Procesador, List<Tarea>> asignaciones, int tiempoMaximoEjecucion, int costo) {
        this.asignaciones = asignaciones;
        this.tiempoMaximoEjecucion = tiempoMaximoEjecucion;
        this.costo = costo;
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tiempo máximo de ejecución: ").append(tiempoMaximoEjecucion).append("\n");
        sb.append("Costo: ").append(costo).append("\n");
        for (Map.Entry<Procesador, List<Tarea>> entry : asignaciones.entrySet()) {
            sb.append(entry.getKey().idProcesador).append(": ");
            for (Tarea tarea : entry.getValue()) {
                sb.append(tarea.getIdTarea()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
