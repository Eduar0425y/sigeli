package main.java.com.mycompany.sigeliapp.modelos;

import java.util.ArrayList;


public class Categoria {
    private int idCategoria;
    private String nombreCategoria;

    public Categoria() {
    }

    public Categoria(int idCategoria, String nombreCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    
    
    public ArrayList<Categoria> arrayListCategoria(){
         ArrayList<Categoria> arrayListCategoria = new ArrayList<>();
         arrayListCategoria.add(new Categoria(0, "Español"));
         arrayListCategoria.add(new Categoria(1, "Matematicas"));
         arrayListCategoria.add(new Categoria(2, "Historia"));
         arrayListCategoria.add(new Categoria(3, "Politica"));
         
         return arrayListCategoria;
         
    }
}
