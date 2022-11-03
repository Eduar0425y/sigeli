package sigeliaplicacion.modelos;

import java.sql.Date;
import java.util.ArrayList;

public class Prestamos {
    private int idPrestamo;
    private int idPersona;
    private int isbnLibro;
    private Date fechaPrestamo;
    private Date fechaEntrega;

    public Prestamos() {
    }

    public Prestamos(int idPrestamo, int idPersona, int isbnLibro, Date fechaPrestamo, Date fechaEntrega) {
        this.idPrestamo = idPrestamo;
        this.idPersona = idPersona;
        this.isbnLibro = isbnLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaEntrega = fechaEntrega;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
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

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    
    public ArrayList<Prestamos> arrayListPrestamos(){
        ArrayList<Prestamos> arrayListPrestamo = new ArrayList<>();
        arrayListPrestamo.add(new Prestamos(0, 1094045112, 3456, Date.valueOf("2022-05-20"), Date.valueOf("2022-06-20")));
        
        return arrayListPrestamo;
    }
    
    
}
