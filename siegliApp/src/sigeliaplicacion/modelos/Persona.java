
package sigeliaplicacion.modelos;

import java.util.ArrayList;


public class Persona {
    private int documentoPersona;
    private String clavePersona;
    private String nombrePersona;
    private String apellidoPersona;
    private int idCargo;
    private int idCarrera;
    private String emailPersona;

    public Persona() {
    }

    public Persona(int documentoPersona, String clavePersona, String nombrePersona, String apellidoPersona, int idCargo, int idCarrera, String emailPersona) {
        this.documentoPersona = documentoPersona;
        this.clavePersona = clavePersona;
        this.nombrePersona = nombrePersona;
        this.apellidoPersona = apellidoPersona;
        this.idCargo = idCargo;
        this.idCarrera = idCarrera;
        this.emailPersona = emailPersona;
    }
    
    

    public int getDocumentoPersona() {
        return documentoPersona;
    }

    public void setDocumentoPersona(int documentoPersona) {
        this.documentoPersona = documentoPersona;
    }

    public String getClavePersona() {
        return clavePersona;
    }

    public void setClavePersona(String clavePersona) {
        this.clavePersona = clavePersona;
    }

    public String getApellidoPersona() {
        return apellidoPersona;
    }

    public void setApellidoPersona(String apellidoPersona) {
        this.apellidoPersona = apellidoPersona;
    }
    
    

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getEmailPersona() {
        return emailPersona;
    }

    public void setEmailPersona(String emailPersona) {
        this.emailPersona = emailPersona;
    }
    
    public ArrayList<Persona> arrayListPersonas(){
        
        ArrayList<Persona> arrayListPersonas = new ArrayList<>();
        arrayListPersonas.add(new Persona(1094045112,"eduar", "Eduar", "Avendaño", 1, 0, "xavieravendano9@gmail.com"));
        arrayListPersonas.add(new Persona(1092525868,"zharick", "Zharick", "Barrera", 2, 2, "xavieravendano9@gmail.com"));
        
        return arrayListPersonas;
    }
    
    
}
