/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.informes;

//import com.itextpdf.kernel.pdf.PdfName.Document;
import com.iteku.backofficefichajes.Config;
import com.iteku.beans.ProfesorBean;
import com.iteku.utils.FechasUtils;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
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
    private static Font FuenteCabecera1=FontFactory.getFont("arial", 22, Font.UNDERLINE, BaseColor.BLACK);
    private static Font FuenteCabecera2=FontFactory.getFont("arial", 18, Font.ITALIC, BaseColor.BLACK);
    private static Font FuenteTextoNormal=FontFactory.getFont("arial", 14, Font.NORMAL, BaseColor.BLACK);
    private static Font FuenteTextoNegrita=FontFactory.getFont("arial", 14, Font.BOLD, BaseColor.BLACK);
    
    public ImpresionInforme(ArrayList<ProfesorBean> ListaProfesores, int mes) {
        this.ListaProfesores=ListaProfesores;
        this.mes=mes;
        
    }
    public ImpresionInforme(ProfesorBean profesor, int mes) {
        this.profesor=profesor;
        this.mes=mes;
    }
    
    private void cabecera(Document documento) throws DocumentException{
        Paragraph p=new Paragraph("COLEGIO SAN JOSE",FuenteCabecera1);
        p.setAlignment(Chunk.ALIGN_CENTER);
        documento.add(p);
        documento.add(new Paragraph(" "));
        documento.add(new Paragraph("Informe de horas del mes de "+FechasUtils.getMesNum(mes),FuenteCabecera2));         
        documento.add( Chunk.NEWLINE );
        Chunk c1 = new Chunk("Nombre y apellidos:  ", FuenteTextoNormal);
        Chunk c2 = new Chunk(profesor.getNombre()+" "+profesor.getApellidos(),FuenteTextoNegrita);
        Paragraph p2=new Paragraph();
        p2.add(c1);
        p2.add(c2);
        documento.add(p2);
        documento.add( Chunk.NEWLINE );
        documento.add(new Paragraph("Fecha de generación de los datos: "));
        documento.add( Chunk.NEWLINE );
        documento.add(new Paragraph("Fecha de generación del informe: "+FechasUtils.dameFechaNTP()));
    }
    
    private void cuerpo(Document documento) throws DocumentException{
        
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
    
    public void generarDocuementoMes()throws FileNotFoundException, DocumentException{
        // Se crea el documento
        Document documento = new Document();

        // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
        FileOutputStream ficheroPdf = new FileOutputStream(Config.rutaPrograma+"\\informes\\informe"+profesor.getNombreCorto()+"_"+mes+".pdf");

        // Se asocia el documento al OutputStream y se indica que el espaciado entre
        // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
        PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
        documento.open();
        cabecera(documento);
        cuerpo(documento);
        documento.close();

    }
}
