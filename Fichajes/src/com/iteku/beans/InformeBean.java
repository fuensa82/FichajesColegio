/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.beans;

/**
 *
 * @author vPalomo
 */
public class InformeBean {
    private int idInforme, mes, idProfesor, horasL, horasNL, horasC;
    private String fechaGeneracion, observaciones, curso;

    public int getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(int idInforme) {
        this.idInforme = idInforme;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public int getHorasL() {
        return horasL;
    }

    public void setHorasL(int horasL) {
        this.horasL = horasL;
    }

    public int getHorasNL() {
        return horasNL;
    }

    public void setHorasNL(int horasNL) {
        this.horasNL = horasNL;
    }

    public int getHorasC() {
        return horasC;
    }

    public void setHorasC(int horasC) {
        this.horasC = horasC;
    }

    public String getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    
}
