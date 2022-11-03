package sigeliaplicacion.modelos;

import java.util.ArrayList;


public class Multa {
    private int idMulta;
    private int idPersona;
    private int isbnLibro;
    private int valorMulta;

    public Multa() {
    }

    public Multa(int idMulta, int idPersona, int isbnLibro, int valorMulta) {
        this.idMulta = idMulta;
        this.idPersona = idPersona;
        this.isbnLibro = isbnLibro;
        this.valorMulta = valorMulta;
    }

    public int getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(int idMulta) {
        this.idMulta = idMulta;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIsbnLibro() {
        return isbnLibro;
    }

    public void setIsbnLibro(int isbnLibro) {
        this.isbnLibro = isbnLibro;
    }

    public int getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(int valorMulta) {
        this.valorMulta = valorMulta;
    }
    
    
    public ArrayList<Multa> arrayListMulta(){
        
        ArrayList<Multa> arrayListMulta = new ArrayList<>();
        arrayListMulta.add(new Multa(0, 1094045112, 3456, 25000));
        arrayListMulta.add(new Multa(1, 109255868, 3456, 25000));
        
        return arrayListMulta;
    }
    
}
