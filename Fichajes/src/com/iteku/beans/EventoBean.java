/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.beans;

import com.iteku.basedatos.GestionEventosBD;
import java.util.ArrayList;

/**
 *
 * @author vPalomo
 */
public class EventoBean{
    private int idEvento;
    private String fecha;
    private String horaIni;
    private String horaFin;
    private boolean diaCompleto;
    private String descripcion;
    private ArrayList<ProfesorBean> listaProfesores;
    private String curso;

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public ArrayList<ProfesorBean> getListaProfesores() {
        //if(listaProfesores==null){
            listaProfesores=GestionEventosBD.getListaProfesores(this);
        //}
        return listaProfesores;
    }

    public void setListaProfesores(ArrayList<ProfesorBean> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getFecha() {
        return fecha;
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

    public boolean isDiaCompleto() {
        return diaCompleto;
    }

    public void setDiaCompleto(boolean diaCompleto) {
        this.diaCompleto = diaCompleto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
            
}
