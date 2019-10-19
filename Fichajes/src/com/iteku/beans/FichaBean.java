/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.beans;

/**
 *
 * @author Víctor
 */
public class FichaBean {
    private int idFicha;
    private String horaIni;
    private String horaFin;
    private int idProfesor;
    private char dia;
    private String tipoHora;
    private String curso;

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public String getHoraIni() {
        return horaIni;
    }
    public String toString(){
        return "Dia: "+this.dia+" hora: de "+this.horaIni+" a "+this.horaFin;
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

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public char getDia() {
        return dia;
    }

    public void setDia(char dia) {
        this.dia = dia;
    }

    public String getTipoHora() {
        return tipoHora;
    }

    public void setTipoHora(String tipoHora) {
        this.tipoHora = tipoHora;
    }

    public String getCurso() {
        return curso;
    }
    

    public void setCurso(String curso) {
        this.curso = curso;
    }
    
}
