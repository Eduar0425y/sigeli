package main.java.com.mycompany.sigeliapp.dao;


public class Constantes {
    public static final String URL="jdbc:mysql://localhost:3306/";
    public static final String DATABASE="sigeli";
    public static final String USER="root";
    public static final String PASSWORD="";
    
    public static final String T_ROL = "";
    public static final String TR_ID = "";
    public static final String TR_ROL = "";
    
    public static final String T_CARRERA = "carrera";
    public static final String TC_ID = "id_carrera";
    public static final String TC_CARREERA = "nombre_carrera";
    
    
    public static final String T_CATEGORIA = "categoria";
    public static final String TCAT_ID = "id_categoria";
    public static final String TCAT_CATEGORIA = "nombre_categoria";
    
    public static final String T_CATLIBRO = "categoria_libro";
    public static final String TCL_ISBN = "isbn_libro";
    public static final String TCL_IDCATEGORIA = "id_categoria";
    
    public static final String T_ESTADO = "estado";
    public static final String TE_ID = "id_estado";
    public static final String TE_ESTADO = "estado";
    
    public static final String T_LIBRO = "libro";
    public static final String TL_ISBN = "isbn_libro";
    public static final String TL_NOMBRE = "nombre_libro";
    public static final String TL_AUTOR = "nombre_autor";
    public static final String TL_EDICION = "edicion_libro";
    public static final String TL_ANO = "ano_Libro";
    public static final String TL_ESTANTE = "estante_libro";
    public static final String TL_FILA = "fila_libro";
    public static final String TL_IDESTADO = "id_estado";
    
    public static final String T_MULTA = "";
    public static final String TM_ID = "";
    public static final String TM_DOCUMENTO = "";
    public static final String TM_IDPRESTAMO = "";
    public static final String TM_VALOR = "";
    public static final String TM_IDESTADO = "";
    
    public static final String T_PERSONA = "";
    public static final String TP_DOCUMENTO = "";
    public static final String TP_NOMBRE = "";
    public static final String TP_IDCARRERA = "";
    public static final String TP_EMAIL = "";
    public static final String TP_TELEFONO = "";
    
    public static final String T_PRESTAMO = "";
    public static final String TPR_ID = "";
    public static final String TPR_DOCUMENTO = "";
    public static final String TPR_ISBN = "";
    public static final String TPR_FECHAPRESTAMO = "";
    public static final String TPR_FECHAENTREGA = "";
    public static final String TPR_IDESTADO = "";
    
    public static final String T_USUARIO = "";
    public static final String TU_DOCUMENTO = "";
    public static final String TU_CLAVE = "";
    public static final String TU_CARGO = "";
    
}
