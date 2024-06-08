package src.tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Backtracking {
    private List<Procesador> procesadores;
    private List<Tarea> tareas;
    // private HashMap<String, ArrayList<Tarea>> solucionFinal;

    public Backtracking(List<Procesador> procesadores, List<Tarea> tareas) {
        this.procesadores = procesadores;
        this.tareas = tareas;
    }

    public Solucion resolver(int x){
        HashMap<Procesador, List<Tarea>> asignaciones = new HashMap<>();
        for(Procesador procesador : procesadores){
            asignaciones.put(procesador, new ArrayList<>());
        }
        return backtrack(asignaciones, 0, x, 0);
    }

    private Solucion backtrack(HashMap<Procesador, List<Tarea>> asignaciones, int idx, int X, int costo) {
        if (idx == tareas.size()) {
            int tiempoMaximoEjecucion = calcularTiempoMaximoEjecucion(asignaciones);
            return new Solucion(new HashMap<>(asignaciones), tiempoMaximoEjecucion, costo);
        }

        Tarea tarea = tareas.get(idx);
        Solucion mejorSolucion = null;

        for (Procesador procesador : procesadores) {
            if (puedeAsignarTarea(procesador, tarea, asignaciones.get(procesador), X)) {
                asignaciones.get(procesador).add(tarea);
                Solucion solucion = backtrack(asignaciones, idx + 1, X, costo + 1);
                asignaciones.get(procesador).remove(tarea);

                if (mejorSolucion == null || solucion.tiempoMaximoEjecucion < mejorSolucion.tiempoMaximoEjecucion) {
                    mejorSolucion = solucion;
                }
            }
        }

        return mejorSolucion;
    }

    private boolean puedeAsignarTarea(Procesador procesador, Tarea tarea, List<Tarea> tareasAsignadas, int X) {
        int tareasCriticas = 0;
        int tiempoTotal = 0;

        for (Tarea t : tareasAsignadas) {
            if (t.esCritica()) tareasCriticas++;
            tiempoTotal += t.getTiempoEjecucion();
        }

        if (tarea.esCritica() && tareasCriticas >= 2) return false;
        if (!procesador.estaRefrigerado && (tiempoTotal + tarea.getTiempoEjecucion()) > X) return false;

        return true;
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
