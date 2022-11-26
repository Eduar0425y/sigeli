/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.mycompany.sigeliapp.validaciones;

import javax.swing.JOptionPane;
import main.java.com.mycompany.sigeliapp.dao.DaoPersona;
import main.java.com.mycompany.sigeliapp.dao.IDaoPersona;
import main.java.com.mycompany.sigeliapp.modelos.Persona;
import main.java.com.mycompany.sigeliapp.modelos.Usuario;

/**
 *
 * @author Eduar Xavier
 */
public class ValidacionRegistroLogin {
    
    
    public static boolean registro(Persona persona, Usuario usuario){
        
        IDaoPersona iDaoPersona = new DaoPersona();
        
        for(Persona personaF : iDaoPersona.verPersonas()){
            if(persona.getDocumentoPersona() == personaF.getDocumentoPersona()){
                JOptionPane.showMessageDialog(null, "El documento ingresado ya se encuentra registrado");
                return false;
            }
        }
        
        if(persona.getNombrePersona().equals("")){
            JOptionPane.showMessageDialog(null, "El Nombre no puede estar vacío");
            return false;
        }
        
        else if(persona.getEmailPersona().equals("")){
            JOptionPane.showMessageDialog(null, "El Email no puede estar vacío");
            return false;
        }
        
        else if(persona.getTelefono().equals("")){
            JOptionPane.showMessageDialog(null, "El Telefono no puede estar vacío");
            return false;
        }
        
        else if(usuario.getIdCargo() == 1 && persona.getIdCarrera() !=0){
            JOptionPane.showMessageDialog(null, "No se puede crear un administrador si tiene una carrera");
            return false;
        }
        
        else if(usuario.getIdCargo() == 2 && persona.getIdCarrera() ==0){
            JOptionPane.showMessageDialog(null, "No se puede crear estudiante con perfil de administrador");
            return false;
        }
        
        else if(usuario.getClave().equals("")){
            JOptionPane.showMessageDialog(null, "La clave no puede estar vacía");
            return false;
        }
        
        else if(usuario.getClave().equals(String.valueOf(usuario.getDocumento()))){
            JOptionPane.showMessageDialog(null, "Tener el documento como contraseña no es algo confiable...");
            return false;
        }
        
        else{
            return true;
        }
        
        
    }
    
}
