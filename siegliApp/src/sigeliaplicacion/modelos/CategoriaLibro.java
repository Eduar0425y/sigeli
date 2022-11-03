package sigeliaplicacion.modelos;

public class CategoriaLibro {
    private int isbnLibro;
    private int idCategoria;

    public CategoriaLibro() {
    }

    public CategoriaLibro(int isbnLibro, int idCategoria) {
        this.isbnLibro = isbnLibro;
        this.idCategoria = idCategoria;
    }

    public int getIsbnLibro() {
        return isbnLibro;
    }

    public void setIsbnLibro(int isbnLibro) {
        this.isbnLibro = isbnLibro;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    
}
