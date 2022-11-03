package sigeliaplicacion.modelos;

import java.util.ArrayList;

public class Carrera {
    private int idCarrera;
    private String nombreCarrera;

    public Carrera() {
    }

    public Carrera(int idCarrera, String nombreCarrera) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }
    
    public ArrayList<Carrera> arrayListCarrera(){
        
        ArrayList<Carrera> arrayListCarrera = new ArrayList<>();
        
        arrayListCarrera.add(new Carrera(0, "Null"));
        arrayListCarrera.add(new Carrera(1, "Ingeniería de software"));
        arrayListCarrera.add(new Carrera(2, "Diseño Gráfico"));
        arrayListCarrera.add(new Carrera(3, "Diseño de Modas"));
        arrayListCarrera.add(new Carrera(2, "Diplomado en Hotelería y turismo"));
        
        return arrayListCarrera();
    }
    
}
