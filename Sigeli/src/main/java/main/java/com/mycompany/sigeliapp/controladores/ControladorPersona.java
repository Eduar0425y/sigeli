/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.mycompany.sigeliapp.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import main.java.com.mycompany.sigeliapp.dao.DaoPersona;
import main.java.com.mycompany.sigeliapp.dao.IDaoPersona;
import main.java.com.mycompany.sigeliapp.modelos.Carrera;
import main.java.com.mycompany.sigeliapp.modelos.Persona;
import main.java.com.mycompany.sigeliapp.modelos.utiles.Portapapeles;
import main.java.com.mycompany.sigeliapp.vistas.panelAdminUsuarios;

/**
 *
 * @author Eduar Xavier
 */
public class ControladorPersona implements ActionListener, MouseListener{

    private panelAdminUsuarios panelAdminUsuario;

    private IDaoPersona iDaoPersona;
    
    private int documentoLogin;
    private String nombre;
    private boolean busqueda = false;
    private int cantMenorPersona = 0, cantMayorPersona = 4, cantLibrosPag = 0;

    
    public ControladorPersona() {
        this.panelAdminUsuario = new panelAdminUsuarios();
        

        this.iDaoPersona = new DaoPersona();
        
        //Acciones del panel admin Usuarios
        this.panelAdminUsuario.btnPegar.addActionListener(this);
        this.panelAdminUsuario.btnBusqueda.addActionListener(this);
        this.panelAdminUsuario.nextPagAdmin.addActionListener(this);
        this.panelAdminUsuario.backPagAdmin.addActionListener(this);
        
        
        this.panelAdminUsuario.btnExtenPanel.addMouseListener(this);
        this.panelAdminUsuario.btnExtenPanelOff.addMouseListener(this);
        this.panelAdminUsuario.btnVolverMenu.addMouseListener(this);
        this.panelAdminUsuario.btnCerrarSesion.addMouseListener(this);
        
        this.panelAdminUsuario.panel1.addMouseListener(this);
        this.panelAdminUsuario.panel2.addMouseListener(this);
        this.panelAdminUsuario.panel3.addMouseListener(this);
        this.panelAdminUsuario.panel4.addMouseListener(this);
        this.panelAdminUsuario.panel5.addMouseListener(this);
        
        //Acciones del panel extendido
        
        this.panelAdminUsuario.adminLibros.addMouseListener(this);
        
        
        this.panelAdminUsuario.adminLibros.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == panelAdminUsuario.btnBusqueda){
            
            buscarUsuario(iDaoPersona.verPersonas());
            
        }
        else if(e.getSource() == panelAdminUsuario.btnPegar){
            try {
                panelAdminUsuario.txtIngresoBusqueda.setText(Portapapeles.get());
            } catch (Exception ex) {
                Logger.getLogger(ControladorLibro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if(e.getSource() == panelAdminUsuario.nextPagAdmin){
             cantMenorPersona = cantMayorPersona;
             cantMayorPersona +=4;
             verPersonas(iDaoPersona.verPersonas());
        }
        else if(e.getSource() == panelAdminUsuario.backPagAdmin){
             
             cantMayorPersona = cantMenorPersona;
             
            if(cantMayorPersona < 4){
                cantMayorPersona = 4;
            }

            cantMenorPersona -=4;
            
           if(cantMenorPersona < 0 && cantMenorPersona > 4){
                cantMenorPersona = 0;
            }

           verPersonas(iDaoPersona.verPersonas());
           
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource() == panelAdminUsuario.btnExtenPanel){
            panelAdminUsuario.panelExten.setVisible(true);
            panelAdminUsuario.btnVolverMenu.setVisible(false);
        }
        
        else if(e.getSource() == panelAdminUsuario.btnExtenPanelOff){
            panelAdminUsuario.btnVolverMenu.setVisible(true);
            panelAdminUsuario.panelExten.setVisible(false);
            
        }
        else if(e.getSource() == panelAdminUsuario.btnVolverMenu){
            cerrarPanelAdminUsuario();
            visiblePanelAdmin();
        }
        
        
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
    
    
    
    
    public void verPersonas(ArrayList<Persona> arrayListPersona) {
        
        ArrayList<Persona> arrayListPersonaEnviar = new ArrayList<>();
        int cant = cantMenorPersona, cantPersonas= 0;


        for(Persona persona : arrayListPersona){

            if(cantMayorPersona > arrayListPersona.size()){
            cantMayorPersona = arrayListPersona.size();
            }
            if(cantMenorPersona < 0){
                cantMenorPersona = 0;
            }
            if(cantPersonas <= cantMayorPersona  && cantPersonas >= cantMenorPersona){
                arrayListPersonaEnviar.add(persona);
                cantMenorPersona +=1;
            }
            cantPersonas +=1;

        }
        
        cantMenorPersona = cant;
        
        setDatosLibro(arrayListPersonaEnviar, arrayListPersonaEnviar.size(), "");
        this.busqueda = false;
        

    }
    
    public void buscarUsuario(ArrayList<Persona> arrayListPersona){
            
        String busqueda = panelAdminUsuario.txtIngresoBusqueda.getText();

        Pattern pat = Pattern.compile(".*" + busqueda.toLowerCase() + ".*");
        if(busqueda.equals("")){
            cantMayorPersona =4;
            cantMenorPersona = 0;
            this.busqueda = false;
            verPersonas(iDaoPersona.verPersonas());  
        }
        else{
           ArrayList<Persona> arrayListPersonaEnviar = new ArrayList<>();
        
            for(Persona persona : arrayListPersona){
                Matcher mat = pat.matcher(String.valueOf(persona.getDocumentoPersona()));
                Matcher mat2 = pat.matcher(persona.getNombrePersona().toLowerCase());
                if(mat.matches() || mat2.matches()){

                    if(arrayListPersona.size() <= 4){
                        arrayListPersonaEnviar.add(persona);
                    }

                }
            } 
            
            setDatosLibro(arrayListPersonaEnviar, arrayListPersonaEnviar.size(), busqueda);
        }
        
        
        
    }
    
    
    
    
    
    
    public void inicio(int documento, String nombre){
        this.documentoLogin = documento;
        this.nombre = nombre;
        panelAdminUsuario.txtNombrePersona.setText(nombre);
        panelAdminUsuario.txtNombrePersona1.setText(nombre);
        panelAdminUsuario.panelExten.setVisible(false);
        verPersonas(iDaoPersona.verPersonas());
        
        visiblePanelAdminUsuario();
    }
    
    public void visiblePanelAdminLibros(){
        ControladorLibro controladorLibro = new ControladorLibro();
        
        controladorLibro.inicio(documentoLogin, nombre);
    }
    
    public void visiblePanelAdmin(){
        ControladorPanelAdmin controladorPanelAdmin = new ControladorPanelAdmin();
        
        controladorPanelAdmin.inicio(documentoLogin, iDaoPersona.verPersonas());
    }
    
    public void visiblePanelAdminUsuario(){
        panelAdminUsuario.setTitle("Panel Administrar Usuarios - Sigeli");
        this.busqueda = false;
        panelAdminUsuario.txtBusquedaAdmin.setText("");
        panelAdminUsuario.setLocationRelativeTo(null);
        panelAdminUsuario.setVisible(true);
    }
    
    public void cerrarPanelAdminUsuario(){
        panelAdminUsuario.setVisible(false);
    }
    
    
    
    
     public void setDatosLibro(ArrayList<Persona> arrayListPersona, int cantPersonas, String busqueda){
        
        if(this.busqueda){
            panelAdminUsuario.nextPagAdmin.setVisible(false);
            panelAdminUsuario.backPagAdmin.setVisible(false);
        }
        else{
            panelAdminUsuario.nextPagAdmin.setVisible(true);
            panelAdminUsuario.backPagAdmin.setVisible(true);
        }
        
        if(arrayListPersona.size() == 0){
           panelAdminUsuario.panel1.setVisible(false);
           panelAdminUsuario.panel2.setVisible(false);
           panelAdminUsuario.panel3.setVisible(false);
           panelAdminUsuario.panel4.setVisible(false);
           panelAdminUsuario.panel5.setVisible(false);
           busqueda += "\" - No se han encontrado resultados";
        }
        else if(arrayListPersona.size() == 1){
            panelAdminUsuario.nextPagAdmin.setVisible(false);
            if(cantMenorPersona == 0){
                panelAdminUsuario.backPagAdmin.setVisible(false);
            }
            //paneles
           panelAdminUsuario.panel1.setVisible(true);
           panelAdminUsuario.panel2.setVisible(false);
           panelAdminUsuario.panel3.setVisible(false);
           panelAdminUsuario.panel4.setVisible(false);
           panelAdminUsuario.panel5.setVisible(false);
           //nombre
           panelAdminUsuario.txtNombre1.setText(arrayListPersona.get(0).getNombrePersona());
           //documento
           panelAdminUsuario.txtNombreAutor1.setText("Documento: " +arrayListPersona.get(0).getDocumentoPersona());
           //email
           panelAdminUsuario.txtAnoLibro1.setText("Email: " + String.valueOf(arrayListPersona.get(0).getEmailPersona()));
           //telefono
           panelAdminUsuario.txtDisponibilidadLibro1.setText("Telefono: " + arrayListPersona.get(0).getTelefono() );
           
        }
        else if(arrayListPersona.size() == 2){
            panelAdminUsuario.nextPagAdmin.setVisible(false);
            if(cantMenorPersona == 0){
                panelAdminUsuario.backPagAdmin.setVisible(false);
            }
            //paneles
           panelAdminUsuario.panel1.setVisible(true);
           panelAdminUsuario.panel2.setVisible(true);
           panelAdminUsuario.panel3.setVisible(false);
           panelAdminUsuario.panel4.setVisible(false);
           panelAdminUsuario.panel5.setVisible(false);
           //nombre
           panelAdminUsuario.txtNombre1.setText(arrayListPersona.get(0).getNombrePersona());
           panelAdminUsuario.txtNombre2.setText(arrayListPersona.get(1).getNombrePersona());
           //documento
           panelAdminUsuario.txtNombreAutor1.setText("Documento: " +arrayListPersona.get(0).getDocumentoPersona());
           panelAdminUsuario.txtNombreAutor2.setText("Documento: " +arrayListPersona.get(1).getDocumentoPersona());
           //email
           panelAdminUsuario.txtAnoLibro1.setText("Email: " + String.valueOf(arrayListPersona.get(0).getEmailPersona()));
           panelAdminUsuario.txtAnoLibro2.setText("Email: " + String.valueOf(arrayListPersona.get(1).getEmailPersona()));
           //telefono
           panelAdminUsuario.txtDisponibilidadLibro1.setText("Telefono: " + arrayListPersona.get(0).getTelefono() );
           panelAdminUsuario.txtDisponibilidadLibro2.setText("Telefono: " + arrayListPersona.get(1).getTelefono() );
        }
        else if(arrayListPersona.size() == 3){
            panelAdminUsuario.nextPagAdmin.setVisible(false);
            if(cantMenorPersona == 0){
                panelAdminUsuario.backPagAdmin.setVisible(false);
            }
            //paneles
           panelAdminUsuario.panel1.setVisible(true);
           panelAdminUsuario.panel2.setVisible(true);
           panelAdminUsuario.panel3.setVisible(true);
           panelAdminUsuario.panel4.setVisible(false);
           panelAdminUsuario.panel5.setVisible(false);
           //nombre
           panelAdminUsuario.txtNombre1.setText(arrayListPersona.get(0).getNombrePersona());
           panelAdminUsuario.txtNombre2.setText(arrayListPersona.get(1).getNombrePersona());
           panelAdminUsuario.txtNombre3.setText(arrayListPersona.get(2).getNombrePersona());
           //documento
           panelAdminUsuario.txtNombreAutor1.setText("Documento: " +arrayListPersona.get(0).getDocumentoPersona());
           panelAdminUsuario.txtNombreAutor2.setText("Documento: " +arrayListPersona.get(1).getDocumentoPersona());
           panelAdminUsuario.txtNombreAutor3.setText("Documento: " +arrayListPersona.get(2).getDocumentoPersona());
           //email
           panelAdminUsuario.txtAnoLibro1.setText("Email: " + String.valueOf(arrayListPersona.get(0).getEmailPersona()));
           panelAdminUsuario.txtAnoLibro2.setText("Email: " + String.valueOf(arrayListPersona.get(1).getEmailPersona()));
           panelAdminUsuario.txtAnoLibro3.setText("Email: " + String.valueOf(arrayListPersona.get(2).getEmailPersona()));
           //telefono
           panelAdminUsuario.txtDisponibilidadLibro1.setText("Telefono: " + arrayListPersona.get(0).getTelefono() );
           panelAdminUsuario.txtDisponibilidadLibro2.setText("Telefono: " + arrayListPersona.get(1).getTelefono() );
           panelAdminUsuario.txtDisponibilidadLibro3.setText("Telefono: " + arrayListPersona.get(2).getTelefono() );
        }
        else if(arrayListPersona.size() == 4){
            panelAdminUsuario.nextPagAdmin.setVisible(false);
            if(cantMenorPersona == 0){
                panelAdminUsuario.backPagAdmin.setVisible(false);
            }
            //paneles
           panelAdminUsuario.panel1.setVisible(true);
           panelAdminUsuario.panel2.setVisible(true);
           panelAdminUsuario.panel3.setVisible(true);
           panelAdminUsuario.panel4.setVisible(true);
           panelAdminUsuario.panel5.setVisible(false);
           //nombre
           panelAdminUsuario.txtNombre1.setText(arrayListPersona.get(0).getNombrePersona());
           panelAdminUsuario.txtNombre2.setText(arrayListPersona.get(1).getNombrePersona());
           panelAdminUsuario.txtNombre3.setText(arrayListPersona.get(2).getNombrePersona());
           panelAdminUsuario.txtNombre4.setText(arrayListPersona.get(3).getNombrePersona());
           //documento
           panelAdminUsuario.txtNombreAutor1.setText("Documento: " +arrayListPersona.get(0).getDocumentoPersona());
           panelAdminUsuario.txtNombreAutor2.setText("Documento: " +arrayListPersona.get(1).getDocumentoPersona());
           panelAdminUsuario.txtNombreAutor3.setText("Documento: " +arrayListPersona.get(2).getDocumentoPersona());
           panelAdminUsuario.txtNombreAutor4.setText("Documento: " +arrayListPersona.get(3).getDocumentoPersona());
           //email
           panelAdminUsuario.txtAnoLibro1.setText("Email: " + String.valueOf(arrayListPersona.get(0).getEmailPersona()));
           panelAdminUsuario.txtAnoLibro2.setText("Email: " + String.valueOf(arrayListPersona.get(1).getEmailPersona()));
           panelAdminUsuario.txtAnoLibro3.setText("Email: " + String.valueOf(arrayListPersona.get(2).getEmailPersona()));
           panelAdminUsuario.txtAnoLibro4.setText("Email: " + String.valueOf(arrayListPersona.get(3).getEmailPersona()));
           //telefono
           panelAdminUsuario.txtDisponibilidadLibro1.setText("Telefono: " + arrayListPersona.get(0).getTelefono() );
           panelAdminUsuario.txtDisponibilidadLibro2.setText("Telefono: " + arrayListPersona.get(1).getTelefono() );
           panelAdminUsuario.txtDisponibilidadLibro3.setText("Telefono: " + arrayListPersona.get(2).getTelefono() );
           panelAdminUsuario.txtDisponibilidadLibro4.setText("Telefono: " + arrayListPersona.get(4).getTelefono() );
        }
        else if(arrayListPersona.size() == 5){
            panelAdminUsuario.nextPagAdmin.setVisible(true);
            if(this.busqueda){
                panelAdminUsuario.nextPagAdmin.setVisible(false);
                panelAdminUsuario.backPagAdmin.setVisible(false);
            }
            if(cantMenorPersona == 0){
                panelAdminUsuario.backPagAdmin.setVisible(false);
            }
            //paneles
           panelAdminUsuario.panel1.setVisible(true);
           panelAdminUsuario.panel2.setVisible(true);
           panelAdminUsuario.panel3.setVisible(true);
           panelAdminUsuario.panel4.setVisible(true);
           panelAdminUsuario.panel5.setVisible(true);
           //nombre
           panelAdminUsuario.txtNombre1.setText(arrayListPersona.get(0).getNombrePersona());
           panelAdminUsuario.txtNombre2.setText(arrayListPersona.get(1).getNombrePersona());
           panelAdminUsuario.txtNombre3.setText(arrayListPersona.get(2).getNombrePersona());
           panelAdminUsuario.txtNombre4.setText(arrayListPersona.get(3).getNombrePersona());
           panelAdminUsuario.txtNombre5.setText(arrayListPersona.get(4).getNombrePersona());
           //documento
           panelAdminUsuario.txtNombreAutor1.setText("Documento: " +arrayListPersona.get(0).getDocumentoPersona());
           panelAdminUsuario.txtNombreAutor2.setText("Documento: " +arrayListPersona.get(1).getDocumentoPersona());
           panelAdminUsuario.txtNombreAutor3.setText("Documento: " +arrayListPersona.get(2).getDocumentoPersona());
           panelAdminUsuario.txtNombreAutor4.setText("Documento: " +arrayListPersona.get(3).getDocumentoPersona());
           panelAdminUsuario.txtNombreAutor5.setText("Documento: " +arrayListPersona.get(4).getDocumentoPersona());
           //email
           panelAdminUsuario.txtAnoLibro1.setText("Email: " + String.valueOf(arrayListPersona.get(0).getEmailPersona()));
           panelAdminUsuario.txtAnoLibro2.setText("Email: " + String.valueOf(arrayListPersona.get(1).getEmailPersona()));
           panelAdminUsuario.txtAnoLibro3.setText("Email: " + String.valueOf(arrayListPersona.get(2).getEmailPersona()));
           panelAdminUsuario.txtAnoLibro4.setText("Email: " + String.valueOf(arrayListPersona.get(3).getEmailPersona()));
           panelAdminUsuario.txtAnoLibro5.setText("Email: " + String.valueOf(arrayListPersona.get(4).getEmailPersona()));
           //telefono
           panelAdminUsuario.txtDisponibilidadLibro1.setText("Telefono: " + arrayListPersona.get(0).getTelefono() );
           panelAdminUsuario.txtDisponibilidadLibro2.setText("Telefono: " + arrayListPersona.get(1).getTelefono() );
           panelAdminUsuario.txtDisponibilidadLibro3.setText("Telefono: " + arrayListPersona.get(2).getTelefono() );
           panelAdminUsuario.txtDisponibilidadLibro4.setText("Telefono: " + arrayListPersona.get(4).getTelefono() );
           panelAdminUsuario.txtDisponibilidadLibro5.setText("Telefono: " + arrayListPersona.get(5).getTelefono() );
        }
        else{
           panelAdminUsuario.panel1.setVisible(false);
           panelAdminUsuario.panel2.setVisible(false);
           panelAdminUsuario.panel3.setVisible(false);
           panelAdminUsuario.panel4.setVisible(false);
           panelAdminUsuario.panel5.setVisible(false);
        }
        
        if(!busqueda.equals("")){
            panelAdminUsuario.txtBusquedaAdmin.setText("Se muestran resultados para \"" + busqueda + "\"");
        }
        else{
            panelAdminUsuario.txtBusquedaAdmin.setText("");
        }
        cantLibrosPag = arrayListPersona.size();
        panelAdminUsuario.txtCantLibroAdmin.setText("Se mostraron " + arrayListPersona.size() + " usuarios de: " + cantPersonas);
        
     }
}
