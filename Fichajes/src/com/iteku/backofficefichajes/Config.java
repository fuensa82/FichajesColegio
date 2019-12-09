/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.backofficefichajes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vPalomo
 */
public class Config {
    

    public static String getHoraMaxima() {
        if(horaMaxima==null)cargarDatos();
        return horaMaxima;
    }
    public static String getHoraMinima() {
        if(horaMinima==null)cargarDatos();
        return horaMinima;
    }
    public static char[] getDias() {
        return dias;
    }
    public static int[] getArrayMes() {
        return arrayMes;
    }
    public static String getRutaScriptNode() {
        if(rutaScriptNode==null)cargarDatos();
        return rutaScriptNode;
    }
    public static String getRutaLog() {
        if(rutaLog==null)cargarDatos();
        return rutaLog;
    }
    public static String getFicheroBarra() {
        if(ficheroBarra==null)cargarDatos();
        return ficheroBarra;
    }
    public static String getRutaPrograma() {
        return rutaPrograma;
    }
    public static int[] getArrayMesOrdenCurso() {
        return arrayMesOrdenCurso;
    }
    
    private static void cargarDatos(){
        System.out.println("Cargando datos del fichero de config");
        FileReader fr = null;
        try {
            String sistema=System.getProperty("os.name");
            System.out.println(sistema);
            String nombreFichero;
            if(sistema.contains("Windows")){
                nombreFichero="config.txt";
            }else{
                nombreFichero="/home/pi/Fichajes/config.txt";
            }
            File archivo = new File (nombreFichero);
            fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            
            while((linea=br.readLine())!=null){
                String[] valores=linea.split("=");
                if(valores[0].equalsIgnoreCase("horaMinima")){
                    horaMinima=valores[1];
                }else if(valores[0].equalsIgnoreCase("horaMaxima")){
                    horaMaxima=valores[1];
                }else if(valores[0].equalsIgnoreCase("rutaScriptNode")){
                    rutaScriptNode=valores[1];
                }else if(valores[0].equalsIgnoreCase("rutaLog")){
                    rutaLog=valores[1];
                }else if(valores[0].equalsIgnoreCase("ficheroBarra")){
                    ficheroBarra=valores[1];
                }else if(valores[0].equalsIgnoreCase("baseDatos")){
                    baseDatos=valores[1];
                }else if(valores[0].equalsIgnoreCase("usuario")){
                    usuario=valores[1];
                }else if(valores[0].equalsIgnoreCase("contrasenia")){
                    contrasenia=valores[1];
                }else if(valores[0].equalsIgnoreCase("host")){
                    hostBaseDatos=valores[1];
                }else if(valores[0].equalsIgnoreCase("nombreCol")){
                    nombreCol=valores[1];
                }else if(valores[0].equalsIgnoreCase("nombreApp")){
                    nombreApp=valores[1];
                }else if(valores[0].equalsIgnoreCase("maquina")){
                    maquina=valores[1];
                }else if(valores[0].equalsIgnoreCase("tiempoCorrecto")){
                    tiempoCorrecto=valores[1];
                }else if(valores[0].equalsIgnoreCase("tiempoError")){
                    tiempoError=valores[1];
                }
            }
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private static String horaMaxima;
    private static String horaMinima;
    private static String rutaScriptNode;
    private static String rutaLog;
    private static String ficheroBarra;
    
    private static String baseDatos;
    private static String usuario;
    private static String contrasenia;
    private static String hostBaseDatos;

    public static String getBaseDatos() {
        if(baseDatos==null)cargarDatos();
        return baseDatos;
    }

    public static String getUsuario() {
        if(usuario==null)cargarDatos();
        return usuario;
    }

    public static String getContrasenia() {
        if(contrasenia==null)cargarDatos();
        return contrasenia;
    }
    
    private static String nombreCol;
    private static String nombreApp;
    private static String maquina;
    private static String tiempoCorrecto;
    private static String tiempoError;
    
    
    private static char dias[]={'D','L','M','X','J','V','S'};
    private static int arrayMes[]={1,2,3,4,5,6,7,8,9,10,11,12};
    private static int arrayMesOrdenCurso[]={5,6,7,8,9,10,11,12,1,2,3,4};//El 5 mes es enero, el primero (1) es septiembre
    //private static int ordenDias[]={7,1,2,3,4,5,6};

    public static HashMap<String,Integer> getOrdenDias() {
        HashMap<String,Integer> ordenDias=new HashMap<String,Integer>();
        ordenDias.put("L", 1);
        ordenDias.put("M", 2);
        ordenDias.put("X", 3);
        ordenDias.put("J", 4);
        ordenDias.put("V", 5);
        ordenDias.put("S", 6);
        ordenDias.put("D", 7);
        return ordenDias;
    }

    public static String getNombreCol() {
        if(nombreCol==null)cargarDatos();
        return nombreCol;
    }

    public static String getNombreApp() {
        if(nombreApp==null)cargarDatos();
        return nombreApp;
    }

    public static String getMaquina() {
        if(maquina==null)cargarDatos();
        return maquina;
    }

    public static String getTiempoCorrecto() {
        if(tiempoCorrecto==null)cargarDatos();
        return tiempoCorrecto;
    }

    public static String getTiempoError() {
        if(tiempoError==null)cargarDatos();
        return tiempoError;
    }
    private static String rutaPrograma=System.getProperty("user.dir");
    

    public static String getHostBaseDatos() {
        if(hostBaseDatos==null)cargarDatos();
        return hostBaseDatos;
    }
}
