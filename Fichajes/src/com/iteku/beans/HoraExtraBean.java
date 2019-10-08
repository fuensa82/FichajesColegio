/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.beans;

import com.iteku.utils.FechasUtils;

/**
 *
 * @author Víctor
 */
public class HoraExtraBean {
    private int idHoraExtra;
    private int idProfesor;
    private String fecha;
    private String horaIni;
    private String horaFin;
    private String motivo;

    public int getIdHoraExtra() {
        return idHoraExtra;
    }

    public void setIdHoraExtra(int idHoraExtra) {
        this.idHoraExtra = idHoraExtra;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getFecha() {
        return fecha;
    }
    public String getFechaMysql(){
        return FechasUtils.fechaParaMysql(fecha);
    }
    public String getHoraIniMysql(){
        return horaIni+":00";
    }
    public String getHoraFinMysql(){
        return horaFin+":00";
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    
}
