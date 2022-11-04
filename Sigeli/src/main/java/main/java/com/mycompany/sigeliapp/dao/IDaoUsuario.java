package main.java.com.mycompany.sigeliapp.dao;

import main.java.com.mycompany.sigeliapp.modelos.Usuario;


public interface IDaoUsuario {
    
    public boolean addUsuario(Usuario usuario);
    
    public boolean cambioClave(Usuario usuario);
    
}
