package src.tpe;

import java.util.ArrayList;
import java.util.HashMap;

public class Backtracking {

	private ArrayList<Tarea> tareas;
	private ArrayList<Procesador> procesadores;
	private HashMap<String, ArrayList<Tarea>> mejorSolucion;
	private int metrica;
	private int sumaMejorSolucion;

	public Backtracking(ArrayList<Tarea> tareas, ArrayList<Procesador> procesadores) {
		this.tareas = tareas;
		this.procesadores = procesadores;
		this.mejorSolucion = null;
	}

	public HashMap<String, ArrayList<Tarea>> resolverBacktracking(int x) {
		HashMap<String, ArrayList<Tarea>> solucionActual = new HashMap<>();
		ArrayList<Tarea> asignadas = new ArrayList<>();
		for (Procesador procesadores : this.procesadores) {
			solucionActual.put(procesadores.getId(), new ArrayList<Tarea>());
		}

		int indiceTareas = 0;
		this.metrica = 0;
		backtracking(solucionActual, asignadas, x, indiceTareas);
		return mejorSolucion;
	}

	private void backtracking(HashMap<String, ArrayList<Tarea>> solucionActual,
		ArrayList<Tarea> asignadas, int tiempoX, int index) {
		// Si todas las tareas fueron asignadas, se considera una solucion completa
		if (asignadas.size() == this.tareas.size()) {
			elegirMejorSolucion(solucionActual);
		} else {
		// Compruebo si la solucion actual es valida y es mejor que la mejor solucion hasta ahora
			if (comprobarSolucionParcial(solucionActual)) {
				int indexTarea = index;
				while (indexTarea < tareas.size()) {
					Tarea tarea = this.tareas.get(indexTarea);
					if (!asignadas.contains(tarea)) {
						for (Procesador procesador : this.procesadores) {
							// compruebo si se puede asignar la tarea al procesador
							if (puedeAsignarTarea(procesador, tarea, solucionActual)) {
								 // Si el procesador no esta refrigerado, compruebo si respeta el limite de tiempo
								if (!procesador.isRefrigerado() && comprobarTiempoLimite(procesador,tarea, solucionActual, tiempoX)) {
									solucionActual.get(procesador.getId()).add(tarea);
									asignadas.add(tarea);
									index += 1;
									metrica += 1;
									backtracking(solucionActual, asignadas, tiempoX, index);
									solucionActual.get(procesador.getId()).remove(tarea);
									asignadas.remove(tarea);
									index--;

								} else if (procesador.isRefrigerado()) {
								 	// Si el procesador esta refrigerado, asigno la tarea sin comprobar el tiempo
									solucionActual.get(procesador.getId()).add(tarea);
									asignadas.add(tarea);
									index += 1;
									metrica += 1;
									backtracking(solucionActual, asignadas,tiempoX, index);
									solucionActual.get(procesador.getId()).remove(tarea);
									asignadas.remove(tarea);
									index--;
								}

							}
						}
					}
					indexTarea += 1;
				}
			}
		}
	}

	private boolean comprobarSolucionParcial(HashMap<String, ArrayList<Tarea>> solucionActual) {
		if (this.mejorSolucion == null) {
			return true;
		} else {
			int sumaActual = procesadorMasTarda(solucionActual);
			if (sumaActual < this.sumaMejorSolucion) {
				return true;
			}
		}
		return false;
	}

	private boolean comprobarTiempoLimite(Procesador procesador, Tarea tarea, HashMap<String, ArrayList<Tarea>> solucionActual, int tiempoX) {
		int sumaParcial = 0;
		for (Tarea t : solucionActual.get(procesador.getId())) {
			sumaParcial += t.getTiempoEjecucion();
		}
		if (sumaParcial + tarea.getTiempoEjecucion() <= tiempoX) {
			return true;
		}
		return false;
	}

	private boolean puedeAsignarTarea(Procesador procesador, Tarea tarea, HashMap<String, ArrayList<Tarea>> solucionActual) {
		if (!tarea.esCritica()) {
			return true;
		} else {
			if (this.getCantidadCritica(procesador, solucionActual) < 2) {
				return true;
			}
			return false;
		}
	}

	private int getCantidadCritica(Procesador procesador, HashMap<String, ArrayList<Tarea>> solucionActual) {
		int tareasCriticas = 0;
		for (Tarea task : solucionActual.get(procesador.getId())) {
			if (task.esCritica())
				tareasCriticas++;
		}
		return tareasCriticas;
	}

	private void elegirMejorSolucion(HashMap<String, ArrayList<Tarea>> solucionActual) {
		int sumaActual = procesadorMasTarda(solucionActual);
		if (sumaActual != 0) {
			if (this.mejorSolucion == null) {
				this.mejorSolucion = new HashMap<>();
				for (String key : solucionActual.keySet()) {
					this.mejorSolucion.put(key,
							new ArrayList<>(solucionActual.get(key)));
				}
				this.sumaMejorSolucion = sumaActual;
			} else {
				if (procesadorMasTarda(this.mejorSolucion) > procesadorMasTarda(solucionActual)) {
					this.mejorSolucion = new HashMap<>();
					for (String key : solucionActual.keySet()) {
						this.mejorSolucion.put(key, new ArrayList<>(
								solucionActual.get(key)));
					}
					this.sumaMejorSolucion = sumaActual;
				}
			}
		}
	}

	public int procesadorMasTarda(HashMap<String, ArrayList<Tarea>> solucion) {
		int sumaTotal = 0;
		for (String procesadores : solucion.keySet()) {
			int suma = 0;
			for (Tarea tareas : solucion.get(procesadores)) {
				suma += tareas.getTiempoEjecucion();
			}
			if (sumaTotal < suma) {
				sumaTotal = suma;
			}
		}
		return sumaTotal;
	}

}