/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.recuento;

import com.iteku.basedatos.GestionProfesoresBD;
import com.iteku.beans.FichaBean;
import com.iteku.beans.ProfesorBean;
import java.util.ArrayList;

/**
 *
 * @author vPalomo
 */
public class Contabilizar {
    public void contabilizarConMesYProfesor(ProfesorBean profesor, int mes){
        getHorarioCompacto(profesor);
        
    }
    
    public ArrayList<FichaBean> getHorarioCompacto(ProfesorBean profesor){
        ArrayList<FichaBean> listaFichas=getHorario(profesor);
        imprimeHorario(listaFichas);
        for(int i=listaFichas.size()-1;i>0;i--){ //Recorremos la lista desde el final al principio
            FichaBean fichaActual=listaFichas.get(i);
            FichaBean fichaAnterior=listaFichas.get(i-1);
            if(fichaActual.getDia()==fichaAnterior.getDia()){
                if(fichaActual.getHoraIni().trim().equals(fichaAnterior.getHoraFin().trim())){
                    listaFichas.remove(i);
                    fichaAnterior.setHoraFin(fichaActual.getHoraFin());
                }else{
                    System.out.println("No se borra por no coincidir la hora");
                }
            }else{
                System.out.println("No se borra por no coincidir el dia");
            }
        }
        imprimeHorario(listaFichas);
        return listaFichas;
    }
    
    public void imprimeHorario(ArrayList<FichaBean> listaFichas){
        System.out.println("*******************Imprimendo***********************");
        for (FichaBean listaFicha : listaFichas) {
            System.out.println(listaFicha);
        }
    }
    /**
     * Consulta el horario de un profesor y devuelve un arrayList con todas sus fichas
     * @param profesor
     * @return 
     */
    public ArrayList<FichaBean> getHorario(ProfesorBean profesor){
        //imprimeHorario(listaFichas);
        //System.out.println("*************************************************************************");
        return GestionProfesoresBD.getListaFichasCurso(""+profesor.getIdProfesor());
        
    }
}
