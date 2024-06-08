package src.tpe;

public class Procesador {

    public String idProcesador;
    public String codigoProcesador;
    public boolean estaRefrigerado;
    public int anioFuncionamiento;

    public Procesador(String idProcesador, String codigoProcesador, boolean estaRefrigerado, int anioFuncionamiento) {
        this.idProcesador = idProcesador;
        this.codigoProcesador = codigoProcesador;
        this.estaRefrigerado = estaRefrigerado;
        this.anioFuncionamiento = anioFuncionamiento;
    }

    public String getId() {
        return this.idProcesador;
    }

    public void setId(String id) {
        this.idProcesador = id;
    }

    public int getAnioFuncionamiento(){
        return this.anioFuncionamiento;
    }
    public void setAnioFuncionamient(int anio) {
        this.anioFuncionamiento = anio;
    }

    public boolean isRefrigerado() {
        return this.estaRefrigerado;
    }
    
    @Override
    public String toString() {
        return "Procesador{" +
                "id='" + idProcesador + '\'' +
                ", codigo='" + codigoProcesador + '\'' +
                ", estaRefrigerado=" + estaRefrigerado +
                ", anioFuncionamiento=" + anioFuncionamiento +
                '}';
    }
}
