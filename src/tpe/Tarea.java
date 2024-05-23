package src.tpe;

public class Tarea {

    private String idTarea;
    private String nombreTarea;
    private int tiempoEjecucion;
    private boolean esCritica;
    private int nivelPrioridad;

    public Tarea(String idTarea, String nombreTarea, int tiempoEjecucion, boolean esCritica, int nivelPrioridad) {
        this.idTarea = idTarea;
        this.nombreTarea = nombreTarea;
        this.tiempoEjecucion = tiempoEjecucion;
        this.esCritica = esCritica;
        this.nivelPrioridad = nivelPrioridad;
    }

    public String getIdTarea() {
        return idTarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public boolean esCritica() {
        return esCritica;
    }

    public int getNivelPrioridad() {
        return nivelPrioridad;
    }
}
