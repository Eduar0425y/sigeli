package main.java.com.mycompany.sigeliapp.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import main.java.com.mycompany.sigeliapp.vistas.*;
import main.java.com.mycompany.sigeliapp.modelos.*;
import main.java.com.mycompany.sigeliapp.dao.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import main.java.com.mycompany.sigeliapp.modelos.utiles.Portapapeles;


public class ControladorPanelAdmin implements ActionListener{
    
    private panelAdministrador vistaAdministrador;
    private ControladorApp controladorApp;
    
    private ControladorLibro controladorLibro;
    
    private int documentoLogin;
    private String nombre;
    
    private IDaoPersona iDaoPersona;
    private IDaoUsuario iDaoUsuario;
    private IDaoLibro iDaoLibro;
    private IDaoEstado iDaoEstado;
    private IDaoCarrera iDaoCarrera;

    public ControladorPanelAdmin() {
        this.vistaAdministrador = new panelAdministrador();
        this.controladorApp = new ControladorApp();
        
        this.controladorLibro = new ControladorLibro();
        
        this.iDaoPersona = new DaoPersona();
        this.iDaoUsuario = new DaoUsuario();
        this.iDaoLibro = new DaoLibro();
        this.iDaoEstado = new DaoEstado();
        this.iDaoCarrera = new DaoCarrera();
        
        this.vistaAdministrador.btnAddLibros.addActionListener(this);
        this.vistaAdministrador.btnAdminLibros.addActionListener(this);
        this.vistaAdministrador.btnAdmnUsuario.addActionListener(this);
        this.vistaAdministrador.btnDeudas.addActionListener(this);
        this.vistaAdministrador.btnOpcionesPanelAdmin.addActionListener(this);
        this.vistaAdministrador.btnPrestamos.addActionListener(this);
        this.vistaAdministrador.btnReportes.addActionListener(this);
        this.vistaAdministrador.btnSalirPanelAdmin.addActionListener(this);
    }
    
     public void actionPerformed(ActionEvent e){
         
         if(e.getSource() == vistaAdministrador.btnAdminLibros){
             cerrarPanelAdmin();
             controladorLibro.inicio(documentoLogin, nombre);
         }
         
         else if(e.getSource() == vistaAdministrador.btnAdmnUsuario){
             cerrarPanelAdmin();
             controladorApp.administrarUsuario(iDaoPersona.verPersonas());
         }
         
         else if(e.getSource() == vistaAdministrador.btnAddLibros){
             cerrarPanelAdmin();
             controladorApp.addLibros(iDaoLibro.verLibros(), iDaoEstado.verEstados());
         }
         
         else if(e.getSource() == vistaAdministrador.btnPrestamos){
             cerrarPanelAdmin();
             controladorApp.prestamoLibros();
         }
         
         else if(e.getSource() == vistaAdministrador.btnDeudas){
             cerrarPanelAdmin();
             controladorApp.adminMultas();
         }
         
         else if(e.getSource() == vistaAdministrador.btnReportes){
             cerrarPanelAdmin();
             controladorApp.reportes();
         }
         
         else if(e.getSource() == vistaAdministrador.btnOpcionesPanelAdmin){
             cerrarPanelAdmin();
             controladorApp.opcionesCuenta(iDaoPersona.verPersonas(), iDaoUsuario.verUsuarios(), documentoLogin, iDaoCarrera.verCarreras());
         }
         else if(e.getSource() == vistaAdministrador.btnSalirPanelAdmin){
             cerrarPanelAdmin();
             visibleLogin();
         }
         
     }
     
    public void inicio(int documento, ArrayList<Persona> arrayListPersona){
        this.documentoLogin = documento;
        for(Persona persona : arrayListPersona){
            if(persona.getDocumentoPersona() == documento){
                this.nombre = persona.getNombrePersona().toUpperCase();
                vistaAdministrador.txtNombrePersona.setText(nombre);
                vistaAdministrador.txtCargo.setText("Administrador");
            }
        }
        visiblePanelAdmin();
    }
    
    
     public void cerrarPanelAdmin(){
        vistaAdministrador.setVisible(false);
    }

    public void visiblePanelAdmin(){
        vistaAdministrador.setTitle("Panel Administrador - Sigeli");
        vistaAdministrador.setLocationRelativeTo(null);
        vistaAdministrador.setVisible(true);
    }
    
    public void visibleLogin(){
        ControladorLogin controladorLogin = new ControladorLogin();
        controladorLogin.visibleLogin();
    }
    
}
