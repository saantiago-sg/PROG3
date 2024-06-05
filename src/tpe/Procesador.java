package src.tpe;

public class Procesador {

    private String id;

    public Procesador(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isRefrigerado() {
        return true; // TODO:
    }

}
