/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.utils;

import java.net.InetAddress;
import java.util.Date;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

/**
 *
 * @author vPalomo
 */
public class FechaExternaNTP {
    //Declaramos el servidor de donde obtendremos la fecha
 
    //String servidor = "0.north-america.pool.ntp.org";
    private static long time=System.currentTimeMillis();
    private static Date fecha;
    
    public static Date getNTPDate() {
        long timeNow=System.currentTimeMillis();
        if((timeNow-time)>2000 || fecha==null){
            time=System.currentTimeMillis();
            System.out.println("Buscamos hora ROA");
            fecha=getNTPDateROA();
        }
        return fecha;
    }
    
    public static Date getNTPDateSYS() {
        return new Date();
    }
    
    public static Date getNTPDateROA() {
        String servidor="minuto.roa.es";         
        //Se le da un valor nulo por defecto a la variable
 
        Date fechaRecibida = null;
        //Se crea un objeto de tipo NTPUDPClient Clase de la libreria Commons
 
        NTPUDPClient cliente = new NTPUDPClient();
       //Tiempo de Espera Antes De Mandar Error.
 
        cliente.setDefaultTimeout(5000);
        try {
            //Obtenemos la direccion IP por medio de el host previamente Asignado
 
            InetAddress hostAddr = InetAddress.getByName(servidor);
            //Solicitamos la fecha al servidor
 
            TimeInfo fecha = cliente.getTime(hostAddr);
            //Recibimos y convertimos la fecha a formato DATE
 
            fechaRecibida = new Date(fecha.getMessage().getTransmitTimeStamp().getTime());
        } catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
        }
        //Cerramos la comunicación con el cliente
 
        cliente.close();
        //Retornamos la fecha ya convertida si no es nula , de ser nula regresa la fecha Local
 
        return fechaRecibida == null ? new Date() : fechaRecibida ;
       
    }
 
}