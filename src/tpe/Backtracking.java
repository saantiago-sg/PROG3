package src.tpe;

import java.util.ArrayList;
import java.util.HashMap;

public class Backtracking {

    private ArrayList<Tarea> tareas;
    private ArrayList<Procesador> procesadores;

    private HashMap<String, ArrayList<Tarea>> solucionFinal;

    public Backtracking(ArrayList<Tarea> tareas, ArrayList<Procesador> procesadores) {
        this.tareas = tareas;
        this.procesadores = procesadores;
    }

    public void encontrarMejorSolucion(int xLimite) {
        HashMap<String, ArrayList<Tarea>> solucionActual = new HashMap<>();

        for (Procesador p : this.procesadores) {
            ArrayList<Tarea> newListTareas = new ArrayList<>();
            solucionActual.put(p.getId(), newListTareas);
        }

        int metrica = 0;
        int indiceTareas = 0;
        int tareasAsignadas = 0;
        this.funcionrecursiva(tareasAsignadas, indiceTareas, xLimite, solucionActual, metrica);
    }

    private void funcionrecursiva(int tareasAsignadas, int indiceTareas, int xTiempoLimite,
            HashMap<String, ArrayList<Tarea>> solucionActual, int metrica) {

        metrica++;

        if (tareasAsignadas == this.tareas.size()) {
            if (solucionFinal.isEmpty()) {
                solucionFinal.putAll(solucionActual);
            } else {
                this.quedarmeConLaMejorSolucion(solucionActual);
            }
        } else {

            int indiceT = indiceTareas;

            while (indiceT < this.tareas.size()) {
                Tarea t = this.tareas.get(indiceT);

                for (Procesador procesador : this.procesadores) {
                    // poda 1
                    if (this.noSuperaTareasCriticas(solucionActual, procesador.getId())) {

                        // poda 2
                        if (!procesador.isRefrigerado() && noSuperaTiempoLimite(xTiempoLimite, procesador.getId(),
                                solucionActual, t.getTiempoEjecucion())) {

                            // se puede agregar tarea
                            solucionActual.get(procesador.getId()).add(t);

                            indiceTareas += 1;
                            tareasAsignadas += 1;
                            this.funcionrecursiva(tareasAsignadas, indiceTareas, xTiempoLimite, solucionActual,
                                    metrica);
                            indiceTareas--;
                            solucionActual.get(procesador.getId()).remove(t);
                            tareasAsignadas--;

                        } else {
                            // se puede agregar tarea
                            solucionActual.get(procesador.getId()).add(t);

                            indiceTareas += 1;
                            tareasAsignadas += 1;
                            this.funcionrecursiva(tareasAsignadas, indiceTareas, xTiempoLimite, solucionActual,
                                    metrica);
                            indiceTareas--;
                            solucionActual.get(procesador.getId()).remove(t);
                            tareasAsignadas--;
                        }
                    }

                }

                indiceT++;
            }

        }

    }

    private boolean noSuperaTareasCriticas(HashMap<String, ArrayList<Tarea>> solucionActual, String procesadorId) {
        return true; //TODO:
    }

    private boolean noSuperaTiempoLimite(int tiempoLimite, String procesadorId,
            HashMap<String, ArrayList<Tarea>> solucionActual, int tiempoEjecucionTarea) {
        return true;//TODO:
    }

    private HashMap<String, ArrayList<Tarea>> quedarmeConLaMejorSolucion(
            HashMap<String, ArrayList<Tarea>> solucionActual) {
        return null; // TODO: // comparar las dos soluciones y quedarme con la mejor / solucionactual
                     // vs solucionfinal
    }

}
