package com.mycompany.sigeliapp.modelos;

import java.util.ArrayList;


public class Persona {
    private int documentoPersona;
    private String nombrePersona;
    private int idCarrera;
    private String emailPersona;
    private String telefono;

    public Persona() {
    }

    public Persona(int documentoPersona, String nombrePersona, int idCarrera, String emailPersona, String telefono) {
        this.documentoPersona = documentoPersona;
        this.nombrePersona = nombrePersona;
        this.idCarrera = idCarrera;
        this.emailPersona = emailPersona;
        this.telefono = telefono;
    }

    public int getDocumentoPersona() {
        return documentoPersona;
    }

    public void setDocumentoPersona(int documentoPersona) {
        this.documentoPersona = documentoPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    
    public ArrayList<Persona> arrayListPersonas(){
        
        ArrayList<Persona> arrayListPersonas = new ArrayList<>();
        arrayListPersonas.add(new Persona(1094045112, "eduar", 0, "xavieravendano9@gmail.com", "3115204339"));
        arrayListPersonas.add(new Persona(1092525868, "zharick", 1, "xavieravendano9@gmail.com", "3202270366"));
        
        return arrayListPersonas;
    }
}
