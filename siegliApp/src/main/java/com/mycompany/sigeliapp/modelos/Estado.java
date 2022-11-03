package main.java.com.mycompany.sigeliapp.modelos;

import java.util.ArrayList;


public class Estado {
    private int idEstado;
    private String estado;

    public Estado() {
    }

    public Estado(int idEstado, String estado) {
        this.idEstado = idEstado;
        this.estado = estado;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public ArrayList<Estado> arrayListEstado(){
        
        ArrayList<Estado> arrayListEstado = new ArrayList<>();
        
        arrayListEstado.add(new Estado(0, "No disponible"));
        arrayListEstado.add(new Estado(1, "Disponible"));
        arrayListEstado.add(new Estado(2, "Entregado"));
        arrayListEstado.add(new Estado(3, "En deuda"));
        arrayListEstado.add(new Estado(4, "Atrasado"));
        arrayListEstado.add(new Estado(5, "Pago"));
        arrayListEstado.add(new Estado(6, "No pago"));
        
        
        return arrayListEstado;
    }
}
