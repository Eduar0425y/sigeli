package main.java.com.mycompany.sigeliapp.modelos;

import java.util.ArrayList;


public class Multa {
    private int idMulta;
    private int documentoPersona;
    private int idPrestamo;
    private int valorMulta;
    private int estadoMulta;

    public Multa() {
    }

    public Multa(int idMulta, int documentoPersona, int idPrestamo, int valorMulta, int estadoMulta) {
        this.idMulta = idMulta;
        this.documentoPersona = documentoPersona;
        this.idPrestamo = idPrestamo;
        this.valorMulta = valorMulta;
        this.estadoMulta = estadoMulta;
    }


    public int getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(int idMulta) {
        this.idMulta = idMulta;
    }

    public int getDocumentoPersona() {
        return documentoPersona;
    }

    public void setDocumentoPersona(int documentoPersona) {
        this.documentoPersona = documentoPersona;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(int valorMulta) {
        this.valorMulta = valorMulta;
    }

    public int getEstadoMulta() {
        return estadoMulta;
    }

    public void setEstadoMulta(int estadoMulta) {
        this.estadoMulta = estadoMulta;
    }
    
    
}
