/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.recuento;

import com.iteku.beans.FichajeBean;
import com.iteku.beans.FichajeRecuentoBean;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author vPalomo
 */
public class UtilsContabilizar {
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
                    fichaRecuento.setFecha(fichajeReal.getFecha());
                    fichaRecuento.setHoraEntrada(fichajeReal.getHora());
                    result.add(fichaRecuento);
                }else{
                    fichaRecuento.setHoraSalida(fichajeReal.getHora());
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
        LocalTime hora1=LocalTime.of(0, 0, 0);//=new LocalTime()
        return 1;
    }
}
