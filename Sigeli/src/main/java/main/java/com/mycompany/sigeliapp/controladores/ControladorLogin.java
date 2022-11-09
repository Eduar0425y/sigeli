package main.java.com.mycompany.sigeliapp.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import main.java.com.mycompany.sigeliapp.dao.*;
import main.java.com.mycompany.sigeliapp.modelos.Carrera;
import main.java.com.mycompany.sigeliapp.modelos.Persona;
import main.java.com.mycompany.sigeliapp.modelos.Usuario;
import main.java.com.mycompany.sigeliapp.vistas.Login;
import main.java.com.mycompany.sigeliapp.vistas.Registro;

public class ControladorLogin implements ActionListener{
    
    private ControladorApp controlador;
    private ControladorPanelAdmin controladorPanelAdmin;
    private Login vistaLogin;
    private Registro vistaRegistro;
    private int documentoAdmin = 0;
    private String claveAdmin = "";
    boolean registro = false;

    
    private IDaoUsuario iDaoUsuario;
    private IDaoPersona iDaoPersona;
    private IDaoCarrera iDaoCarrera;

    public ControladorLogin() {
        this.controlador = new ControladorApp();
        this.controladorPanelAdmin = new ControladorPanelAdmin();
        this.vistaLogin = new Login();
        this.vistaRegistro= new Registro();
        this.iDaoUsuario = new DaoUsuario();
        this.iDaoPersona = new DaoPersona();
        this.iDaoCarrera = new DaoCarrera();
        
        this.vistaLogin.btnIngresar.addActionListener(this);
        this.vistaLogin.btnRegistrar.addActionListener(this);
        
        this.vistaRegistro.btnRegistro.addActionListener(this);
        this.vistaRegistro.volverLogin.addActionListener(this);
    }
    

    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == vistaLogin.btnIngresar){
            ingresar(iDaoUsuario.verUsuarios());
        }

        else if(e.getSource() == vistaLogin.btnRegistrar){
            this.documentoAdmin = Integer.parseInt(vistaLogin.txtDocumentoLogin.getText());
            this.claveAdmin = vistaLogin.txtClaveLogin.getText();
            boolean datosValidos = false;
            if(documentoAdmin != 0 && !claveAdmin.equals("")){
                for(Usuario usuario : iDaoUsuario.verUsuarios()){
                    if(documentoAdmin == usuario.getDocumento() && claveAdmin.equals(usuario.getClave())){
                        cerrarLogin();
                        verRoles();
                        mostrarCarrera(iDaoCarrera.verCarreras());
                        limpiarTextos();
                        visibleRegistro();
                        datosValidos = true;
                    }
                }
            }
            if(datosValidos == false){
                vistaLogin.txtMensajeLogin.setText("Ingrese como administrador");
            }
        }
        else if(e.getSource() == vistaRegistro.btnRegistro){
                registro = true;
                registro(iDaoPersona.verPersonas(), iDaoUsuario.verUsuarios());
                limpiarTextos();
                cerrarRegistro();
                visibleLogin();
        }
        
        else if(e.getSource() == vistaRegistro.volverLogin){
            limpiarTextos();
            cerrarRegistro();
            visibleLogin();
        }
    }
    
    public void ingresar(ArrayList<Usuario> arrayListUsuario){
        
        int documento = Integer.parseInt(vistaLogin.txtDocumentoLogin.getText());
        String clave = vistaLogin.txtClaveLogin.getText();
        boolean ingresar = false;
        for(Usuario usuario : arrayListUsuario){
            if(documento == usuario.getDocumento() && clave.equals(usuario.getClave())){
                if(usuario.getIdCargo() == 1){
                    cerrarLogin();
                    controladorPanelAdmin.inicio(usuario.getDocumento(), iDaoPersona.verPersonas());
                    //controlador.panelAdmin();
                    ingresar = true;
                }

                else if(usuario.getIdCargo() == 2){
                    cerrarLogin();
                    controlador.panelEstudiante();
                    ingresar = true;
                }
            }
        }
        
        if(ingresar == false){
            vistaLogin.txtMensajeLogin.setText("Los datos ingresados son erróneos");
        }
    }
    
    public void registro(ArrayList<Persona> arrayListPersona, ArrayList<Usuario> arrayListUsuario){
        
        String nombre, email, telefono, clave;
        int documento, idCarrera, idCargo;

        for(Usuario usuario : arrayListUsuario){
            if(usuario.getIdCargo() == 1){
                documento = Integer.parseInt(vistaRegistro.txtDocumentoRegistro.getText());
                nombre = vistaRegistro.txtNombreRegistro.getText();
                email = vistaRegistro.txtEmailRegistro.getText();
                telefono = vistaRegistro.txtTelefonoRegistro.getText();

                idCarrera = vistaRegistro.boxCarreraRegistro.getSelectedIndex();

                idCargo = vistaRegistro.boxTipoRegistro.getSelectedIndex();
                clave = vistaRegistro.txtClaveRegistro.getText();

                Persona persona = new Persona(documento, nombre, idCarrera, email, telefono);
                Usuario usuarioE = new Usuario(documento, clave, idCargo);

                if(registro == true){
                    iDaoPersona.addPersona(persona);
                    iDaoUsuario.addUsuario(usuarioE);
                    JOptionPane.showMessageDialog(null, "El usuario ha sido creado");
                }
            }
        }

    }
    
    public void mostrarCarrera(ArrayList<Carrera> arrayListCarrera){
        vistaRegistro.boxCarreraRegistro.removeAllItems();
        vistaRegistro.boxCarreraRegistro.addItem("");
        for(Carrera carrera : arrayListCarrera){
            vistaRegistro.boxCarreraRegistro.addItem(carrera.getCarrera());
        }
    }
    
    public void verRoles(){
        vistaRegistro.boxTipoRegistro.removeAllItems();
        vistaRegistro.boxTipoRegistro.addItem("");
        vistaRegistro.boxTipoRegistro.addItem("Administrador");
        vistaRegistro.boxTipoRegistro.addItem("Estudiante");
    }
    
    
     public void visibleRegistro(){
        vistaRegistro.setTitle("Registro - Sigeli");
        vistaRegistro.setLocationRelativeTo(null);
        vistaRegistro.setVisible(true);
    }
    
    public void cerrarRegistro(){
        vistaRegistro.setVisible(false);
    }

    public void visibleLogin(){
        vistaLogin.setTitle("Login - Sigeli");
        vistaLogin.txtMensajeLogin.setText("");
        vistaLogin.setLocationRelativeTo(null);
        vistaLogin.setVisible(true);
    }
    
    public void cerrarLogin(){
        vistaLogin.setVisible(false);
    }
    
    public void limpiarTextos(){
        vistaLogin.txtDocumentoLogin.setText("");
        vistaLogin.txtClaveLogin.setText("");
        vistaRegistro.txtDocumentoRegistro.setText("");
        vistaRegistro.txtNombreRegistro.setText("");
        vistaRegistro.txtEmailRegistro.setText("");
        vistaRegistro.txtTelefonoRegistro.setText("");
        vistaRegistro.txtClaveRegistro.setText("");
        vistaLogin.txtMensajeLogin.setText("");
        
        vistaRegistro.boxTipoRegistro.setSelectedIndex(0);
        vistaRegistro.boxCarreraRegistro.setSelectedIndex(0);
    }
   
    
    
    
}