package main.java.com.mycompany.sigeliapp.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import main.java.com.mycompany.sigeliapp.modelos.Usuario;


public class DaoUsuario extends Conexion implements IDaoUsuario{

    @Override
    public boolean addUsuario(Usuario usuario) {
        String sql = "INSERT INTO "+ Constantes.T_USUARIO+ "(" + Constantes.TU_DOCUMENTO + "," + 
                Constantes.TU_CLAVE + "," + Constantes.TU_CARGO  + ") " + 
                "VALUES (?,?,?)";
        try {            
            PreparedStatement ps=getConexion().prepareStatement(sql);
            ps.setInt(1, usuario.getDocumento());
            ps.setString(2, usuario.getClave());
            ps.setInt(3, usuario.getIdCargo());
            
            ps.executeUpdate();
            
            System.out.println("Usuario creado con éxito");
            
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error al crear el usuario "+e.getMessage());
            return false;
        }finally{
            try {
                getConexion().close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion "+e);
            }
        }
    }

    @Override
    public boolean cambioClave(Usuario usuario) {
        String sql = "UPDATE " + Constantes.T_USUARIO + " SET " + Constantes.TU_CLAVE + "= ?," +
                    " WHERE " + Constantes.T_USUARIO + "." + Constantes.TU_DOCUMENTO + "=" + usuario.getDocumento() ;
        
        try {
            PreparedStatement ps=getConexion().prepareStatement(sql);
            ps.setString(1, usuario.getClave());
            
            ps.executeUpdate();
            
            System.out.println("El pago ha sido subido con éxito...");
            
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error al hacer el pago "+e.getMessage());
            return false;
        }finally{
            try {
                getConexion().close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion "+e);
            }
        }
    }

    
}
