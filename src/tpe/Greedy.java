package src.tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;

public class Greedy {
    private ArrayList<Tarea> tareas;
    private ArrayList<Procesador> procesadores;
    private HashMap<String, ArrayList<Tarea>> solucion;
    private int candidatos;

    public Greedy(ArrayList<Tarea> tareas, ArrayList<Procesador> procesadores) {
        this.tareas = tareas;
        this.procesadores = procesadores;
        this.candidatos = 0;
    }

    public HashMap<String, ArrayList<Tarea>> resolverGreedy(int x) {
        ArrayList<Tarea> tareasOrdenadas = this.ordenarTareas();
        this.solucion = crearHashMap();

        for (Tarea tarea : tareasOrdenadas) {
            Procesador mejorProcesador = null;
            int tiempoMejorProcesador = Integer.MAX_VALUE;
            for (Procesador procesador : procesadores) {
                if (cumpleCondiciones(x, tarea, procesador)) {
                    candidatos++;
                    int tiempoProc = sumarTiemposProcesador(procesador);
                    if (tiempoProc < tiempoMejorProcesador) {
                        mejorProcesador = procesador;
                        tiempoMejorProcesador = tiempoProc;
                    }
                }
            }
            if (mejorProcesador != null) {
                solucion.get(mejorProcesador.getId()).add(tarea);
            }
        }
        return solucion;
    }

    private boolean cumpleCondiciones(int x, Tarea tarea, Procesador procesador) {
        int tiempoProc = sumarTiemposProcesador(procesador) + tarea.getTiempoEjecucion();
        if (!tarea.esCritica()) {
            if (procesador.isRefrigerado()) {
                return tiempoProc <= x;
            } else {
                return true;
            }
        } else {
            if (cantidadCriticas(procesador) < 2) {
                if (procesador.isRefrigerado()) {
                    return tiempoProc <= x;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private int cantidadCriticas(Procesador procesador) {
        int suma = 0;
        for (Tarea tarea : solucion.get(procesador.getId())) {
            if (tarea.esCritica()) {
                suma++;
            }
        }
        return suma;
    }

    private ArrayList<Tarea> ordenarTareas() {
        Collections.sort(tareas, new Comparator<Tarea>() {
            @Override
            public int compare(Tarea t1, Tarea t2) {
                return Integer.compare(t2.getTiempoEjecucion(), t1.getTiempoEjecucion());
            }
        });
        return tareas;
    }

    private int sumarTiemposProcesador(Procesador procesador) {
        ArrayList<Tarea> tareasDelProcesador = solucion.get(procesador.getId());
        int suma = 0;
        for (Tarea tarea : tareasDelProcesador) {
            suma += tarea.getTiempoEjecucion();
        }
        return suma;
    }

    private HashMap<String, ArrayList<Tarea>> crearHashMap() {
        HashMap<String, ArrayList<Tarea>> hash = new HashMap<>();
        for (Procesador procesador : this.procesadores) {
            hash.put(procesador.getId(), new ArrayList<Tarea>());
        }
        return hash;
    }

    public int procesadorMasTarda(HashMap<String, ArrayList<Tarea>> solucion) {
        int sumaTotal = 0;
        for (String procesadores : solucion.keySet()) {
            int suma = 0;
            for (Tarea tarea : solucion.get(procesadores)) {
                suma += tarea.getTiempoEjecucion();
            }
            if (sumaTotal < suma) {
                sumaTotal = suma;
            }
        }
        return sumaTotal;
    }
    
    public int getCandidatos() {
        return this.candidatos;
    }
}