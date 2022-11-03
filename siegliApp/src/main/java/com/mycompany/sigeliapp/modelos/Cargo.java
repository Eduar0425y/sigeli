
package main.java.com.mycompany.sigeliapp.modelos;

public class Cargo {
    private int idcargo;
    private String nombreCargo;

    public Cargo() {
    }

    public Cargo(int idcargo, String nombreCargo) {
        this.idcargo = idcargo;
        this.nombreCargo = nombreCargo;
    }

    public int getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(int idcargo) {
        this.idcargo = idcargo;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }




}
