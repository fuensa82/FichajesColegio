/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.beans;

import com.iteku.basedatos.GestionProfesoresBD;
import com.iteku.utils.FechasUtils;
import java.util.GregorianCalendar;

/**
 *
 * @author Víctor
 */
public class ProfesorBean {
    private int idProfesor;
    private String nombre;
    private String apellidos;
    private int idTarjeta;
    private long currentTimeMillis;

    public long getCurrentTimeMillis() {
        return currentTimeMillis;
    }

    public void setCurrentTimeMillis(long currentTimeMillis) {
        this.currentTimeMillis = currentTimeMillis;
    }
    public void cargaCurrentTime(){
        currentTimeMillis=GestionProfesoresBD.getUltimaCurrentTime(idProfesor, FechasUtils.fechaHoyParaMysql());
    }
    public String getFechaHoraCurrentTime(){
        if(isDentro()){
            GregorianCalendar c=new GregorianCalendar();
            c.setTimeInMillis(currentTimeMillis);
            return FechasUtils.getFechaString(c);
        }else{
            return "";
        }
    }
    
    public String getHoraCurrentTime(){
        if(isDentro()){
            String fecha=getFechaHoraCurrentTime();
            return fecha.substring(11);
        }else{
            return "";
        }
        
    }
/**
 * Indica si el profesor que marca está saliendo o entrado. True para entrando y false para saliendo.
 * @return 
 */
    public boolean isDentro() {
        return GestionProfesoresBD.isDentro(idProfesor);
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

   
           
}
