package src.tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Greedy {
    private List<Procesador> procesadores;
    private List<Tarea> tareas;

    public Greedy(List<Procesador> procesadores, List<Tarea> tareas) {
        this.procesadores = procesadores;
        this.tareas = tareas;
    }

    public Solucion resolver(int X) {
        HashMap<Procesador, List<Tarea>> asignaciones = new HashMap<>();
        for (Procesador procesador : procesadores) {
            asignaciones.put(procesador, new ArrayList<>());
        }

        List<Tarea> tareasOrdenadas = new ArrayList<>(tareas);
        tareasOrdenadas.sort((t1, t2) -> t2.getTiempoEjecucion() - t1.getTiempoEjecucion());

        int costo = 0;

        for (Tarea tarea : tareasOrdenadas) {
            Procesador mejorProcesador = null;
            int menorTiempo = Integer.MAX_VALUE;

            for (Procesador procesador : procesadores) {
                if (puedeAsignarTarea(procesador, tarea, asignaciones.get(procesador), X)) {
                    int tiempoActual = calcularTiempoEjecucion(asignaciones.get(procesador))
                            + tarea.getTiempoEjecucion();
                    if (tiempoActual < menorTiempo) {
                        menorTiempo = tiempoActual;
                        mejorProcesador = procesador;
                    }
                }
            }

            if (mejorProcesador != null) {
                asignaciones.get(mejorProcesador).add(tarea);
                costo++;
            }
        }

        int tiempoMaximoEjecucion = calcularTiempoMaximoEjecucion(asignaciones);
        return new Solucion(asignaciones, tiempoMaximoEjecucion, costo);
    }

    private boolean puedeAsignarTarea(Procesador procesador, Tarea tarea, List<Tarea> tareasAsignadas, int X) {
        int tareasCriticas = 0;
        int tiempoTotal = 0;

        for (Tarea t : tareasAsignadas) {
            if (t.esCritica())
                tareasCriticas++;
            tiempoTotal += t.getTiempoEjecucion();
        }

        if (tarea.esCritica() && tareasCriticas >= 2)
            return false;
        if (!procesador.estaRefrigerado && (tiempoTotal + tarea.getTiempoEjecucion()) > X)
            return false;

        return true;
    }

    private int calcularTiempoEjecucion(List<Tarea> tareas) {
        int tiempoTotal = 0;
        for (Tarea tarea : tareas) {
            tiempoTotal += tarea.getTiempoEjecucion();
        }
        return tiempoTotal;
    }

    private int calcularTiempoMaximoEjecucion(HashMap<Procesador, List<Tarea>> asignaciones) {
        int tiempoMaximo = 0;
        for (List<Tarea> tareas : asignaciones.values()) {
            int tiempoTotal = 0;
            for (Tarea tarea : tareas) {
                tiempoTotal += tarea.getTiempoEjecucion();
            }
            tiempoMaximo = Math.max(tiempoMaximo, tiempoTotal);
        }
        return tiempoMaximo;
    }
}
