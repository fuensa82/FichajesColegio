/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.beans;

import com.iteku.backofficefichajes.Config;
import com.iteku.utils.FechasUtils;

/**
 * Esta clase se utilizará para hacer los calculos, y contendrá los tramos de horas que está el personal en el centro.
 * @author vPalomo
 */
public class FichajeRecuentoBean {
    private String horaEntrada;
    private String horaSalida;
    private String fecha;
    private int idProfesor;

    public FichajeRecuentoBean(FichajeRecuentoBean fichaje) {
        this.horaEntrada=fichaje.horaEntrada;
        this.horaSalida=fichaje.horaSalida;
        this.fecha=fichaje.fecha;
        this.idProfesor=fichaje.idProfesor;
    }
    public FichajeRecuentoBean() {
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        if(horaSalida==null)return Config.horaMaxima;
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }
    
    public String toString(){
        return "Fecha: "+getFecha()+" Ini: "+getHoraEntrada()+" Fin: "+getHoraSalida();
    }
    public char getDiaSemana(){
        return FechasUtils.dimeDíaSemana(fecha);
    }
}
