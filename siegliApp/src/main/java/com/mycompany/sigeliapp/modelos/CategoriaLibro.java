
package main.java.com.mycompany.sigeliapp.modelos;

import java.util.ArrayList;

public class CategoriaLibro {
    
    private String isbnLibro;
    private int idCategoria;

    public CategoriaLibro() {
    }

    public CategoriaLibro(String isbnLibro, int idCategoria) {
        this.isbnLibro = isbnLibro;
        this.idCategoria = idCategoria;
    }

    public String getIsbnLibro() {
        return isbnLibro;
    }

    public void setIsbnLibro(String isbnLibro) {
        this.isbnLibro = isbnLibro;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    public ArrayList<CategoriaLibro> arrayListCategoriaLibro(){
        
        ArrayList<CategoriaLibro> arrayListCategoriaLibro = new ArrayList<>();
        
        arrayListCategoriaLibro.add(new CategoriaLibro("3456", 1));
        arrayListCategoriaLibro.add(new CategoriaLibro("3456", 2));
        arrayListCategoriaLibro.add(new CategoriaLibro("56789", 3));
        
        return arrayListCategoriaLibro;
    }
}
