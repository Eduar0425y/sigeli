/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigeliaplicacion.vistas;

import java.util.Scanner;

public class VistaApp {
    
    private Scanner teclado= new Scanner(System.in);
    
    public String getEntrada(String texto){
        
        System.out.println(texto);
        
        return teclado.nextLine();
    }
    
    public void setTexto(String texto){
        
        System.out.println(texto);
        
    }
    public void setTextoError(String texto){
        
        System.err.println(texto);
        
    }
    
    public String menuAdmin(){
        
        System.out.println("Ingrese la opción de lo que desea hacer: "
                + "\n1. administrar libros\n2. administrar usuarios"
                + "\n3. añadir libros\n4. Prestamo de libros\n5. Pago de deudas\n6. Generar reporte\n7. opciones de cuenta\n8. salir");
        
        return teclado.nextLine();
        
    }
    
    public int menuAdminLibros(){
        System.out.println("Ingrese la opción de lo que desea hacer: "
                + "\n1. Ver libros\n2. Modificar libros");
        return teclado.nextInt();
    
    }
    
    
    public int menuEstudiante(){
        System.out.println("Ingrese la opción de lo que desea hacer: "
                + "\n1. Buscar libros\n2. Opciones de cuenta");
        return teclado.nextInt();
    }
    
    public void verLibros(String[] datos){
        System.out.println("Nombre libro: " + datos[0] + "\nEdición libro: " + datos[1]
                         + "\nAutor: " + datos[2] + "\nISBN libro: " + datos[3] + "\nUbicación: Estante: " + datos[4] +
                           " Fila: " + datos[5] + "\nAño del libro: " + datos[6] + "\nEstado del libro: " + datos[7] + "\n");
    }
    
    public int modificacionLibro(){
        System.out.println("Ingrese qué desea modificar: \n"
                         + "1. Nombre del libro\n2. Nombre del autor\n3. Edición del libro\n"
                         + "4. Año del libro\n5. Ubicación\n6. Estado\n7. Eliminar el libro");
        
        return teclado.nextInt();
    }
    
}
