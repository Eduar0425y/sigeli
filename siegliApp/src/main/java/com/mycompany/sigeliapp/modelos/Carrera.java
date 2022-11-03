
package com.mycompany.sigeliapp.modelos;

import java.util.ArrayList;

public class Carrera {
    private int idCarrera;
    private String carrera;

    public Carrera() {
    }

    public Carrera(int idCarrera, String carrera) {
        this.idCarrera = idCarrera;
        this.carrera = carrera;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public ArrayList<Carrera> arrayListCarrera(){

        ArrayList<Carrera> arrayListCarrera = new ArrayList<>();
        arrayListCarrera.add(new Carrera(0, " "));
        arrayListCarrera.add(new Carrera(1, "Ingenieria de software"));
        arrayListCarrera.add(new Carrera(2, "Curso de modas"));
        arrayListCarrera.add(new Carrera(3, "Curso corto de Diseño gráfico"));
        arrayListCarrera.add(new Carrera(4, "Curso corto de matematica basica (finanzas)"));
        
        return arrayListCarrera;

    }

        

}
