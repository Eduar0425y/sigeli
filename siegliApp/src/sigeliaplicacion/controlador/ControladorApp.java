package sigeliaplicacion.controlador;

import sigeliaplicacion.modelos.*;
import sigeliaplicacion.vistas.VistaApp;
import java.util.ArrayList;
//import sigeliaplicacion.dao.*;

public class ControladorApp {

    private VistaApp vistaApp;
    private Persona persona;
    private Carrera carrera;
    private Categoria categoria;
    private CategoriaLibro categoriaLibro;
    private Disponibilidad disponibilidad;
    private Libro libro;
    private Multa multa;
    private Prestamos prestamo;
    private Rol rol;
    private int documentoLogin;
    private String claveLogin;
    
    
    

    public ControladorApp() {
        this.vistaApp = new VistaApp();
        this.persona = new Persona();
        this.carrera = new Carrera();
        this.categoria = new Categoria();
        this.categoriaLibro = new CategoriaLibro();
        this.disponibilidad = new Disponibilidad();
        this.libro = new Libro();
        this.multa = new Multa();
        this.prestamo = new Prestamos();
        this.rol = new Rol();

    }

    public void inicio() {
        login(persona.arrayListPersonas());
    }

    public void login(ArrayList<Persona> arrayListPersonas) {
        documentoLogin = Integer.parseInt(vistaApp.getEntrada("Ingrese su usuario: "));
        claveLogin = vistaApp.getEntrada("Ingrese su clave: ");
        boolean opcion = true;

            for (int i = 0; i < arrayListPersonas.size(); i++) {
                if (arrayListPersonas.get(i).getDocumentoPersona() == documentoLogin) {
                    vistaApp.setTexto("Documento encontrado. Corresponde a: " + arrayListPersonas.get(i).getNombrePersona());
                    if (arrayListPersonas.get(i).getClavePersona().equals(claveLogin)) {

                        vistaApp.setTexto("Ingresó al perfil ...");

                        if (arrayListPersonas.get(i).getIdCargo() == 1) {
                            panelAdmin();
                        } else if (arrayListPersonas.get(i).getIdCargo() == 2) {
                            panelEstudiante();
                        }
                    } else {
                        vistaApp.setTextoError("Clave incorrecta");
                    }
                }
            }

    }

    public void panelAdmin() {
        vistaApp.setTexto("Perfil de administrador");

            switch (vistaApp.menuAdmin()) {
                case "1":
                    adminLibros();
                    break;
                case "2":
                    adminUsuarios();
                    break;
                case "3":
                    addLibros();
                    break;
                case "4":
                    prestamoLibros();
                    break;
                case "5":
                    pagoDeudas();
                    break;
                case "6":
                    reportes();
                    break;
                case "7":
                    opcionesCuenta();
                    break;
                default:
                    vistaApp.setTexto("No existe la opcion ingresada");
                    break;
            }

    }

    public void panelEstudiante() {
        vistaApp.setTexto("Perfil de Estudiante");
        switch (vistaApp.menuEstudiante()) {
            case 1:
                busquedaLibro();
                break;
            case 2:
                opcionesCuenta();
                break;
        }
    }

    public void adminLibros() {

        switch (vistaApp.menuAdminLibros()) {
            case 1:
                verLibros(libro.arrayListLibro(), disponibilidad.arrayListDisponibilidad());
                break;
            case 2:
                modificarLibros(libro.arrayListLibro(), disponibilidad.arrayListDisponibilidad());
                break;
            default:
                break;
        }

    }

    public void adminUsuarios() {

    }

    public void addLibros() {

    }

    public void prestamoLibros() {

    }

    public void pagoDeudas() {

    }

    public void reportes() {

    }

    public void opcionesCuenta() {

    }

    public void busquedaLibro() {

    }

    public void verLibros(ArrayList<Libro> arrayListLibro, ArrayList<Disponibilidad> arrayListDisponibilidad) {
        String[] datosLibro = new String[8];
        int cantLibros = 0;

        /*
        for(int i=0; i < arrayListLibro.size(); i++){
            
            cantLibros += 1;
           
            
            datosLibro[0] = arrayListLibro.get(i).getNombreLibro();
            datosLibro[1] = String.valueOf(arrayListLibro.get(i).getEdicionLibro());
            datosLibro[2] = arrayListLibro.get(i).getNombreAutor();
            datosLibro[3] = String.valueOf(arrayListLibro.get(i).getIsbnLibro());
            datosLibro[4] = arrayListLibro.get(i).getEstanteLibro();
            datosLibro[5] = String.valueOf(arrayListLibro.get(i).getFilaLibro());
            datosLibro[6] = String.valueOf(arrayListLibro.get(i).getAnoLibro());
            datosLibro[7] = arrayListDisponibilidad.get(arrayListLibro.get(i).getDisponibilidadLibro()).getNombreDisponibilidad();
            
            vistaApp.verLibros(datosLibro);
        }*/
        for (Libro libro : arrayListLibro) {

            cantLibros += 1;

            datosLibro[0] = libro.getNombreLibro();
            datosLibro[1] = String.valueOf(libro.getEdicionLibro());
            datosLibro[2] = libro.getNombreAutor();
            datosLibro[3] = String.valueOf(libro.getIsbnLibro());
            datosLibro[4] = libro.getEstanteLibro();
            datosLibro[5] = String.valueOf(libro.getFilaLibro());
            datosLibro[6] = String.valueOf(libro.getAnoLibro());
            datosLibro[7] = arrayListDisponibilidad.get(libro.getDisponibilidadLibro()).getNombreDisponibilidad();

            vistaApp.verLibros(datosLibro);
        }

        vistaApp.setTexto("Se mostraron " + cantLibros + " Libros");
    }

    public void modificarLibros(ArrayList<Libro> arrayListLibro, ArrayList<Disponibilidad> arrayListDisponibilidad) {

        String[] datosLibro = new String[8];
        vistaApp.getEntrada("");
        int isbn = Integer.parseInt(vistaApp.getEntrada("Ingrese el ISBN del libro: "));
        for (Libro libro : arrayListLibro) {

            if (libro.getIsbnLibro() == isbn) {
                datosLibro[0] = libro.getNombreLibro();
                datosLibro[1] = String.valueOf(libro.getEdicionLibro());
                datosLibro[2] = libro.getNombreAutor();
                datosLibro[3] = String.valueOf(libro.getIsbnLibro());
                datosLibro[4] = libro.getEstanteLibro();
                datosLibro[5] = String.valueOf(libro.getFilaLibro());
                datosLibro[6] = String.valueOf(libro.getAnoLibro());
                datosLibro[7] = arrayListDisponibilidad.get(libro.getDisponibilidadLibro()).getNombreDisponibilidad();

                vistaApp.verLibros(datosLibro);
                
                vistaApp.setTexto("***Ingrese los nuevos datos del libro***");
                libro.setNombreLibro(vistaApp.getEntrada("Ingrese el nuevo nombre del libro: "));
                libro.setNombreAutor(vistaApp.getEntrada("Ingrese el nuevo nombre del Autor: "));
                libro.setEdicionLibro(Integer.parseInt(vistaApp.getEntrada("Ingrese la nueva edición del libro: ")));
                libro.setAnoLibro(Integer.parseInt(vistaApp.getEntrada("Ingrese el año del libro: ")));
                libro.setEstanteLibro(vistaApp.getEntrada("Ingrese el estante del libro: "));
                libro.setFilaLibro(Integer.parseInt(vistaApp.getEntrada("Ingrese la fila del libro: ")));
                libro.setDisponibilidadLibro(Integer.parseInt(vistaApp.getEntrada("Ingrese la disponibilidad del libro: \n1. No disponible\n2. Disponible")) - 1);

                datosLibro[0] = libro.getNombreLibro();
                datosLibro[1] = String.valueOf(libro.getEdicionLibro());
                datosLibro[2] = libro.getNombreAutor();
                datosLibro[3] = String.valueOf(libro.getIsbnLibro());
                datosLibro[4] = libro.getEstanteLibro();
                datosLibro[5] = String.valueOf(libro.getFilaLibro());
                datosLibro[6] = String.valueOf(libro.getAnoLibro());
                datosLibro[7] = arrayListDisponibilidad.get(libro.getDisponibilidadLibro()).getNombreDisponibilidad();

                vistaApp.verLibros(datosLibro);
                    
            }
                
        }
    }
}
