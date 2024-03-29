/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.utils;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author vPalomo
 */
public class Utils {
    public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
    
    
    
    public static String getTipoHora(String texto){
        if ("Complementaria (C)".equalsIgnoreCase(texto)) {
            return "C";
        } else if ("Lectiva (L)".equalsIgnoreCase(texto)) {
            return "L";
        } else if ("No lectiva (NL)".equalsIgnoreCase(texto)) {
            return "NL";
        }else{ 
            return "";
        }

    }

    public static void generarFichero(String nFichero) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(nFichero);
            pw = new PrintWriter(fichero);
            pw.println("");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    /**
     * Convierte los segundos en horas:minutos:segundos
     * @param seg
     * @return un String con el formato hh:mm:ss
     */
    public static String convierteSegundos(int seg) {
        int minutos=seg/60;
        int segundos=seg%60;
        int horas=minutos/60;
        minutos=minutos%60;
        return ""+horas+":"+(minutos<10?"0"+minutos:minutos)+":"+(segundos<10?"0"+segundos:segundos);
        
    }
    
    
}
