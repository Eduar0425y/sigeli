package main.java.com.mycompany.sigeliapp.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import main.java.com.mycompany.sigeliapp.vistas.*;
import main.java.com.mycompany.sigeliapp.modelos.*;
import main.java.com.mycompany.sigeliapp.dao.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import main.java.com.mycompany.sigeliapp.modelos.utiles.Portapapeles;


public class ControladorPanelAdmin implements ActionListener, MouseListener{
    
    private panelAdministrador vistaAdministrador;
    private ControladorApp controladorApp;
    
    private ControladorLibro controladorLibro;
    
    private int documentoLogin;
    private String nombre;
    private boolean panelConfig = false;
    
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
        
        this.vistaAdministrador.btnAddLibros.addMouseListener(this);
        this.vistaAdministrador.btnAdminLibros.addMouseListener(this);
        this.vistaAdministrador.btnAddLibros.addMouseListener(this);
        this.vistaAdministrador.btnDeudas.addMouseListener(this);
        this.vistaAdministrador.btnOpcionesPanelAdmin.addMouseListener(this);
        this.vistaAdministrador.btnPrestamos.addMouseListener(this);
        this.vistaAdministrador.btnReportes.addMouseListener(this);
        this.vistaAdministrador.btnSalirPanelAdmin.addMouseListener(this);
        this.vistaAdministrador.opcion1Config.addMouseListener(this);
        this.vistaAdministrador.opcion2Config.addMouseListener(this);
    }
    
     public void actionPerformed(ActionEvent e){

     }

    @Override
    public void mouseClicked(MouseEvent e) {
        
       if(e.getSource() == vistaAdministrador.btnAdminLibros){
            cerrarPanelAdmin();
            controladorLibro.inicio(documentoLogin, nombre);
        }

        else if(e.getSource() == vistaAdministrador.btnAddLibros){
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
            if(panelConfig == true){
                vistaAdministrador.panelConfig.setVisible(false);
                vistaAdministrador.opcion1Config.setVisible(false);
                vistaAdministrador.opcion2Config.setVisible(false);
                vistaAdministrador.separadorCofing.setVisible(false);
                panelConfig = false;
            }
            else if(panelConfig == false){
                vistaAdministrador.panelConfig.setVisible(true);
                vistaAdministrador.opcion1Config.setVisible(true);
                vistaAdministrador.opcion2Config.setVisible(true);
                vistaAdministrador.separadorCofing.setVisible(true);
                panelConfig = true;
            }
        }
        else if(e.getSource() == vistaAdministrador.btnSalirPanelAdmin){
            cerrarPanelAdmin();
            visibleLogin();
        }
        
        else if(e.getSource() == vistaAdministrador.opcion1Config){
            cerrarPanelAdmin();
            controladorApp.opcionesCuenta(iDaoPersona.verPersonas(), iDaoUsuario.verUsuarios(), documentoLogin, iDaoCarrera.verCarreras());
        }
       
        else if(e.getSource() == vistaAdministrador.opcion2Config){
            cerrarPanelAdmin();
            visibleLogin();
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
     
     
    
     
    public void inicio(int documento, ArrayList<Persona> arrayListPersona){
        this.documentoLogin = documento;
        for(Persona persona : arrayListPersona){
            if(persona.getDocumentoPersona() == documento){
                this.nombre = persona.getNombrePersona().toUpperCase();
                vistaAdministrador.txtNombrePersona.setText(nombre);
                vistaAdministrador.txtCargo.setText("Administrador");
            }
        }
        
        vistaAdministrador.panelConfig.setVisible(false);
        vistaAdministrador.opcion1Config.setVisible(false);
        vistaAdministrador.opcion2Config.setVisible(false);
        vistaAdministrador.separadorCofing.setVisible(false);
        
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
