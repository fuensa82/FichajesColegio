/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.recuento;

import com.iteku.basedatos.GestionDetallesInformesBD;
import com.iteku.basedatos.GestionEventosBD;
import com.iteku.basedatos.GestionFichajeBD;
import com.iteku.basedatos.GestionHorasExtrasBD;
import com.iteku.basedatos.GestionInformesBD;
import com.iteku.beans.DetalleInformeBean;
import com.iteku.beans.EventoBean;
import com.iteku.beans.FichaBean;
import com.iteku.beans.FichajeBean;
import com.iteku.beans.FichajeRecuentoBean;
import com.iteku.beans.HoraExtraBean;
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
     * Prepara todos los datos de un profesor y un mes concreto para empezar el recuento de dicho profesor. Realiza el recuento y lo
     * guarda en la base de datos, si hubiera otro informe anterior del mismo profesor y mes lo elimina.
     * @param profesor
     * @param mes 
     */
    public void contabilizarConMesYProfesor(ProfesorBean profesor, int mes){
        GestionDetallesInformesBD.deleteDetallesInforme(profesor, mes);        

        //Preparamos todos los datos. Lista de fichas de horario.
        ArrayList<FichajeBean> listaFichajes = GestionFichajeBD.getListaFichajesProfesor(profesor,mes);
        ArrayList<FichajeRecuentoBean> listaFichajesRecuento= UtilsContabilizar.convertirFichajes(listaFichajes);

        /**
         * Contabilizar horas en eventos de día completo
         */
        
        int segundosEventosCompletos=contabilizarEventosCompletos(listaFichajesRecuento, profesor,mes);
        
        /**
         * Contabilizar horas en eventos de tiempo parcial
         */
        
        ArrayList<EventoBean> listaEventos=GestionEventosBD.getListaEventosProfesor(false, profesor, mes);
        int segundosEventosParciales=contabilizarEventosParciales(listaFichajesRecuento,listaEventos, profesor, mes, "C");
        
        /**
         * Contabilizamos las horas lectivas
         */
        ArrayList<FichaBean> listaFichasLectivas=UtilsContabilizar.getHorarioCompacto(profesor, "L");
        int segundosLectivos=contabilizaHorasLectivasOCmplementarias(listaFichajesRecuento, listaFichasLectivas, profesor,"L", mes);
        
        /**
         * Contabilizar horas complementarias
         */
        ArrayList<FichaBean> listaFichasComplementarias=UtilsContabilizar.getHorarioCompacto(profesor, "C");
        int segundosComplementarios=contabilizaHorasLectivasOCmplementarias(listaFichajesRecuento, listaFichasComplementarias, profesor,"C", mes);
        
        /**
         * Contabilizamos la horas no lectivas (el resto de lo que quede en los fichajes.
         */
        int segundosNLectivos=contabilizaHorasNoLectivas(listaFichajesRecuento, profesor, true, mes);
        
        /**
         * Contabilizamos las horas extra añadidas al profesor
         */
        ArrayList<HoraExtraBean> listaHorasExtra=GestionHorasExtrasBD.getHorasExtraProfesor(profesor, mes);
        HashMap<String, Integer> tablaHorasExtra = contabilizaHorasExtra(listaHorasExtra, profesor, mes);
        
        System.out.println("Segundos de horas lectivas:        "+Utils.convierteSegundos(segundosLectivos));
        System.out.println("Segundos de horas complementarias: "+Utils.convierteSegundos(segundosComplementarios));
        System.out.println("Segundos de horas no lectivas:     "+Utils.convierteSegundos(segundosNLectivos));
        System.out.println("Segundos eventos completos:        "+Utils.convierteSegundos(segundosEventosCompletos));
        System.out.println("Segundos eventos parciales:        "+Utils.convierteSegundos(segundosEventosParciales));
        System.out.println("Total:                             "+Utils.convierteSegundos((segundosComplementarios+segundosLectivos+segundosNLectivos+segundosEventosCompletos+segundosEventosParciales)));
        
        /**
         * Comprobacion
         */
        listaFichajes = GestionFichajeBD.getListaFichajesProfesor(profesor,mes);
        listaFichajesRecuento= UtilsContabilizar.convertirFichajes(listaFichajes);
        int segundosValidacion=contabilizaHorasNoLectivas(listaFichajesRecuento, profesor, false, mes);
        int segundosValidacion2=segundosComplementarios+segundosLectivos+segundosNLectivos+segundosEventosCompletos+segundosEventosParciales;
        System.out.println("Comprobacion: "+Utils.convierteSegundos(segundosValidacion));
        String obser=segundosValidacion==segundosValidacion2?"Correcto":"No coinciden las horas en el colegio, con las horas calculadas de cada tipo.";
        
        segundosComplementarios+=segundosEventosCompletos;
        segundosComplementarios+=segundosEventosParciales;

        //Guardamos en la base de datos
        GestionInformesBD.guardaInforme(profesor, obser, segundosLectivos, segundosNLectivos, segundosComplementarios,mes);
        
    }
    /**
     * Se le pasa la lista de fichajes que está aun por tratar y la lista de fichas de horario que le corresponden al profesor.
     * La listaFichajesRecuento se verá modificada, quitando el tiempo que coincida con el horario.
     * @param listaFichajesRecuento
     * @param listaFichas
     * @return Los segundos que se han completado de horas lectivas, luego si se quiere se pasaran a horas, minutos
     */
    private int contabilizaHorasLectivasOCmplementarias(ArrayList<FichajeRecuentoBean> listaFichajesRecuento, ArrayList<FichaBean> listaFichas, ProfesorBean profesor, String tipoHora, int mes) {
        
        HashMap<String,ArrayList<FichaBean>> horario=UtilsContabilizar.convertirHorario(listaFichas);
        int segundos=0;
        FichajeRecuentoBean fichaje;
        ArrayList<FichaBean> fichasHorario;
        for(int i=listaFichajesRecuento.size()-1;i>=0;i--){
            DetalleInformeBean detalleInforme=new DetalleInformeBean();
            detalleInforme.setIdProfesor(profesor.getIdProfesor());
            fichaje=listaFichajesRecuento.get(i);
            fichasHorario=horario.get(""+fichaje.getDiaSemana());
//            System.out.println("Fichashorarios: "+fichasHorario.size());
            boolean seguir=true;
            for(int j=0;j<fichasHorario.size() && seguir;j++){
//                System.out.println("Comparando i="+i+" j="+j);
//                System.out.println("            Fichas      Fichajes");
//                System.out.println(fichaje.getFecha()+": "+fichasHorario.get(j).getHoraIni()+" -> "+fichaje.getHoraEntrada());
//                System.out.println("            "+fichasHorario.get(j).getHoraFin()+" -> "+fichaje.getHoraSalida() );
                //Caso. Se empieza la ficha de horario estando en el centro y se termina la ficha estando en el centro
                if(UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraIni(),fichaje.getHoraSalida())>=0){
//                    System.out.println("Caso todo antes");
//                    System.out.println("Fichaje:============");
//                    System.out.println("Ficha:                ======");
                }else if(UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraFin(),fichaje.getHoraEntrada())<=0) {
//                    System.out.println("Caso todo despues");
//                    System.out.println("Fichaje:         ============");
//                    System.out.println("Ficha:  ======");
                }else if(UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraIni(),fichaje.getHoraEntrada())>=0 
                        && UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraFin(),fichaje.getHoraSalida())<0){
//                    System.out.println("Caso 1");
//                    System.out.println("Fichaje:============");
//                    System.out.println("Ficha:     ======");
                    segundos+=UtilsContabilizar.dimeDuracion(fichasHorario.get(j).getHoraIni(), fichasHorario.get(j).getHoraFin());
                    
                    detalleInforme.setTotalHoras(UtilsContabilizar.dimeDuracion(fichasHorario.get(j).getHoraIni(), fichasHorario.get(j).getHoraFin()));
                    detalleInforme.setFecha(fichaje.getFecha());
                    detalleInforme.setHoraIni(fichasHorario.get(j).getHoraIni());
                    detalleInforme.setHoraFin(fichasHorario.get(j).getHoraFin());
                    detalleInforme.setTipoHora(tipoHora);
                    GestionDetallesInformesBD.guardaDetalleInforme(detalleInforme, "Caso 1 - contabilizaHorasLectivasOCmplementarias", mes);
                    
                    //Creamos el fichaje ficticio nuevo, para no perder las horas
                    FichajeRecuentoBean fichajeResto=new FichajeRecuentoBean(fichaje);
                    fichajeResto.setHoraEntrada(fichasHorario.get(j).getHoraFin());
                    //Modifico el fichaje de recuento existente, quitandole las horas lectivas que ya están contadas.
                    fichaje.setHoraSalida(fichasHorario.get(j).getHoraIni());
                    listaFichajesRecuento.add(i, fichajeResto);
                    i++; //Como hemos añadido un item más a la lista y la estamos recorriendo al reves, hay que mantener el indice para que este nuevo item tambien sea tratado.
                }else if(UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraIni(),fichaje.getHoraEntrada())>=0
                        && UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraFin(),fichaje.getHoraSalida())>0){
//                    System.out.println("Caso 2");
//                    System.out.println("Fichaje:============");
//                    System.out.println("Ficha:         ===========");
                    segundos+=UtilsContabilizar.dimeDuracion(fichasHorario.get(j).getHoraIni(), fichaje.getHoraSalida());
                    
                    detalleInforme.setTotalHoras(UtilsContabilizar.dimeDuracion(fichasHorario.get(j).getHoraIni(), fichaje.getHoraSalida()));
                    detalleInforme.setFecha(fichaje.getFecha());
                    detalleInforme.setHoraIni(fichasHorario.get(j).getHoraIni());
                    detalleInforme.setHoraFin(fichaje.getHoraSalida());
                    detalleInforme.setTipoHora(tipoHora);
                    GestionDetallesInformesBD.guardaDetalleInforme(detalleInforme, "Caso 2 - contabilizaHorasLectivasOCmplementarias", mes);
                    
                    fichaje.setHoraSalida(fichasHorario.get(j).getHoraIni());
                    //listaFichajesRecuento.add(i+1, fichajeResto);
                }else if(UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraIni(),fichaje.getHoraEntrada())<=0
                        && UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraFin(),fichaje.getHoraSalida())<0){
//                    System.out.println("Caso 3");
//                    System.out.println("Fichaje:   ============");
//                    System.out.println("Ficha:  ======");
                    segundos+=UtilsContabilizar.dimeDuracion(fichaje.getHoraEntrada(),fichasHorario.get(j).getHoraFin());
                    
                    detalleInforme.setTotalHoras(UtilsContabilizar.dimeDuracion(fichaje.getHoraEntrada(),fichasHorario.get(j).getHoraFin()));
                    detalleInforme.setFecha(fichaje.getFecha());
                    detalleInforme.setHoraIni(fichaje.getHoraEntrada());
                    detalleInforme.setHoraFin(fichasHorario.get(j).getHoraFin());
                    detalleInforme.setTipoHora(tipoHora);
                    GestionDetallesInformesBD.guardaDetalleInforme(detalleInforme, "Caso 3 - contabilizaHorasLectivasOCmplementarias", mes);
                    
                    fichaje.setHoraEntrada(fichasHorario.get(j).getHoraFin());
                }else if(UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraIni(),fichaje.getHoraEntrada())<=0
                        && UtilsContabilizar.compararHoras(fichasHorario.get(j).getHoraFin(),fichaje.getHoraSalida())>0){
//                    System.out.println("Fichaje:   =======");
//                    System.out.println("Ficha:  =============");
//                    System.out.println("Caso 4");
                    segundos+=UtilsContabilizar.dimeDuracion(fichaje.getHoraEntrada(),fichaje.getHoraSalida());
                    
                    detalleInforme.setTotalHoras(UtilsContabilizar.dimeDuracion(fichaje.getHoraEntrada(),fichaje.getHoraSalida()));
                    detalleInforme.setFecha(fichaje.getFecha());
                    detalleInforme.setHoraIni(fichaje.getHoraEntrada());
                    detalleInforme.setHoraFin(fichaje.getHoraSalida());
                    detalleInforme.setTipoHora(tipoHora);
                    GestionDetallesInformesBD.guardaDetalleInforme(detalleInforme," Ultimmo caso - contabilizaHorasLectivasOCmplementarias", mes);
                    
                    //Se ha completado todo el tiempo, se borra el fichaje para no tratarlo más.
                    listaFichajesRecuento.remove(i);
                    seguir=false;
                }else{
//                    System.out.println("ERROR: por aquí no debería pasar, ya se deberían haber tratado todos los casos");
                    
                }
                //System.out.println("Resultado ->Fichas      Fichajes");
//                System.out.println(fichaje.getFecha()+": "+fichasHorario.get(j).getHoraIni()+" -> "+fichaje.getHoraEntrada());
//                System.out.println("            "+fichasHorario.get(j).getHoraFin()+" -> "+fichaje.getHoraSalida() );
//                System.out.println("***********************************************************");
            }
        }
        //System.out.println("Hashmap: "+horario.toString());
        return segundos;
    }

    /**
     * Contabiliza todo lo que quede en la lista de fichajes de recuento
     * @param listaFichajesRecuento
     * @return 
     */
    private int contabilizaHorasNoLectivas(ArrayList<FichajeRecuentoBean> listaFichajesRecuento, ProfesorBean profesor, boolean guardar, int mes) {
        int segundos=0;
        for (FichajeRecuentoBean fichajeRecuento : listaFichajesRecuento) {
//            System.out.println(fichajeRecuento.getFecha()+" "+fichajeRecuento.getHoraEntrada()+"->"+fichajeRecuento.getHoraSalida()+" => "+UtilsContabilizar.dimeDuracion(fichajeRecuento.getHoraEntrada(),fichajeRecuento.getHoraSalida()));
            segundos+=UtilsContabilizar.dimeDuracion(fichajeRecuento.getHoraEntrada(),fichajeRecuento.getHoraSalida());
            
            DetalleInformeBean detalleInforme=new DetalleInformeBean();
            detalleInforme.setIdProfesor(profesor.getIdProfesor());
            detalleInforme.setTotalHoras(UtilsContabilizar.dimeDuracion(fichajeRecuento.getHoraEntrada(),fichajeRecuento.getHoraSalida()));
            detalleInforme.setFecha(fichajeRecuento.getFecha());
            detalleInforme.setHoraIni(fichajeRecuento.getHoraEntrada());
            detalleInforme.setHoraFin(fichajeRecuento.getHoraSalida());
            detalleInforme.setTipoHora("NL");
            if(guardar && detalleInforme.getTotalHoras()>0)GestionDetallesInformesBD.guardaDetalleInforme(detalleInforme, "contabilizaHorasNoLectivas", mes);
                    
        }
        return segundos;
    }

    /**
     * Metodo que cuentas el tiempo (en segundos) que se ha estado en un evento de día completo, teniendo en cuenta las horas
     * de entrada y salida del profesor.
     * @param listaFichajesRecuento Lista de fichajes con la que se va a comparar los eventos de día completo que
     * tiene asignado dicho profesor. Se eliminaran las horas que se contabilicen.
     * @param profesor Con tener relleno el codigo de profesor nos valdrá
     * @param mes Mes que se está calculando, Enero el 1, febrero 2,...
     * @return  Segundo que se ha estado en el colegio el día del evento.
     */
    private int contabilizarEventosCompletos(ArrayList<FichajeRecuentoBean> listaFichajesRecuento, ProfesorBean profesor, int mes) {
        int segundos=0;
        ArrayList<EventoBean> listaEventos=GestionEventosBD.getListaEventosProfesor(true, profesor, mes);
        for (EventoBean evento:listaEventos){
            for(int i=listaFichajesRecuento.size()-1;i>=0;i--){
                FichajeRecuentoBean fichaje=listaFichajesRecuento.get(i);
                if(fichaje.getFecha().equals(evento.getFecha())){
                    segundos+=UtilsContabilizar.dimeDuracion(fichaje.getHoraEntrada(), fichaje.getHoraSalida());
                    DetalleInformeBean detalleInforme=new DetalleInformeBean();
                    detalleInforme.setIdProfesor(profesor.getIdProfesor());
                    detalleInforme.setTotalHoras(UtilsContabilizar.dimeDuracion(fichaje.getHoraEntrada(), fichaje.getHoraSalida()));
                    detalleInforme.setFecha(fichaje.getFecha());
                    detalleInforme.setHoraIni(fichaje.getHoraEntrada());
                    detalleInforme.setHoraFin(fichaje.getHoraSalida());
                    detalleInforme.setTipoHora("C");
                    GestionDetallesInformesBD.guardaDetalleInforme(detalleInforme," eventoCompleto", mes);
                    
                    listaFichajesRecuento.remove(i);
//                    System.out.println("Fichaje en evento: "+fichaje.getFecha()+" "+fichaje.getHoraEntrada()+" "+fichaje.getHoraSalida());
                }
            }
        }
        return segundos;
    }

    private int contabilizarEventosParciales(ArrayList<FichajeRecuentoBean> listaFichajesRecuento, ArrayList<EventoBean> listaEventos, ProfesorBean profesor, int mes, String tipoHora) {
        int segundos=0;
        FichajeRecuentoBean fichaje;
        for(int i=listaFichajesRecuento.size()-1;i>=0;i--){
            DetalleInformeBean detalleInforme=new DetalleInformeBean();
            detalleInforme.setIdProfesor(profesor.getIdProfesor());
            boolean seguir=true;
            fichaje=listaFichajesRecuento.get(i);
            for(EventoBean evento: listaEventos ){
                if(evento.getFecha().equalsIgnoreCase(fichaje.getFecha())){
                    if(UtilsContabilizar.compararHoras(evento.getHoraIni(),fichaje.getHoraSalida())>=0){
    //                    System.out.println("Caso todo antes");
    //                    System.out.println("Fichaje:============");
    //                    System.out.println("Ficha:                ======");
                    }else if(UtilsContabilizar.compararHoras(evento.getHoraFin(),fichaje.getHoraEntrada())<=0) {
    //                    System.out.println("Caso todo despues");
    //                    System.out.println("Fichaje:         ============");
    //                    System.out.println("Ficha:  ======");
                    }else if(UtilsContabilizar.compararHoras(evento.getHoraIni(),fichaje.getHoraEntrada())>=0 
                            && UtilsContabilizar.compararHoras(evento.getHoraFin(),fichaje.getHoraSalida())<0){
    //                    System.out.println("Caso 1");
    //                    System.out.println("Fichaje:============");
    //                    System.out.println("Ficha:     ======");
                        segundos+=UtilsContabilizar.dimeDuracion(evento.getHoraIni(), evento.getHoraFin());

                        detalleInforme.setTotalHoras(UtilsContabilizar.dimeDuracion(evento.getHoraIni(), evento.getHoraFin()));
                        detalleInforme.setFecha(fichaje.getFecha());
                        detalleInforme.setHoraIni(evento.getHoraIni());
                        detalleInforme.setHoraFin(evento.getHoraFin());
                        detalleInforme.setTipoHora(tipoHora);
                        GestionDetallesInformesBD.guardaDetalleInforme(detalleInforme, "Caso 1 - contabilizarEventosParciales", mes);

                        //Creamos el fichaje ficticio nuevo, para no perder las horas
                        FichajeRecuentoBean fichajeResto=new FichajeRecuentoBean(fichaje);
                        fichajeResto.setHoraEntrada(evento.getHoraFin());
                        //Modifico el fichaje de recuento existente, quitandole las horas lectivas que ya están contadas.
                        fichaje.setHoraSalida(evento.getHoraIni());
                        listaFichajesRecuento.add(i, fichajeResto);
                        i++; //Como hemos añadido un item más a la lista y la estamos recorriendo al reves, hay que mantener el indice para que este nuevo item tambien sea tratado.
                    }else if(UtilsContabilizar.compararHoras(evento.getHoraIni(),fichaje.getHoraEntrada())>=0
                            && UtilsContabilizar.compararHoras(evento.getHoraFin(),fichaje.getHoraSalida())>0){
    //                    System.out.println("Caso 2");
    //                    System.out.println("Fichaje:============");
    //                    System.out.println("Ficha:         ===========");
                        segundos+=UtilsContabilizar.dimeDuracion(evento.getHoraIni(), fichaje.getHoraSalida());

                        detalleInforme.setTotalHoras(UtilsContabilizar.dimeDuracion(evento.getHoraIni(), fichaje.getHoraSalida()));
                        detalleInforme.setFecha(fichaje.getFecha());
                        detalleInforme.setHoraIni(evento.getHoraIni());
                        detalleInforme.setHoraFin(fichaje.getHoraSalida());
                        detalleInforme.setTipoHora(tipoHora);
                        GestionDetallesInformesBD.guardaDetalleInforme(detalleInforme, "Caso 2 - contabilizarEventosParciales", mes);

                        fichaje.setHoraSalida(evento.getHoraIni());
                        //listaFichajesRecuento.add(i+1, fichajeResto);
                    }else if(UtilsContabilizar.compararHoras(evento.getHoraIni(),fichaje.getHoraEntrada())<=0
                            && UtilsContabilizar.compararHoras(evento.getHoraFin(),fichaje.getHoraSalida())<0){
    //                    System.out.println("Caso 3");
    //                    System.out.println("Fichaje:   ============");
    //                    System.out.println("Ficha:  ======");
                        segundos+=UtilsContabilizar.dimeDuracion(fichaje.getHoraEntrada(),evento.getHoraFin());

                        detalleInforme.setTotalHoras(UtilsContabilizar.dimeDuracion(fichaje.getHoraEntrada(),evento.getHoraFin()));
                        detalleInforme.setFecha(fichaje.getFecha());
                        detalleInforme.setHoraIni(fichaje.getHoraEntrada());
                        detalleInforme.setHoraFin(evento.getHoraFin());
                        detalleInforme.setTipoHora(tipoHora);
                        GestionDetallesInformesBD.guardaDetalleInforme(detalleInforme, "Caso 3 - contabilizarEventosParciales", mes);

                        fichaje.setHoraEntrada(evento.getHoraFin());
                    }else if(UtilsContabilizar.compararHoras(evento.getHoraIni(),fichaje.getHoraEntrada())>=0
                            && UtilsContabilizar.compararHoras(evento.getHoraFin(),fichaje.getHoraSalida())<0){
    //                    System.out.println("Fichaje:   =======");
    //                    System.out.println("Ficha:  =============");
    //                    System.out.println("Caso 4");
                        segundos+=UtilsContabilizar.dimeDuracion(fichaje.getHoraEntrada(),fichaje.getHoraSalida());

                        detalleInforme.setTotalHoras(UtilsContabilizar.dimeDuracion(fichaje.getHoraEntrada(),fichaje.getHoraSalida()));
                        detalleInforme.setFecha(fichaje.getFecha());
                        detalleInforme.setHoraIni(fichaje.getHoraEntrada());
                        detalleInforme.setHoraFin(fichaje.getHoraSalida());
                        detalleInforme.setTipoHora(tipoHora);
                        GestionDetallesInformesBD.guardaDetalleInforme(detalleInforme," Ultimmo caso - contabilizarEventosParciales", mes);

                        //Se ha completado todo el tiempo, se borra el fichaje para no tratarlo más.
                        listaFichajesRecuento.remove(i);
                        seguir=false;
                    }else{
    //                    System.out.println("ERROR: por aquí no debería pasar, ya se deberían haber tratado todos los casos");

                    }
                }
            }
        }
        return segundos;
    }

    private HashMap<String, Integer> contabilizaHorasExtra(ArrayList<HoraExtraBean> listaHorasExtra, ProfesorBean profesor, int mes) {
        HashMap<String, Integer> tabla=new HashMap<String, Integer>();
        int segundosL=0;
        int segundosC=0;
        int segundosNL=0;
        for (HoraExtraBean horaExtra : listaHorasExtra) {
//            System.out.println(fichajeRecuento.getFecha()+" "+fichajeRecuento.getHoraEntrada()+"->"+fichajeRecuento.getHoraSalida()+" => "+UtilsContabilizar.dimeDuracion(fichajeRecuento.getHoraEntrada(),fichajeRecuento.getHoraSalida()));
            
            
            DetalleInformeBean detalleInforme=new DetalleInformeBean();
            detalleInforme.setIdProfesor(profesor.getIdProfesor());
            detalleInforme.setTotalHoras(UtilsContabilizar.dimeDuracion(horaExtra.getHoraIni(),horaExtra.getHoraFin()));
            detalleInforme.setFecha(horaExtra.getFecha());
            detalleInforme.setHoraIni(horaExtra.getHoraIni());
            detalleInforme.setHoraFin(horaExtra.getHoraFin());
            
            if(horaExtra.getTipoHora().equalsIgnoreCase("L")){
                detalleInforme.setTipoHora("L");
                segundosL+=UtilsContabilizar.dimeDuracion(horaExtra.getHoraIni(),horaExtra.getHoraFin());
            }else if(horaExtra.getTipoHora().equalsIgnoreCase("C")){
                detalleInforme.setTipoHora("C");
                segundosC+=UtilsContabilizar.dimeDuracion(horaExtra.getHoraIni(),horaExtra.getHoraFin());
            }else if(horaExtra.getTipoHora().equalsIgnoreCase("NL")){
                detalleInforme.setTipoHora("NL");
                segundosNL+=UtilsContabilizar.dimeDuracion(horaExtra.getHoraIni(),horaExtra.getHoraFin());
            }
            
            GestionDetallesInformesBD.guardaDetalleInforme(detalleInforme, "contabilizaHorasExtra", mes);
                    
        }
        tabla.put("L", segundosL);
        tabla.put("C", segundosC);
        tabla.put("NL", segundosNL);
        return tabla;
    }
}
