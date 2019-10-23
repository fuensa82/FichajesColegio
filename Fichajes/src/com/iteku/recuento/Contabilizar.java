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
import com.iteku.utils.Utils;
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
        //Preparamos todos los datos. Lista de fichas de horario, lista de fichajes
        ArrayList<FichajeBean> listaFichajes = GestionFichajeBD.getListaFichajesProfesor(profesor,mes);
        ArrayList<FichajeRecuentoBean> listaFichajesRecuento= UtilsContabilizar.convertirFichajes(listaFichajes);
        ArrayList<FichaBean> listaFichas=UtilsContabilizar.getHorarioCompacto(profesor);
        HashMap<String,ArrayList<FichaBean>> horario=UtilsContabilizar.convertirHorario(listaFichas);
        
        
        
        /**
         * Contabilizamos las horas lectivas
         */
        UtilsContabilizar.imprimeArray("Lista antes de contabilizar",listaFichajesRecuento);
        int segundosLectivos=contabilizaHorasLectivas(listaFichajesRecuento, listaFichas);
        System.out.println("Segundos de horas lectivas: "+Utils.convierteSegundos(segundosLectivos));
        
        /**
         * Contabilizamos la horas no lectivas
         */
        UtilsContabilizar.imprimeArray("Lista despues de recuento",listaFichajesRecuento);
        int segundosNLectivos=contabilizaHorasNoLectivas(listaFichajesRecuento);
        System.out.println("Segundos de horas no lectivas: "+Utils.convierteSegundos(segundosNLectivos));
    }
    
    
    
    /**
     * Se le pasa la lista de fichajes que está aun por tratar y la lista de fichas de horario que le corresponden al profesor.
     * La listaFichajesRecuento se verá modificada, quitando el tiempo que coincida con el horario.
     * @param listaFichajesRecuento
     * @param listaFichas
     * @return Los segundos que se han completado de horas lectivas, luego si se quiere se pasaran a horas, minutos
     */
    private int contabilizaHorasLectivas(ArrayList<FichajeRecuentoBean> listaFichajesRecuento, ArrayList<FichaBean> listaFichas) {
        HashMap<String,ArrayList<FichaBean>> horario=UtilsContabilizar.convertirHorario(listaFichas);
        int segundos=0;
        FichajeRecuentoBean fichaje;
        ArrayList<FichaBean> fichasHorario;
        for(int i=listaFichajesRecuento.size()-1;i>=0;i--){
            fichaje=listaFichajesRecuento.get(i);
            fichasHorario=horario.get(""+fichaje.getDiaSemana());
            System.out.println("Fichashorarios: "+fichasHorario.size());
            boolean seguir=true;
            for(int j=0;j<fichasHorario.size() && seguir;j++){
                System.out.println("Comparando i="+i+" j="+j);
                System.out.println("            Fichas      Fichajes");
                System.out.println(fichaje.getFecha()+": "+fichasHorario.get(j).getHoraIni()+" -> "+fichaje.getHoraEntrada());
                System.out.println("            "+fichasHorario.get(j).getHoraFin()+" -> "+fichaje.getHoraSalida() );
                //Caso. Se empieza la ficha de horario estando en el centro y se termina la ficha estando en el centro
                if(UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraIni(),fichaje.getHoraSalida())>0){
                    System.out.println("Caso todo antes");
                    System.out.println("Fichaje:============");
                    System.out.println("Ficha:                ======");
                }else if(UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraFin(),fichaje.getHoraEntrada())<0) {
                    System.out.println("Caso todo despues");
                    System.out.println("Fichaje:         ============");
                    System.out.println("Ficha:  ======");
                }else if(UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraIni(),fichaje.getHoraEntrada())>0 
                        && UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraFin(),fichaje.getHoraSalida())<0){
                    System.out.println("Caso 1");
                    System.out.println("Fichaje:============");
                    System.out.println("Ficha:     ======");
                    segundos+=UtilsContabilizar.dimeDuracion(fichasHorario.get(j).getHoraIni(), fichasHorario.get(j).getHoraFin());
                    //Creamos el fichaje ficticio nuevo, para no perder las horas
                    FichajeRecuentoBean fichajeResto=new FichajeRecuentoBean(fichaje);
                    fichajeResto.setHoraEntrada(fichasHorario.get(j).getHoraFin());
                    //Modifico el fichaje de recuento existente, quitandole las horas lectivas que ya están contadas.
                    fichaje.setHoraSalida(fichasHorario.get(j).getHoraIni());
                    listaFichajesRecuento.add(i, fichajeResto);
                    i++; //Como hemos añadido un item más a la lista y la estamos recorriendo al reves, hay que mantener el indice para que este nuevo item tambien sea tratado.
                }else if(UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraIni(),fichaje.getHoraEntrada())>0
                        && UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraFin(),fichaje.getHoraSalida())>0){
                    System.out.println("Caso 2");
                    System.out.println("Fichaje:============");
                    System.out.println("Ficha:         ===========");
                    segundos+=UtilsContabilizar.dimeDuracion(fichasHorario.get(j).getHoraIni(), fichaje.getHoraSalida());
                    fichaje.setHoraSalida(fichasHorario.get(j).getHoraIni());
                    //listaFichajesRecuento.add(i+1, fichajeResto);
                }else if(UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraIni(),fichaje.getHoraEntrada())<0
                        && UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraFin(),fichaje.getHoraSalida())<0){
                    System.out.println("Caso 3");
                    System.out.println("Fichaje:   ============");
                    System.out.println("Ficha:  ======");
                    segundos+=UtilsContabilizar.dimeDuracion(fichaje.getHoraEntrada(),fichasHorario.get(j).getHoraFin());
                    fichaje.setHoraEntrada(fichasHorario.get(j).getHoraFin());
                }else if(UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraIni(),fichaje.getHoraEntrada())>0
                        && UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraFin(),fichaje.getHoraSalida())<0){
                    System.out.println("Fichaje:   =======");
                    System.out.println("Ficha:  =============");
                    System.out.println("Caso 4");
                    segundos+=UtilsContabilizar.dimeDuracion(fichasHorario.get(j).getHoraIni(),fichasHorario.get(j).getHoraFin());
                    //Se ha completado todo el tiempo, se borra el fichaje para no tratarlo más.
                    listaFichajesRecuento.remove(i);
                    seguir=false;
                }else{
                    System.out.println("ERROR: por aquí no debería pasar, ya se deberían haber tratado todos los casos");
                }
                System.out.println("Resultado ->Fichas      Fichajes");
                System.out.println(fichaje.getFecha()+": "+fichasHorario.get(j).getHoraIni()+" -> "+fichaje.getHoraEntrada());
                System.out.println("            "+fichasHorario.get(j).getHoraFin()+" -> "+fichaje.getHoraSalida() );
                System.out.println("***********************************************************");
            }
        }
        System.out.println("Hashmap: "+horario.toString());
        return segundos;
    }

    /**
     * Contabiliza todo lo que quede en la lista de fichajes de recuento
     * @param listaFichajesRecuento
     * @return 
     */
    private int contabilizaHorasNoLectivas(ArrayList<FichajeRecuentoBean> listaFichajesRecuento) {
        int segundos=0;
        for (FichajeRecuentoBean fichajeRecuento : listaFichajesRecuento) {
            segundos+=UtilsContabilizar.dimeDuracion(fichajeRecuento.getHoraEntrada(),fichajeRecuento.getHoraSalida());
        }
        return segundos;
    }
}
