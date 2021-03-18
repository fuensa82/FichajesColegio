/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.informes;

//import com.itextpdf.kernel.pdf.PdfName.Document;
import com.iteku.backofficefichajes.Config;
import com.iteku.basedatos.GestionDetallesInformesBD;
import com.iteku.basedatos.GestionInformesBD;
import com.iteku.beans.DetalleInformeBean;
import com.iteku.beans.InformeBean;
import com.iteku.beans.ProfesorBean;
import com.iteku.utils.FechasUtils;
import com.iteku.utils.Utils;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author vPalomo
 */
public class ImpresionInforme {

    
    private ProfesorBean profesor;
    private int mes;
    private String curso;
    private static Font FuenteCabecera1=FontFactory.getFont("arial", 22, Font.UNDERLINE, BaseColor.BLACK);
    private static Font FuenteCabecera2=FontFactory.getFont("arial", 18, Font.ITALIC, BaseColor.BLACK);
    private static Font FuenteTextoNormal=FontFactory.getFont("arial", 12, Font.NORMAL, BaseColor.BLACK);
    private static Font FuenteTextoNegrita=FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK);
    private static Font FuenteTextoNegritaPe=FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK);
    private static Font FuenteTextoNormalPe=FontFactory.getFont("arial", 8, Font.NORMAL, BaseColor.BLACK);
    private ArrayList<DetalleInformeBean> listaDetalles;
    
    
    public ImpresionInforme(ProfesorBean profesor, int mes) {
        this.profesor=profesor;
        this.mes=mes;
    }

    public ImpresionInforme(ProfesorBean profesor) {
        this.profesor=profesor;
        this.curso=FechasUtils.getCursoActual();
    }
    
    private void cabecera(Document documento) throws DocumentException{
        Paragraph p=new Paragraph(Config.getNombreCol(),FuenteCabecera1);
        p.setAlignment(Chunk.ALIGN_CENTER);
        documento.add(p);
        if(mes==0){
            documento.add(new Paragraph("Informe de horas del año "+curso,FuenteCabecera2));
        }else{
            documento.add(new Paragraph("Informe de horas del mes de "+FechasUtils.getMesNum(mes),FuenteCabecera2)); 
        }
        Chunk c1 = new Chunk("Nombre y apellidos:  ", FuenteTextoNormal);
        Chunk c2 = new Chunk(profesor.getNombre()+" "+profesor.getApellidos(),FuenteTextoNegrita);
        Paragraph p2=new Paragraph();
        p2.add(c1);
        p2.add(c2);
        documento.add(p2);
        //documento.add(new Paragraph("Fecha de generación de los datos: "+listaDetalles.get(0).getFechaCalculo()));
        documento.add(new Paragraph("Fecha de generación del informe: "+FechasUtils.dameFechaNTP()));
    }
    
    private void cuerpo(Document documento) throws DocumentException{
        documento.add(new Paragraph("Informe de horas:",FuenteTextoNormal));
        documento.add(new Phrase("", FuenteTextoNormalPe));
        PdfPTable tabla = new PdfPTable(5);
        tabla.addCell(new Phrase("Fecha", FuenteTextoNegritaPe));
        tabla.addCell(new Phrase("Inicio", FuenteTextoNegritaPe));
        tabla.addCell(new Phrase("Fin", FuenteTextoNegritaPe));
        tabla.addCell(new Phrase("Tipo", FuenteTextoNegritaPe));
        tabla.addCell(new Phrase("Total", FuenteTextoNegritaPe));
        for (DetalleInformeBean listaDetalle : listaDetalles) {
            tabla.addCell(new Phrase(listaDetalle.getFecha(), FuenteTextoNormalPe));
            tabla.addCell(new Phrase(listaDetalle.getHoraIni(), FuenteTextoNormalPe));
            tabla.addCell(new Phrase(listaDetalle.getHoraFin(), FuenteTextoNormalPe));
            tabla.addCell(new Phrase(listaDetalle.getTipoHora(), FuenteTextoNormalPe));
            tabla.addCell(new Phrase(Utils.convierteSegundos(listaDetalle.getTotalHoras()), FuenteTextoNormalPe));
        }
        documento.add(tabla);
    }
    
    public void generarDocuementoMes()throws FileNotFoundException, DocumentException{
        
        cargaDatosIniciales();
        // Se crea el documento
        Document documento = new Document();

        // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
        FileOutputStream ficheroPdf = new FileOutputStream(Config.getRutaPrograma()+"\\informes\\informe"+profesor.getNombreCorto()+"_"+mes+".pdf");

        // Se asocia el documento al OutputStream y se indica que el espaciado entre
        // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
        PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
        documento.open();
        cabecera(documento);
        cuerpo(documento);
        totales(documento);
        documento.close();

    }

    private void cargaDatosIniciales() {
        listaDetalles=GestionDetallesInformesBD.getListaDetallesInformes(profesor, mes);
    }

    private void totales(Document documento) throws DocumentException {
        InformeBean informe=GestionInformesBD.getTotalInformes(profesor, mes);
        
        documento.add(new Paragraph("Resumen total horas:",FuenteTextoNormal));
        Chunk c1 = new Chunk("Horas terapia(T):  ", FuenteTextoNormal);
        Chunk c2 = new Chunk(Utils.convierteSegundos(informe.getHorasL()),FuenteTextoNegrita);
        Paragraph p2=new Paragraph();
        p2.add(c1);
        p2.add(c2);
        documento.add(p2);

        c1 = new Chunk("Horas despacho(D):  ", FuenteTextoNormal);
        c2 = new Chunk(Utils.convierteSegundos(informe.getHorasC()),FuenteTextoNegrita);
        p2=new Paragraph();
        p2.add(c1);
        p2.add(c2);
        documento.add(p2);
        
        c1 = new Chunk("Horas no computables(NC):  ", FuenteTextoNormal);
        c2 = new Chunk(Utils.convierteSegundos(informe.getHorasNL()),FuenteTextoNegrita);
        p2=new Paragraph();
        p2.add(c1);
        p2.add(c2);
        documento.add(p2);
        
    }

    void generarDocuementoCurso() throws FileNotFoundException, DocumentException{
        Document documento = new Document();

        // Se crea el OutputStream para el fichero donde queremos dejar el pdf .
        FileOutputStream ficheroPdf = new FileOutputStream(Config.getRutaPrograma()+"\\informes\\recuento"+FechasUtils.getCursoActual()+"_"+profesor.getNombreCorto()+".pdf");

        // Se asocia el documento al OutputStream y se indica que el espaciado entre
        // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
        PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
        documento.open();
        cabecera(documento);
        resumenTotalCurso(documento);
        documento.close();
        
    }

    private void resumenTotalCurso(Document documento) throws DocumentException {
        ArrayList<InformeBean> lista=GestionInformesBD.getTotalInformesCurso(profesor);
        int segL=0;
        int segNL=0;
        int segC=0;
        lista.sort(new Comparator<InformeBean>(){
            @Override
            public int compare(InformeBean info1, InformeBean info2) {
                int orden1=Config.getArrayMesOrdenCurso()[info1.getMes()-1];
                int orden2=Config.getArrayMesOrdenCurso()[info2.getMes()-1];
                if(orden1<orden2)return -1;
                else if (orden1==orden2) return 0;
                else return 1;
            }
            
        });
        for (InformeBean informe: lista){
            documento.add(new Paragraph(FechasUtils.getMesNum(informe.getMes()),FuenteTextoNegritaPe));
            PdfPTable tabla = new PdfPTable(2);
            tabla.addCell(new Phrase("Horas terapia(T): ", FuenteTextoNegritaPe));
            tabla.addCell(new Phrase(""+Utils.convierteSegundos(informe.getHorasL()), FuenteTextoNormalPe));
            tabla.addCell(new Phrase("Horas despacho (D): ", FuenteTextoNegritaPe));
            tabla.addCell(new Phrase(""+Utils.convierteSegundos(informe.getHorasC()), FuenteTextoNormalPe));
            tabla.addCell(new Phrase("Horas no computables: ", FuenteTextoNegritaPe));
            tabla.addCell(new Phrase(""+Utils.convierteSegundos(informe.getHorasNL()), FuenteTextoNormalPe));
            documento.add(tabla);
            segL+=informe.getHorasL();
            segNL+=informe.getHorasNL();
            segC+=informe.getHorasC();
        }
        documento.add(new Paragraph("Total año "+FechasUtils.getCursoActual(),FuenteTextoNegritaPe));
        PdfPTable tabla = new PdfPTable(2);
        tabla.addCell(new Phrase("Horas terapia(T): ", FuenteTextoNegritaPe));
        tabla.addCell(new Phrase(""+Utils.convierteSegundos(segL), FuenteTextoNormalPe));
        tabla.addCell(new Phrase("Horas despacho (D): ", FuenteTextoNegritaPe));
        tabla.addCell(new Phrase(""+Utils.convierteSegundos(segC), FuenteTextoNormalPe));
        tabla.addCell(new Phrase("Horas no computables (NC): ", FuenteTextoNegritaPe));
        tabla.addCell(new Phrase(""+Utils.convierteSegundos(segNL), FuenteTextoNormalPe));
        
        documento.add(tabla);
    }
}
