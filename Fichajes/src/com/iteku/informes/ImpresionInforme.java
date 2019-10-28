/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.informes;

//import com.itextpdf.kernel.pdf.PdfName.Document;
import com.iteku.beans.ProfesorBean;
import com.iteku.utils.FechasUtils;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 *
 * @author vPalomo
 */
public class ImpresionInforme {

    
    private ArrayList<ProfesorBean> ListaProfesores;
    private ProfesorBean profesor;
    private int mes;
    
    public ImpresionInforme(ArrayList<ProfesorBean> ListaProfesores, int mes) {
        this.ListaProfesores=ListaProfesores;
        this.mes=mes;
        
    }
    public ImpresionInforme(ProfesorBean profesor, int mes) {
        this.profesor=profesor;
        this.mes=mes;
    }
    
    public void cabecera(Document documento) throws DocumentException{
        documento.add(new Paragraph("Informe de horas del mes de "+FechasUtils.getMesNum(mes),
                                        FontFactory.getFont("arial",   // fuente
                                        22,                            // tamaño
                                        Font.ITALIC,                   // estilo
                                        BaseColor.BLACK)));             // color
        documento.add(new Paragraph("Fecha de generación de los datos: "));
        documento.add(new Paragraph("Fecha de generación del informe: "));
    }
    
    public void cuerpo(Document documento) throws DocumentException{
        
        documento.add(new Paragraph("Este es el segundo y tiene una fuente rara",
                                        FontFactory.getFont("arial",   // fuente
                                        22,                            // tamaño
                                        Font.ITALIC,                   // estilo
                                        BaseColor.CYAN)));             // color

        PdfPTable tabla = new PdfPTable(3);
        for (int i = 0; i < 15; i++)
        {
                tabla.addCell("celda " + i);
        }
        documento.add(tabla);
    }
    
    public void generarDocuemnto()throws FileNotFoundException, DocumentException{
        // Se crea el documento
        Document documento = new Document();

        // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
        FileOutputStream ficheroPdf = new FileOutputStream("fichero.pdf");

        // Se asocia el documento al OutputStream y se indica que el espaciado entre
        // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
        PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
        documento.open();
        cabecera(documento);
        cuerpo(documento);
        documento.close();

    }
}
