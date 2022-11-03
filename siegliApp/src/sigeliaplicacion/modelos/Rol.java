package sigeliaplicacion.modelos;

import java.util.ArrayList;

public class Rol {
    private int idRol;
    private String nombreRol;

    public Rol() {
    }

    public Rol(int idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
    
    public ArrayList<Rol> arrayListRol(){
        ArrayList<Rol> arrayListRol = new ArrayList<>();
        arrayListRol.add(new Rol(0, "Administrador"));
        arrayListRol.add(new Rol(1, "Estudiante"));
        
        return arrayListRol;
        
    }
    
    
}
