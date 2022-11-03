package sigeliaplicacion.modelos;

import java.util.ArrayList;

public class Disponibilidad {
    private int idDisponibilidad;
    private String nombreDisponibilidad;

    public Disponibilidad() {
    }

    public Disponibilidad(int idDisponibilidad, String nombreDisponibilidad) {
        this.idDisponibilidad = idDisponibilidad;
        this.nombreDisponibilidad = nombreDisponibilidad;
    }

    public int getIdDisponibilidad() {
        return idDisponibilidad;
    }

    public void setIdDisponibilidad(int idDisponibilidad) {
        this.idDisponibilidad = idDisponibilidad;
    }

    public String getNombreDisponibilidad() {
        return nombreDisponibilidad;
    }

    public void setNombreDisponibilidad(String nombreDisponibilidad) {
        this.nombreDisponibilidad = nombreDisponibilidad;
    }
    
    public ArrayList<Disponibilidad> arrayListDisponibilidad(){
        
        ArrayList<Disponibilidad> arrayListDisponibilidad = new ArrayList<>();
        
        arrayListDisponibilidad.add(new Disponibilidad(0, "No disponible"));
        arrayListDisponibilidad.add(new Disponibilidad(1, "Disponible"));
        
        return arrayListDisponibilidad;
    }
    
    
}
