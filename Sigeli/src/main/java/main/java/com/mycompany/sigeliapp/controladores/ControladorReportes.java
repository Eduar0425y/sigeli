
package main.java.com.mycompany.sigeliapp.controladores;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import main.java.com.mycompany.sigeliapp.dao.*;
import main.java.com.mycompany.sigeliapp.vistas.*;
import main.java.com.mycompany.sigeliapp.modelos.*;


public class ControladorReportes implements ActionListener, MouseListener{
    
    //DAO
    private IDaoMulta iDaoMulta;
    private IDaoPrestamos iDaoPrestamos;
    private IDaoPersona iDaoPersona;
    
    //Vistas
    
    private PanelReportes panelReportes;
    //Variables
    
    private int documentoLogin;
    private int opcionReporte;
    private String nombre, nombreCorto;

    public ControladorReportes() {
        //DAO
        this.iDaoMulta = new DaoMulta();
        this.iDaoPrestamos = new DaoPrestamos();
        this.iDaoPersona = new DaoPersona();
        
        //VISTAS
        
        this.panelReportes = new PanelReportes();
        
        //Opciones panel
        
        this.panelReportes.btnPrestamos.addActionListener(this);
        this.panelReportes.btnMultas.addActionListener(this);
        this.panelReportes.btnPagos.addActionListener(this);
        this.panelReportes.btnIncluirTodo.addActionListener(this);
        this.panelReportes.btnCrearReporte.addMouseListener(this);
        this.panelReportes.btnVolverMenu.addMouseListener(this);
        this.panelReportes.btnExtenPanel.addMouseListener(this);
        this.panelReportes.btnExtenPanelOff.addMouseListener(this);
        
        this.panelReportes.btnCerrarSesion.addMouseListener(this);
        this.panelReportes.adminLibros.addMouseListener(this);
        this.panelReportes.adminUsuarios.addMouseListener(this);
        this.panelReportes.addLibros.addMouseListener(this);
        this.panelReportes.prestamos.addMouseListener(this);
        this.panelReportes.multas.addMouseListener(this);
        this.panelReportes.reportes.addMouseListener(this);
         
    }
    
    //Acciones

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.panelReportes.btnPrestamos){
            this.opcionReporte = 1;
            
            panelReportes.btnPrestamos.setSelected(true);
            panelReportes.btnMultas.setSelected(false);
            panelReportes.btnPagos.setSelected(false);
            panelReportes.btnIncluirTodo.setSelected(false);
        }
        else if(e.getSource() == this.panelReportes.btnMultas){
            this.opcionReporte = 2;
            panelReportes.btnPrestamos.setSelected(false);
            panelReportes.btnMultas.setSelected(true);
            panelReportes.btnPagos.setSelected(false);
            panelReportes.btnIncluirTodo.setSelected(false);
        }
        else if(e.getSource() == this.panelReportes.btnPagos){
            this.opcionReporte = 3;
            panelReportes.btnPrestamos.setSelected(false);
            panelReportes.btnMultas.setSelected(false);
            panelReportes.btnPagos.setSelected(true);
            panelReportes.btnIncluirTodo.setSelected(false);
        }
        else if(e.getSource() == this.panelReportes.btnIncluirTodo){
            this.opcionReporte = 4;
            panelReportes.btnPrestamos.setSelected(false);
            panelReportes.btnMultas.setSelected(false);
            panelReportes.btnPagos.setSelected(false);
            panelReportes.btnIncluirTodo.setSelected(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == panelReportes.btnCrearReporte){
            try {
                generarReporte(opcionReporte, new File("C:\\Users\\Eduar Xavier\\Documents\\reportes\\" + Date.valueOf(LocalDate.now() )));
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Se ha producido un error " +ex.getMessage());
            } catch (DocumentException ex) {
                JOptionPane.showMessageDialog(null, "Se ha producido un error " +ex.getMessage());
            }
            
            cerrarPanelReportes();
            limpiarDatos();
            visiblePanelAdmin();
        }
        
        else if(e.getSource() == panelReportes.btnVolverMenu){
            cerrarPanelReportes();
            visiblePanelAdmin();
        }
        
        else if(e.getSource() == panelReportes.btnCerrarSesion){
            ControladorLogin controladorLogin = new ControladorLogin();
            cerrarPanelReportes();
            controladorLogin.visibleLogin();
        }
        
        else if(e.getSource() == panelReportes.btnExtenPanel){
            panelReportes.panelExten.setVisible(true);
            panelReportes.btnVolverMenu.setVisible(false);
        }
        
        else if(e.getSource() == panelReportes.btnExtenPanelOff){
            panelReportes.btnVolverMenu.setVisible(true);
            panelReportes.panelExten.setVisible(false);
        }
        
        
        //Opciones panel extendido
        else if(e.getSource() == panelReportes.adminLibros){
            cerrarPanelReportes();
            ControladorLibro controladorLibro = new ControladorLibro();
            controladorLibro.inicio(documentoLogin, nombre);
        }
        
        else if(e.getSource() == panelReportes.adminUsuarios){
            cerrarPanelReportes();
            
            ControladorPersona controladorPersona = new ControladorPersona();
            controladorPersona.inicio(documentoLogin, nombre);
            
        }
        
        else if(e.getSource() == panelReportes.addLibros){
            cerrarPanelReportes();

            
            ControladorLibro controladorLibro = new ControladorLibro();
            
            controladorLibro.inicioAddLibros(documentoLogin, nombre);
        }
        
        //Sin controladores
        
        else if(e.getSource() == panelReportes.prestamos){
            cerrarPanelReportes();
            
            ControladorPrestamos controladorPrestamos = new ControladorPrestamos();
            controladorPrestamos.inicio(documentoLogin, nombre);

        }
        
        else if(e.getSource() == panelReportes.multas){
            cerrarPanelReportes();
            
            ControladorMulta controladorMulta = new ControladorMulta();
            
            controladorMulta.inicio(documentoLogin, nombre);

        }
        
        else if(e.getSource() == panelReportes.reportes){
            
            cerrarPanelReportes();
            visiblePanelReportes();

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
    
    
    
    //metodos
    
    public void generarReporte(int opcion, File pdfFile) throws FileNotFoundException, DocumentException{
        
        Font fuenteTitulo = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
        Font fuenteSubTitulo = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
        
        Date fechaInicio = Date.valueOf(panelReportes.txtAnoInicio.getText() + "-" + panelReportes.txtMesInicio.getSelectedIndex() + "-" + panelReportes.txtDiaInicio.getText());
        Date fechaFin = Date.valueOf(panelReportes.txtAnoFin.getText() + "-" + panelReportes.txtMesFin.getSelectedIndex() + "-" + panelReportes.txtDiaFin.getText());
        
        
        if(opcion == 1){
            try {
                Document documentoPdf = new Document();
                FileOutputStream ficheroDocumento = new FileOutputStream(pdfFile + " Reporte de prestamos " + " inicio " + String.valueOf(fechaInicio) + " fin " + String.valueOf(fechaFin) + ".pdf");
                PdfWriter.getInstance(documentoPdf, ficheroDocumento).setInitialLeading(10);
                
                
                documentoPdf.open();
                
                
                
                documentoPdf.add(new Paragraph("REPORTE DE PRESTAMOS  desde: " + fechaInicio + " hasta: "+ fechaFin , fuenteTitulo));
                documentoPdf.add(new Paragraph("\n"));
                documentoPdf.add(new Paragraph("Observaciones: ", fuenteSubTitulo));
                documentoPdf.add(new Paragraph(panelReportes.txtObservaciones.getText()));
                documentoPdf.add(new Paragraph("\n"));
                documentoPdf.add(new Paragraph("\n"));
                documentoPdf.add(new Paragraph("Prestamos" , fuenteSubTitulo));
                documentoPdf.add(new Paragraph("\n"));
                
                for(Prestamos prestamo : iDaoPrestamos.verPrestamos()){
                    if(prestamo.getFechaPrestamo().after(fechaInicio)  && prestamo.getFechaPrestamo().before(fechaFin)){
                        for(Persona persona : iDaoPersona.verPersonas()){
                            if(prestamo.getIdPersona() == persona.getDocumentoPersona()){
                                documentoPdf.add(new Paragraph("Prestamo número: " + prestamo.getIdPrestamo()));
                                documentoPdf.add(new Paragraph("Persona que pidió el prestamo: " + persona.getNombrePersona() + "\nDocumento: " + prestamo.getIdPersona()));
                                documentoPdf.add(new Paragraph("ISBN del libro prestado: " + prestamo.getIsbnLibro()));
                                documentoPdf.add(new Paragraph("Fecha del prestamo: " + prestamo.getFechaPrestamo() + "Fecha de entrega: " + prestamo.getFechaEntrega()));
                                documentoPdf.add(new Paragraph("Estado del prestamo: " + ((prestamo.getIdEstado() == 3)? "En deuda":"Entregado")));
                                documentoPdf.add(new Paragraph("\n"));
                                documentoPdf.add(new Paragraph("\n"));
                            }
                        }
                    }
                }
                
                documentoPdf.close();
                
                JOptionPane.showMessageDialog(null, "Documento Creado con éxito");
                
                try {
                    File path = new File (pdfFile + " Reporte de prestamos " + " inicio " + String.valueOf(fechaInicio) + " fin " + String.valueOf(fechaFin) + ".pdf");
                    Desktop.getDesktop().open(path);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
                
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, "No se ha podido crear el documento " + e.getMessage());
            }
        }
        else if(opcion == 2){
            Document documentoPdf = new Document();
            try {
                FileOutputStream ficheroDocumento = new FileOutputStream( pdfFile + " Reporte de Multas " + " inicio " + String.valueOf(fechaInicio) + " fin " + String.valueOf(fechaFin) + ".pdf");
                PdfWriter.getInstance(documentoPdf, ficheroDocumento).setInitialLeading(10);
                
                
                documentoPdf.open();
                
                
                
                documentoPdf.add(new Paragraph("REPORTE DE MULTAS  desde: " + fechaInicio + " hasta: "+ fechaFin , fuenteTitulo));
                documentoPdf.add(new Paragraph("\n"));
                documentoPdf.add(new Paragraph("Observaciones: ", fuenteSubTitulo));
                documentoPdf.add(new Paragraph(panelReportes.txtObservaciones.getText()));
                documentoPdf.add(new Paragraph("\n"));
                documentoPdf.add(new Paragraph("\n"));
                documentoPdf.add(new Paragraph("Multas" , fuenteSubTitulo));
                documentoPdf.add(new Paragraph("\n"));
                
                
                for(Multa multa : iDaoMulta.verMultas()){
                    for(Prestamos prestamo : iDaoPrestamos.verPrestamos()){
                        if(multa.getEstadoMulta() == 6 && prestamo.getFechaEntrega().after(fechaInicio) && prestamo.getFechaEntrega().before(fechaFin)){
                            if(prestamo.getIdPrestamo() == multa.getIdPrestamo()){
                                for(Persona persona : iDaoPersona.verPersonas()){
                                    if(prestamo.getIdPersona() == persona.getDocumentoPersona()){
                                        documentoPdf.add(new Paragraph("Multa numero: " + multa.getIdMulta() + "\nID del prestamo multado : " + multa.getIdPrestamo()));
                                        documentoPdf.add(new Paragraph("Persona que pidió el prestamo: " + persona.getNombrePersona() + "\nDocumento: " + persona.getDocumentoPersona()));
                                        documentoPdf.add(new Paragraph("Fecha del prestamo: " + prestamo.getFechaPrestamo() + "\nFecha de entrega: " + prestamo.getFechaEntrega()));
                                        documentoPdf.add(new Paragraph("Fecha en la que se creó la multa: " + multa.getFecha()));
                                        documentoPdf.add(new Paragraph("ISBN del libro multado: " + prestamo.getIsbnLibro()));
                                        documentoPdf.add(new Paragraph("Valor de la multa: " + multa.getValorMulta()));
                                        documentoPdf.add(new Paragraph("\n"));
                                        documentoPdf.add(new Paragraph("\n"));
                                    }
                                }
                            }
                        }
                    }
                }
                
                documentoPdf.close();
                
                JOptionPane.showMessageDialog(null, "Documento Creado con éxito");
                
                try {
                    File path = new File (pdfFile + " Reporte de Multas " + " inicio " + String.valueOf(fechaInicio) + " fin " + String.valueOf(fechaFin) + ".pdf");
                    Desktop.getDesktop().open(path);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
                
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, "No se ha podido crear el documento " + e.getMessage());
            }
                
            
        }
        else if(opcion == 3){
            Document documentoPdf = new Document();
            try {
                FileOutputStream ficheroDocumento = new FileOutputStream(pdfFile + " Reporte de Pagos " + " inicio " + String.valueOf(fechaInicio) + " fin " + String.valueOf(fechaFin) + ".pdf");
                PdfWriter.getInstance(documentoPdf, ficheroDocumento).setInitialLeading(10);
                
                
                documentoPdf.open();
                
                
                
                documentoPdf.add(new Paragraph("REPORTE DE PAGOS  desde: " + fechaInicio + " hasta: "+ fechaFin , fuenteTitulo));
                documentoPdf.add(new Paragraph("\n"));
                documentoPdf.add(new Paragraph("Observaciones: ", fuenteSubTitulo));
                documentoPdf.add(new Paragraph(panelReportes.txtObservaciones.getText()));
                documentoPdf.add(new Paragraph("\n"));
                documentoPdf.add(new Paragraph("\n"));
                documentoPdf.add(new Paragraph("Pagos" , fuenteSubTitulo));
                documentoPdf.add(new Paragraph("\n"));
                
                for(Multa multa : iDaoMulta.verMultas()){
                    for(Prestamos prestamo : iDaoPrestamos.verPrestamos()){
                        if(multa.getEstadoMulta() == 5 && prestamo.getFechaEntrega().after(fechaInicio) && prestamo.getFechaEntrega().before(fechaFin)){
                            if(prestamo.getIdPrestamo() == multa.getIdPrestamo()){
                                for(Persona persona : iDaoPersona.verPersonas()){
                                    if(prestamo.getIdPersona() == persona.getDocumentoPersona()){
                                        documentoPdf.add(new Paragraph("Pago de la multa: " + multa.getIdMulta() + "\nID del prestamo multado : " + multa.getIdPrestamo()));
                                        documentoPdf.add(new Paragraph("Persona que pagó la multa: " + persona.getNombrePersona() + "\nDocumento: " + persona.getDocumentoPersona()));
                                        documentoPdf.add(new Paragraph("Fecha del prestamo: " + prestamo.getFechaPrestamo() + "\nFecha de entrega: " + prestamo.getFechaEntrega()));
                                        documentoPdf.add(new Paragraph("Fecha en la que se Pagó la multa: " + multa.getFecha()));
                                        documentoPdf.add(new Paragraph("ISBN del libro multado: " + prestamo.getIsbnLibro()));
                                        documentoPdf.add(new Paragraph("Valor pagado: " + multa.getValorMulta()));
                                        documentoPdf.add(new Paragraph("\n"));
                                        documentoPdf.add(new Paragraph("\n"));
                                    }
                                }
                            }
                        }
                    }
                }
                
                documentoPdf.close();
                JOptionPane.showMessageDialog(null, "Documento Creado con éxito");
                
                try {
                    File path = new File (pdfFile + " Reporte de Pagos " + " inicio " + String.valueOf(fechaInicio) + " fin " + String.valueOf(fechaFin) + ".pdf");
                    Desktop.getDesktop().open(path);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, "No se ha podido crear el documento " + e.getMessage());
            }
        }
        else if(opcion == 4){
            Document documentoPdf = new Document();
            try {
                FileOutputStream ficheroDocumento = new FileOutputStream(pdfFile + " Reporte total " + " inicio " + fechaInicio + " fin " + fechaFin + ".pdf");
                PdfWriter.getInstance(documentoPdf, ficheroDocumento).setInitialLeading(10);
                
                
                documentoPdf.open();
                
                
                
                documentoPdf.add(new Paragraph("REPORTE TOTAL  desde: " + fechaInicio + " hasta: "+ fechaFin , fuenteTitulo));
                documentoPdf.add(new Paragraph("\n"));
                documentoPdf.add(new Paragraph("Observaciones: ", fuenteSubTitulo));
                documentoPdf.add(new Paragraph(panelReportes.txtObservaciones.getText()));
                documentoPdf.add(new Paragraph("\n"));
                documentoPdf.add(new Paragraph("\n"));
                
                documentoPdf.add(new Paragraph("Prestamos" , fuenteSubTitulo));
                documentoPdf.add(new Paragraph("\n"));
                
                
                for(Prestamos prestamo : iDaoPrestamos.verPrestamos()){
                    if(prestamo.getFechaPrestamo().after(fechaInicio)  && prestamo.getFechaPrestamo().before(fechaFin)){
                        for(Persona persona : iDaoPersona.verPersonas()){
                            if(prestamo.getIdPersona() == persona.getDocumentoPersona()){
                                documentoPdf.add(new Paragraph("Prestamo número: " + prestamo.getIdPrestamo()));
                                documentoPdf.add(new Paragraph("Persona que pidió el prestamo: " + persona.getNombrePersona() + "\nDocumento: " + prestamo.getIdPersona()));
                                documentoPdf.add(new Paragraph("ISBN del libro prestado: " + prestamo.getIsbnLibro()));
                                documentoPdf.add(new Paragraph("Fecha del prestamo: " + prestamo.getFechaPrestamo() + "Fecha de entrega: " + prestamo.getFechaEntrega()));
                                documentoPdf.add(new Paragraph("Estado del prestamo: " + ((prestamo.getIdEstado() == 3)? "En deuda":"Entregado")));
                                documentoPdf.add(new Paragraph("\n"));
                                documentoPdf.add(new Paragraph("\n"));
                            }
                        }
                    }
                }
                
                
                documentoPdf.add(new Paragraph("Multas" , fuenteSubTitulo));
                documentoPdf.add(new Paragraph("\n"));
                
                
                for(Multa multa : iDaoMulta.verMultas()){
                    for(Prestamos prestamo : iDaoPrestamos.verPrestamos()){
                        if(multa.getEstadoMulta() == 6 && prestamo.getFechaEntrega().after(fechaInicio) && prestamo.getFechaEntrega().before(fechaFin)){
                            if(prestamo.getIdPrestamo() == multa.getIdPrestamo()){
                                for(Persona persona : iDaoPersona.verPersonas()){
                                    if(prestamo.getIdPersona() == persona.getDocumentoPersona()){
                                        documentoPdf.add(new Paragraph("Multa numero: " + multa.getIdMulta() + "\nID del prestamo multado : " + multa.getIdPrestamo()));
                                        documentoPdf.add(new Paragraph("Persona que pidió el prestamo: " + persona.getNombrePersona() + "\nDocumento: " + persona.getDocumentoPersona()));
                                        documentoPdf.add(new Paragraph("Fecha del prestamo: " + prestamo.getFechaPrestamo() + "\nFecha de entrega: " + prestamo.getFechaEntrega()));
                                        documentoPdf.add(new Paragraph("Fecha en la que se creó la multa: " + multa.getFecha()));
                                        documentoPdf.add(new Paragraph("ISBN del libro multado: " + prestamo.getIsbnLibro()));
                                        documentoPdf.add(new Paragraph("Valor de la multa: " + multa.getValorMulta()));
                                        documentoPdf.add(new Paragraph("\n"));
                                        documentoPdf.add(new Paragraph("\n"));
                                    }
                                }
                            }
                        }
                    }
                }
                
                
                
                documentoPdf.add(new Paragraph("Pagos" , fuenteSubTitulo));
                documentoPdf.add(new Paragraph("\n"));
                
                for(Multa multa : iDaoMulta.verMultas()){
                    for(Prestamos prestamo : iDaoPrestamos.verPrestamos()){
                        if(multa.getEstadoMulta() == 5 && prestamo.getFechaEntrega().after(fechaInicio) && prestamo.getFechaEntrega().before(fechaFin)){
                            if(prestamo.getIdPrestamo() == multa.getIdPrestamo()){
                                for(Persona persona : iDaoPersona.verPersonas()){
                                    if(prestamo.getIdPersona() == persona.getDocumentoPersona()){
                                        documentoPdf.add(new Paragraph("Pago de la multa: " + multa.getIdMulta() + "\nID del prestamo multado : " + multa.getIdPrestamo()));
                                        documentoPdf.add(new Paragraph("Persona que pagó la multa: " + persona.getNombrePersona() + "\nDocumento: " + persona.getDocumentoPersona()));
                                        documentoPdf.add(new Paragraph("Fecha del prestamo: " + prestamo.getFechaPrestamo() + "\nFecha de entrega: " + prestamo.getFechaEntrega()));
                                        documentoPdf.add(new Paragraph("Fecha en la que se Pagó la multa: " + multa.getFecha()));
                                        documentoPdf.add(new Paragraph("ISBN del libro multado: " + prestamo.getIsbnLibro()));
                                        documentoPdf.add(new Paragraph("Valor pagado: " + multa.getValorMulta()));
                                        documentoPdf.add(new Paragraph("\n"));
                                        documentoPdf.add(new Paragraph("\n"));
                                    }
                                }
                            }
                        }
                    }
                }
                
                documentoPdf.close();
                JOptionPane.showMessageDialog(null, "Documento Creado con éxito");
                
                try {
                    File path = new File (pdfFile + " Reporte total " + " inicio " + fechaInicio + " fin " + fechaFin + ".pdf");
                    Desktop.getDesktop().open(path);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, "No se ha podido crear el documento " + e.getMessage());
            }
        }
        
    }
    
    
    //Metodos de las vistas
    
    public void inicio(int documento, String nombre){
        this.documentoLogin = documento;
        this.nombre = nombre;
        
        String nombreCorto = "";
        
        for(int i=0; i <= nombre.length(); i++){
            if(nombre.charAt(i) == ' '){
                i = nombre.length();
            }
            else{
                nombreCorto += nombre.charAt(i);
            }
        }
        this.nombreCorto = nombreCorto;
        
        panelReportes.txtNombrePersona.setText(this.nombreCorto);
        panelReportes.txtNombrePersona1.setText(this.nombre);
        panelReportes.panelExten.setVisible(false);
        
        inicioDatos();
        visiblePanelReportes();
        
    }
    
    public void visiblePanelReportes(){
        panelReportes.setTitle("Panel generador de reportes - Sigeli");
        panelReportes.setLocationRelativeTo(null);
        panelReportes.setVisible(true);
    }
    
    public void cerrarPanelReportes(){
        panelReportes.setVisible(false);
    }
    
    public void visiblePanelAdmin(){
        ControladorPanelAdmin controladorPanelAdmin = new ControladorPanelAdmin();
        
        controladorPanelAdmin.inicio(documentoLogin, iDaoPersona.verPersonas());
    }
    
    //Seteo de datos
    
    public void limpiarDatos(){
        panelReportes.txtDiaInicio.setText("");
        panelReportes.txtDiaFin.setText("");
        panelReportes.txtAnoFin.setText("");
        panelReportes.txtAnoInicio.setText("");
        panelReportes.txtObservaciones.setText("");
        panelReportes.txtMesInicio.setSelectedIndex(0);
        panelReportes.txtMesFin.setSelectedIndex(0);
        panelReportes.btnPrestamos.setSelected(false);
        panelReportes.btnMultas.setSelected(false);
        panelReportes.btnPagos.setSelected(false);
        panelReportes.btnIncluirTodo.setSelected(false);
    }
    
    public void inicioDatos(){
        
        panelReportes.txtMesInicio.removeAllItems();
        panelReportes.txtMesInicio.addItem("-mes-");
        panelReportes.txtMesInicio.addItem("Enero");
        panelReportes.txtMesInicio.addItem("Febrero");
        panelReportes.txtMesInicio.addItem("Marzo");
        panelReportes.txtMesInicio.addItem("Abril");
        panelReportes.txtMesInicio.addItem("Mayo");
        panelReportes.txtMesInicio.addItem("Junio");
        panelReportes.txtMesInicio.addItem("Julio");
        panelReportes.txtMesInicio.addItem("Agosto");
        panelReportes.txtMesInicio.addItem("Septiembre");
        panelReportes.txtMesInicio.addItem("Octubre");
        panelReportes.txtMesInicio.addItem("Noviembre");
        panelReportes.txtMesInicio.addItem("Diciembre");
        
        panelReportes.txtMesFin.removeAllItems();
        panelReportes.txtMesFin.addItem("-mes-");
        panelReportes.txtMesFin.addItem("Enero");
        panelReportes.txtMesFin.addItem("Febrero");
        panelReportes.txtMesFin.addItem("Marzo");
        panelReportes.txtMesFin.addItem("Abril");
        panelReportes.txtMesFin.addItem("Mayo");
        panelReportes.txtMesFin.addItem("Junio");
        panelReportes.txtMesFin.addItem("Julio");
        panelReportes.txtMesFin.addItem("Agosto");
        panelReportes.txtMesFin.addItem("Septiembre");
        panelReportes.txtMesFin.addItem("Octubre");
        panelReportes.txtMesFin.addItem("Noviembre");
        panelReportes.txtMesFin.addItem("Diciembre");
        
    }
    
}
