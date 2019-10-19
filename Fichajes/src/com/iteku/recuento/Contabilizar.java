/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.recuento;

import com.iteku.basedatos.GestionFichajeBD;
import com.iteku.basedatos.GestionProfesoresBD;
import com.iteku.beans.FichaBean;
import com.iteku.beans.FichajeBean;
import com.iteku.beans.FichajeRecuentoBean;
import com.iteku.beans.ProfesorBean;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author vPalomo
 */
public class Contabilizar {
    /**
     * Prepara todos los datos de un profesor y un mes concreto para empezar el recuento de dicho profesor.
     * @param profesor
     * @param mes 
     */
    public void contabilizarConMesYProfesor(ProfesorBean profesor, int mes){
        ArrayList<FichajeBean> listaFichajes = GestionFichajeBD.getListaFichajesProfesor(profesor,mes);
        ArrayList<FichajeRecuentoBean> listaFichajesRecuento= UtilsContabilizar.convertirFichajes(listaFichajes);
        ArrayList<FichaBean> listaFichas=UtilsContabilizar.getHorarioCompacto(profesor);
        HashMap<String,ArrayList<FichaBean>> horario=UtilsContabilizar.convertirHorario(listaFichas);
        UtilsContabilizar.imprimeArray("Lista antes de contabilizar",listaFichajesRecuento);
        int horasL=contabilizaHorasLectivas(listaFichajesRecuento, listaFichas);
        System.out.println("Segundos de horas lectivas: "+horasL);
        UtilsContabilizar.imprimeArray("Lista despues de recuento",listaFichajesRecuento);
    }
    
    
    
    /**
     * 
     * @param listaFichajesRecuento
     * @param listaFichas
     * @return Los segundos que se han completado de horas lectivas, luego si se quiere se pasaran a horas, minutos
     */
    private int contabilizaHorasLectivas(ArrayList<FichajeRecuentoBean> listaFichajesRecuento, ArrayList<FichaBean> listaFichas) {
        HashMap<String,ArrayList<FichaBean>> horario=UtilsContabilizar.convertirHorario(listaFichas);
        int segundos=0;
        FichajeRecuentoBean fichaje;
        ArrayList<FichaBean> fichasHorario;
        for(int i=0;i<listaFichajesRecuento.size();i++){
            fichaje=listaFichajesRecuento.get(i);
            fichasHorario=horario.get(""+fichaje.getDiaSemana());
            System.out.println("Fichashorarios: "+fichasHorario.size());
            for(int j=0;j<fichasHorario.size();j++){
                System.out.println("Comparando");
                System.out.println(fichaje.getFecha()+": "+fichasHorario.get(j).getHoraIni()+" -> "+fichaje.getHoraEntrada());
                System.out.println("            "+fichasHorario.get(j).getHoraFin()+" -> "+fichaje.getHoraSalida() );
                //Caso. Se empieza la ficha de horario estando en el centro y se termina la ficha estando en el centro
                if(UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraIni(),fichaje.getHoraEntrada())>0 &&
                        UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraFin(),fichaje.getHoraSalida())<0){
                    segundos+=UtilsContabilizar.dimeDuracion(fichasHorario.get(j).getHoraIni(), fichasHorario.get(j).getHoraFin());
                } else {
                    
                }
            }
        }
        
        System.out.println("Hashmap: "+horario.toString());
        return segundos;
    }
}
