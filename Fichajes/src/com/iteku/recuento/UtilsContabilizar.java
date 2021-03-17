/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.recuento;

import com.iteku.backofficefichajes.Config;
import com.iteku.basedatos.GestionProfesoresBD;
import com.iteku.beans.FichaBean;
import com.iteku.beans.FichajeBean;
import com.iteku.beans.FichajeRecuentoBean;
import com.iteku.beans.ProfesorBean;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author vPalomo
 */
public class UtilsContabilizar {
    /**
     * Convierte la lista de fichajes, en los que cada objeto es solo una salida, o una entrada, en una lista
     * donde cada objeto contiene la hora y salida, es como convertirlo en el tramo de horas que han estado
     * dentro del centro
     * @param listaFichajes Lista con los fichajes a tratar.
     * @return Un ArrayList con los nuevos objetos que contienen los tramos de presencia en el centro
     */
    public static ArrayList<FichajeRecuentoBean> convertirFichajes(ArrayList<FichajeBean> listaFichajes){
        ArrayList<FichajeRecuentoBean> result=new ArrayList<>();
        FichajeRecuentoBean fichaRecuento=new FichajeRecuentoBean();
        String fechaAnterior="";
        for (FichajeBean fichajeReal : listaFichajes) {
            if(("".equals(fechaAnterior) || !fechaAnterior.equalsIgnoreCase(fichajeReal.getFecha())) && !fichajeReal.isEsEntrada()) {
                System.out.println("Error, es el fichaje del primer día y no es una entrada. Esto en principio no se puede dar");
            }else{
                //es la parte de si todo es correcto
                if(fichajeReal.isEsEntrada()){
                    fichaRecuento=new FichajeRecuentoBean();
                    if(UtilsContabilizar.compararHoras(fichajeReal.getHora(),Config.getHoraMinima())>0){
                        fichaRecuento.setHoraEntrada(fichajeReal.getHora());
                    }else{
                        fichaRecuento.setHoraEntrada(Config.getHoraMinima());
                    }
                    fichaRecuento.setFecha(fichajeReal.getFecha());
                    result.add(fichaRecuento);
                }else{
                    if(UtilsContabilizar.compararHoras(fichajeReal.getHora(),Config.getHoraMaxima())>0){
                        fichaRecuento.setHoraSalida(Config.getHoraMaxima());
                    }else{
                        fichaRecuento.setHoraSalida(fichajeReal.getHora());
                    }
                    fichaRecuento.setIdProfesor(fichajeReal.getIdProfesor());
                }
            }
            fechaAnterior=fichajeReal.getFecha();
        }
        
        
        return result;
    }
    /**
     * Compara las dos horas y si la primera es mayor devuelve 1, si son iguales 0 y si es menor -1
     * @param horaMayor
     * @param horaMenor
     * @return 
     */
    public static int compararHoras(String horaMayor, String horaMenor){
        System.out.println("CompararHoras "+horaMayor+" "+horaMenor);
        LocalTime horaM=LocalTime.of(Integer.parseInt(horaMayor.substring(0, 2)), Integer.parseInt(horaMayor.substring(3, 5)), Integer.parseInt(horaMayor.substring(7)));//=new LocalTime()
        LocalTime horam=LocalTime.of(Integer.parseInt(horaMenor.substring(0, 2)), Integer.parseInt(horaMenor.substring(3, 5)), Integer.parseInt(horaMenor.substring(7)));//=new LocalTime()
        System.out.println("Result:" +horaM.compareTo(horam));
        return horaM.compareTo(horam);
    }

    /**
     * Convierte el horario en una HasMaps cuya clave es el día de la semana al que pertenecen las fichas de horario,
     * así será más facil recorrer los días para buscar las fichas de horario que corresponden
     * @param listaFichas
     * @return 
     */
    public static HashMap<String, ArrayList<FichaBean>> convertirHorario(ArrayList<FichaBean> listaFichas) {
        HashMap<String,ArrayList<FichaBean>> horario=new HashMap<>();
        for(int i=0;i<Config.getDias().length;i++){
            horario.put(""+Config.getDias()[i], new ArrayList<>());
        }
        
        for (FichaBean ficha : listaFichas) {
            horario.get(""+ficha.getDia()).add(ficha);
        }
        return horario;
    }
    
    /**
     * Consulta el horario de un profesor y devuelve un arrayList con todas sus fichas
     * @param profesor Con que contenga solo el ID vale.
     * @return 
     */
    public static ArrayList<FichaBean> getHorario(ProfesorBean profesor, String tipoHora){
        //imprimeHorario(listaFichas);
        //System.out.println("************************************************************************* ");
        return GestionProfesoresBD.getListaFichasCurso(profesor, tipoHora);
        
    }
    
    /**
     * 
     * @param diaCompleto
     * @param profesor
     * @param mes
     * @return 
     */
    public static ArrayList<FichaBean> getFichasRecuentoEventos(boolean diaCompleto,ProfesorBean profesor, int mes){
        ArrayList<FichaBean> listaFichas=new ArrayList<FichaBean>();
        
        return listaFichas;
    }
    
    /**
     * Genera las fichas de horarios pero agrupando todo lo que se puede agrupar, dos fichas consecutivas se
     * unen en una sola (Ini: 9:00, Fin: 10:00)(Ini: 10:00, Fin: 11:00) pasa a ser una sola ficha (Ini: 9:00, Fin: 11:00)
     * @param profesor
     * @param tipoHora Lectiva, no lectiva y complementaria(L, NL, C)
     * @return Un arrayList de fichas pero unificadas si son consecutivas.
     */
    public static ArrayList<FichaBean> getHorarioCompacto(ProfesorBean profesor, String tipoHora){
        ArrayList<FichaBean> listaFichas=UtilsContabilizar.getHorario(profesor, tipoHora);
        UtilsContabilizar.imprimeHorario(listaFichas);
        for(int i=listaFichas.size()-1;i>0;i--){ //Recorremos la lista desde el final al principio
            FichaBean fichaActual=listaFichas.get(i);
            FichaBean fichaAnterior=listaFichas.get(i-1);
            if(fichaActual.getDia()==fichaAnterior.getDia()){
                if(fichaActual.getHoraIni().trim().equals(fichaAnterior.getHoraFin().trim())){
                    listaFichas.remove(i);
                    fichaAnterior.setHoraFin(fichaActual.getHoraFin());
                }else{
                    //System.out.println("No se borra por no coincidir la hora");
                }
            }else{
                //System.out.println("No se borra por no coincidir el dia");
            }
        }
        //UtilsContabilizar.imprimeHorario(listaFichas);
        return listaFichas;
    }
    /**
     * Utilizadad para imprimir un arrayList completo de FichaBean. Para que todo salga "bonito" el objeto debería 
     * implementar el metodo toString
     * @param listaFichas 
     *  
     */
    public static void imprimeHorario(ArrayList<FichaBean> listaFichas){
        System.out.println("*******************Imprimendo***********************");
        for (FichaBean listaFicha : listaFichas) {
            System.out.println(listaFicha);
        }
    }
    /**
     * Utilidad que imprime un arrayList de objetos. El objeto debe implementar el metodo toString
     * @param cabecera
     * @param lista 
     * @deprecated 
     */
    public static void imprimeArray(String cabecera, ArrayList lista){
        System.out.println("***********"+cabecera+"*******************");
        for (Object objeto : lista) {
            System.out.println(objeto);
        }
    }
    
    /**
     * Dada una hora de inicio y una hora de fin, en formato hh:mm:ss, devuelve el número 
     * de segundos transcurridos entre una y otra. Siempre devuelve un número positivo
     * @param hIni
     * @param hFin
     * @return Los segundos transcurridos entre una y otra hora. Siempre en valor positivo o 0.
     */
    public static int dimeDuracion(String hIni, String hFin){
        int horasM=Integer.parseInt(hIni.substring(0, 2));
        int minutosM=Integer.parseInt(hIni.substring(3, 5));
        int segundosM=Integer.parseInt(hIni.substring(6))+minutosM*60+horasM*60*60;
        
        int horasm=Integer.parseInt(hFin.substring(0, 2));
        int minutosm=Integer.parseInt(hFin.substring(3, 5));
        int segundosm=Integer.parseInt(hFin.substring(6))+minutosm*60+horasm*60*60;
        //System.out.println(""+Integer.parseInt(hFin.substring(0, 2))+" "+Integer.parseInt(hFin.substring(3, 5))+" "+Integer.parseInt(hFin.substring(6)));
        if(segundosM>segundosm){
            return segundosM-segundosm;
        }else{
            return segundosm-segundosM;
        }
    }

}
