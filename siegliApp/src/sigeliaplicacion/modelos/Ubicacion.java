package sigeliaplicacion.modelos;

public class Ubicacion {
    private String estante;
    private int fila;

    public Ubicacion() {
    }

    public Ubicacion(String estante, int fila) {
        this.estante = estante;
        this.fila = fila;
    }

    public String getEstante() {
        return estante;
    }

    public void setEstante(String estante) {
        this.estante = estante;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    
    
}
