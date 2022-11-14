package main.java.com.mycompany.sigeliapp.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import main.java.com.mycompany.sigeliapp.dao.*;
import main.java.com.mycompany.sigeliapp.modelos.*;
import main.java.com.mycompany.sigeliapp.vistas.*;

public class ControladorLibro  implements ActionListener, MouseListener{
    
    private VistaApp vistaApp;
    
    private adminLibros panelAdminLibros;
    
    private Persona persona;
    private Categoria categoria;
    private CategoriaLibro categoriaLibro;
    private Estado estado;
    private Libro libro;
    private Multa multa;
    private Carrera carrera;
    private Usuario usuario;
    private Prestamos prestamo;
    
    
    private IDaoCarrera iDaoCarrera;
    private IDaoCategoria iDaoCategoria;
    private IDaoCategoriaLibro iDaoCategoriaLibro;
    private IDaoEstado iDaoEstado;
    private IDaoLibro iDaoLibro;
    private IDaoMulta iDaoMulta;
    private IDaoPrestamos iDaoPrestamos;
    private IDaoPersona iDaoPersona;
    private IDaoUsuario iDaoUsuario;
    
    private int documentoLogin;
    private int cantMenorLibro = 0, cantMayorLibro = 4, cantLibrosPag = 0;

    public ControladorLibro(){
    
        this.panelAdminLibros = new adminLibros();
        
        this.vistaApp = new VistaApp();
        this.persona = new Persona();
        this.categoria = new Categoria();
        this.categoriaLibro = new CategoriaLibro();
        this.estado = new Estado();
        this.libro = new Libro();
        this.multa = new Multa();
        this.carrera = new Carrera();
        this.prestamo = new Prestamos();
        this.usuario = new Usuario();
        
        this.iDaoCarrera = new DaoCarrera();
        this.iDaoCategoria = new DaoCategoria();
        this.iDaoCategoriaLibro = new DaoCategoriaLibro();
        this.iDaoEstado = new DaoEstado();
        this.iDaoLibro = new DaoLibro();
        this.iDaoMulta = new DaoMulta();
        this.iDaoPrestamos = new DaoPrestamos();
        this.iDaoPersona = new DaoPersona();
        this.iDaoUsuario = new DaoUsuario();
        
        
        this.panelAdminLibros.btnBusqueda.addActionListener(this);
        this.panelAdminLibros.nextPagAdmin.addActionListener(this);
        this.panelAdminLibros.backPagAdmin.addActionListener(this);
        this.panelAdminLibros.btnVolverMenu.addActionListener(this);
        //this.panelAdminLibros.btnCerrarSesion.addMouseListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /*if(e.getSource() == panelAdminLibros.btnCerrarSesion){
            ControladorLogin controladorLogin = new ControladorLogin();
            cerrarPanelAdminLibros();
            controladorLogin.visibleLogin();
        }*/
    }

    @Override
    public void mousePressed(MouseEvent e) {
 
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == panelAdminLibros.btnBusqueda){
            busquedaLibro(iDaoLibro.verLibros(), iDaoCategoriaLibro.verCategoriaLibros(), iDaoCategoria.verCategorias());
        }
        else if(e.getSource() == panelAdminLibros.nextPagAdmin){
             cantMenorLibro = cantMayorLibro;
             cantMayorLibro +=4;
             verLibros(iDaoLibro.verLibros());
        }
        else if(e.getSource() == panelAdminLibros.backPagAdmin){
             
             cantMayorLibro = cantMenorLibro;
             
            if(cantMayorLibro < 5){
                cantMayorLibro = 5;
            }

            cantMenorLibro -=cantLibrosPag;
            
            if(cantMenorLibro < 0){
                cantMenorLibro = 0;
            }

            System.out.print(String.valueOf(cantMenorLibro) + " " + String.valueOf(cantMayorLibro));

           verLibros(iDaoLibro.verLibros());
           
        }
        else if(e.getSource() == panelAdminLibros.btnVolverMenu){
             cerrarPanelAdminLibros();
             visiblePanelAdmin();
        }
    }
    
    
    
    
    
    
    
    

    //funciones de libro
    
    //con DAO
    public void busquedaLibro(ArrayList<Libro> arrayListLibro, ArrayList<CategoriaLibro> arrayListCategoriaLibro, ArrayList<Categoria> arrayListCategoria) {

        ArrayList<Libro> arrayListLibroEnviar = new ArrayList<>();
        String busqueda = panelAdminLibros.txtIngresoBusqueda.getText();

        Pattern pat = Pattern.compile(".*" + busqueda.toLowerCase() + ".*");
        
        vistaApp.setTexto("Resultados para \"" + busqueda + "\"");
        
        for(Libro libro : arrayListLibro){
            for( Categoria categoria: arrayListCategoria){
                Matcher mat5 = pat.matcher(categoria.getNombreCategoria().toLowerCase());
                if(mat5.matches()){
                    for(CategoriaLibro categoriaLibro : arrayListCategoriaLibro){
                        if(libro.getIsbnLibro().equals(categoriaLibro.getIsbnLibro()) && categoriaLibro.getIdCategoria() == categoria.getIdCategoria()){
                            arrayListLibroEnviar.add(libro);
                        }
                    }
                }
            }
            Matcher mat = pat.matcher(libro.getNombreLibro().toLowerCase());
            Matcher mat2 = pat.matcher(libro.getNombreAutor().toLowerCase());
            Matcher mat3 = pat.matcher(libro.getIsbnLibro());
            Matcher mat4 = pat.matcher(String.valueOf(libro.getAnoLibro()));
            if(mat.matches() || mat2.matches() || mat3.matches() || mat4.matches()){
                arrayListLibroEnviar.add(libro);
            }
            
        }
        
        setDatosLibro(arrayListLibroEnviar, arrayListLibro.size(), busqueda);
 
    }

    //con DAO
    public void verLibros(ArrayList<Libro> arrayListLibro) {
        
        ArrayList<Libro> arrayListLibroEnviar = new ArrayList<>();
        
        int cantidad=0;

        for(Libro libro : arrayListLibro){
            
            if(cantidad <= cantMayorLibro  && cantidad >= cantMenorLibro){
                arrayListLibroEnviar.add(libro);
                cantMenorLibro +=1;
            }
            if(cantMayorLibro > arrayListLibro.size()){
                cantMayorLibro = arrayListLibro.size();
            }

            cantidad +=1;
            
        }
        
        setDatosLibro(arrayListLibroEnviar, arrayListLibro.size(), "");

    }

    //con DAO
    public void modificarLibros(ArrayList<Libro> arrayListLibro, ArrayList<Estado> arrayListEstado) {

        vistaApp.getEntrada("");
        String isbn = vistaApp.getEntrada("Ingrese el ISBN del libro: ");
        int opcion = vistaApp.modificarLibro();
        
        if(opcion == 1){
            for (Libro libro : arrayListLibro) {

                if (libro.getIsbnLibro().equals(isbn)) {

                        vistaApp.setTexto("***Ingrese los nuevos datos del libro***");
                        libro.setNombreLibro(vistaApp.getEntrada("Ingrese el nuevo nombre del libro: "));
                        libro.setNombreAutor(vistaApp.getEntrada("Ingrese el nuevo nombre del Autor: "));
                        libro.setEdicionLibro(Integer.parseInt(vistaApp.getEntrada("Ingrese la nueva edición del libro: ")));
                        libro.setAnoLibro(Integer.parseInt(vistaApp.getEntrada("Ingrese el año del libro: ")));
                        libro.setEstanteLibro(vistaApp.getEntrada("Ingrese el estante del libro: "));
                        libro.setFilaLibro(Integer.parseInt(vistaApp.getEntrada("Ingrese la fila del libro: ")));
                        libro.setIdEstado(Integer.parseInt(vistaApp.getEntrada("Ingrese el estado del libro: \n1. No disponible\n2. Disponible")) - 1);

                        iDaoLibro.modificarLibro(libro, isbn);

                }

            }

        }
        else if(opcion == 2){                
            
            for(int i=0; i<arrayListLibro.size(); i++){
                
                iDaoLibro.eliminarLibro(isbn);
                
            }

        }
                
        else{
            
        }
        
    }
    

    public void inicio(int documento, String nombre){
        this.documentoLogin = documento;
        panelAdminLibros.txtNombrePersona.setText(nombre);
        panelAdminLibros.txtCargo.setText("Administrador");
        verLibros(iDaoLibro.verLibros());
        
        visiblePanelAdminLibros();
    }
    
    public void setDatosLibro(ArrayList<Libro> arrayListLibro, int cantLibros, String busqueda){
        if(arrayListLibro.size() == 0){
           panelAdminLibros.panel1.setVisible(false);
           panelAdminLibros.panel2.setVisible(false);
           panelAdminLibros.panel3.setVisible(false);
           panelAdminLibros.panel4.setVisible(false);
           panelAdminLibros.panel5.setVisible(false);
           busqueda += "\" - No se han encontrado resultados";
        }
        else if(arrayListLibro.size() == 1){
            //paneles
           panelAdminLibros.panel1.setVisible(true);
           panelAdminLibros.panel2.setVisible(false);
           panelAdminLibros.panel3.setVisible(false);
           panelAdminLibros.panel4.setVisible(false);
           panelAdminLibros.panel5.setVisible(false);
           //nombre libro
            panelAdminLibros.txtNombreLibro1.setText(arrayListLibro.get(0).getNombreLibro());
            //nombre Autor
            panelAdminLibros.txtNombreAutor1.setText(arrayListLibro.get(0).getNombreAutor());
        }
        else if(arrayListLibro.size() == 2){
            //paneles
           panelAdminLibros.panel1.setVisible(true);
           panelAdminLibros.panel2.setVisible(true);
           panelAdminLibros.panel3.setVisible(false);
           panelAdminLibros.panel4.setVisible(false);
           panelAdminLibros.panel5.setVisible(false);
            //nombre libro
            panelAdminLibros.txtNombreLibro1.setText(arrayListLibro.get(0).getNombreLibro());
            panelAdminLibros.txtNombreLibro2.setText(arrayListLibro.get(1).getNombreLibro());
            //nombre Autor
            panelAdminLibros.txtNombreAutor1.setText(arrayListLibro.get(0).getNombreAutor());
            panelAdminLibros.txtNombreAutor2.setText(arrayListLibro.get(1).getNombreAutor());
        }
        else if(arrayListLibro.size() == 3){
            //paneles
           panelAdminLibros.panel1.setVisible(true);
           panelAdminLibros.panel2.setVisible(true);
           panelAdminLibros.panel3.setVisible(true);
           panelAdminLibros.panel4.setVisible(false);
           panelAdminLibros.panel5.setVisible(false);
            //nombre libro
            panelAdminLibros.txtNombreLibro1.setText(arrayListLibro.get(0).getNombreLibro());
            panelAdminLibros.txtNombreLibro2.setText(arrayListLibro.get(1).getNombreLibro());
            panelAdminLibros.txtNombreLibro3.setText(arrayListLibro.get(2).getNombreLibro());
            //nombre Autor
            panelAdminLibros.txtNombreAutor1.setText(arrayListLibro.get(0).getNombreAutor());
            panelAdminLibros.txtNombreAutor2.setText(arrayListLibro.get(1).getNombreAutor());
            panelAdminLibros.txtNombreAutor3.setText(arrayListLibro.get(2).getNombreAutor());
        }
        else if(arrayListLibro.size() == 4){
            //paneles
           panelAdminLibros.panel1.setVisible(true);
           panelAdminLibros.panel2.setVisible(true);
           panelAdminLibros.panel3.setVisible(true);
           panelAdminLibros.panel4.setVisible(true);
           panelAdminLibros.panel5.setVisible(false);
            //nombre libro
            panelAdminLibros.txtNombreLibro1.setText(arrayListLibro.get(0).getNombreLibro());
            panelAdminLibros.txtNombreLibro2.setText(arrayListLibro.get(1).getNombreLibro());
            panelAdminLibros.txtNombreLibro3.setText(arrayListLibro.get(2).getNombreLibro());
            panelAdminLibros.txtNombreLibro4.setText(arrayListLibro.get(3).getNombreLibro());
            //nombre Autor
            panelAdminLibros.txtNombreAutor1.setText(arrayListLibro.get(0).getNombreAutor());
            panelAdminLibros.txtNombreAutor2.setText(arrayListLibro.get(1).getNombreAutor());
            panelAdminLibros.txtNombreAutor3.setText(arrayListLibro.get(2).getNombreAutor());
            panelAdminLibros.txtNombreAutor4.setText(arrayListLibro.get(3).getNombreAutor());
        }
        else if(arrayListLibro.size() == 5){
            //paneles
           panelAdminLibros.panel1.setVisible(true);
           panelAdminLibros.panel2.setVisible(true);
           panelAdminLibros.panel3.setVisible(true);
           panelAdminLibros.panel4.setVisible(true);
           panelAdminLibros.panel5.setVisible(true);
            //nombre libro
            panelAdminLibros.txtNombreLibro1.setText(arrayListLibro.get(0).getNombreLibro());
            panelAdminLibros.txtNombreLibro2.setText(arrayListLibro.get(1).getNombreLibro());
            panelAdminLibros.txtNombreLibro3.setText(arrayListLibro.get(2).getNombreLibro());
            panelAdminLibros.txtNombreLibro4.setText(arrayListLibro.get(3).getNombreLibro());
            panelAdminLibros.txtNombreLibro5.setText(arrayListLibro.get(4).getNombreLibro());
            //nombre Autor
            panelAdminLibros.txtNombreAutor1.setText(arrayListLibro.get(0).getNombreAutor());
            panelAdminLibros.txtNombreAutor2.setText(arrayListLibro.get(1).getNombreAutor());
            panelAdminLibros.txtNombreAutor3.setText(arrayListLibro.get(2).getNombreAutor());
            panelAdminLibros.txtNombreAutor4.setText(arrayListLibro.get(3).getNombreAutor());
            panelAdminLibros.txtNombreAutor4.setText(arrayListLibro.get(4).getNombreAutor());
        }
        else{
            panelAdminLibros.txtNombreLibro1.setText("");
            panelAdminLibros.txtNombreLibro2.setText("");
            panelAdminLibros.txtNombreLibro3.setText("");
            panelAdminLibros.txtNombreLibro4.setText("");
            panelAdminLibros.txtNombreLibro5.setText("");
        }
        
        if(!busqueda.equals("")){
            panelAdminLibros.txtBusquedaAdmin.setText("Se muestran resultados para \"" + busqueda + "\"");
        }
        else{
            panelAdminLibros.txtBusquedaAdmin.setText("");
        }
        cantLibrosPag = arrayListLibro.size();
        panelAdminLibros.txtCantLibroAdmin.setText("Se mostraron " + arrayListLibro.size() + " libros de: " + cantLibros);
        
    }
    
     public void cerrarPanelAdminLibros(){
        panelAdminLibros.setVisible(false);
    }

    public void visiblePanelAdminLibros(){
        panelAdminLibros.setTitle("Panel Administrar Libros - Sigeli");
        panelAdminLibros.setLocationRelativeTo(null);
        panelAdminLibros.setVisible(true);
    }
    
    public void visiblePanelAdmin(){
        ControladorPanelAdmin controladorPanelAdmin = new ControladorPanelAdmin();
        
        controladorPanelAdmin.inicio(documentoLogin, iDaoPersona.verPersonas());
    }
    
}
