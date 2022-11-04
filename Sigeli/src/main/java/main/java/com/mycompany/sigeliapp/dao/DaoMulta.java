package main.java.com.mycompany.sigeliapp.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import main.java.com.mycompany.sigeliapp.modelos.Multa;

public class DaoMulta extends Conexion implements IDaoMulta{

    @Override
    public boolean hacerMultas(Multa multa) {
        String sql = "INSERT INTO "+ Constantes.T_MULTA+ "(" + Constantes.TM_ID + "," + 
                Constantes.TM_DOCUMENTO + "," + Constantes.TM_IDPRESTAMO + "," +
                Constantes.TM_VALOR + "," + Constantes.TM_IDESTADO + ") " + 
                "VALUES (?,?,?,?,?)";
        try {            
            PreparedStatement ps=getConexion().prepareStatement(sql);
            ps.setInt(1, multa.getIdMulta());
            ps.setInt(2, multa.getDocumentoPersona());
            ps.setInt(3, multa.getIdPrestamo());
            ps.setInt(4, multa.getValorMulta());
            ps.setInt(5, multa.getEstadoMulta());
            
            ps.executeUpdate();
            
            System.out.println("Multa ingresada con éxito");
            
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error al Añadir la multa "+e.getMessage());
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
    public boolean pagarMulta(int id, Multa multa) {
        String sql = "UPDATE " + Constantes.T_MULTA + " SET " + Constantes.TM_IDESTADO + "= ?," +
                    " WHERE " + Constantes.T_MULTA + "." + Constantes.TM_ID + "=" + id ;
        
        try {
            PreparedStatement ps=getConexion().prepareStatement(sql);
            ps.setInt(1, multa.getEstadoMulta());
            
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

    @Override
    public boolean eliminarMulta(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
