/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.basedatos;

import com.iteku.beans.EventoBean;
import com.iteku.beans.FichajeBean;
import com.iteku.utils.FechasUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author vPalomo
 */
public class GestionEventosBD {
    /**
     * Devuelve la lista de eventos del mes actual
     * @return 
     */
    public static ArrayList<EventoBean> getListaEventos(){
        String mes=FechasUtils.dameMesFechaActual();
        return getListaEventos(Integer.parseInt(mes));
    }
    /**
     * 
     * @param mes de 1 a 12 para los meses y 0 para todos los meses
     * @return 
     */
    public static ArrayList<EventoBean> getListaEventos(int mes){
        ArrayList<EventoBean> listaResult = new ArrayList<>();
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            EventoBean evento;
            PreparedStatement consulta;
            if(mes==0){
                consulta = conexion.prepareStatement("SELECT idEvento, fecha, horaIni, horaFin, diaCompleto, descripcion FROM eventos WHERE curso=?");
                consulta.setString(1, FechasUtils.getCursoActual());
            }else{
                consulta = conexion.prepareStatement("SELECT idEvento, fecha, horaIni, horaFin, diaCompleto, descripcion FROM eventos WHERE curso=? and MONTH(fecha)=?");
                consulta.setString(1, FechasUtils.getCursoActual());
                consulta.setString(2, ""+mes);
            }
            
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()){
                evento=new EventoBean();
                evento.setIdEvento(resultado.getInt(1));
                evento.setFecha(FechasUtils.fecha(resultado.getString(2), "/"));
                evento.setHoraIni(resultado.getString(3));
                evento.setHoraFin(resultado.getString(4));
                evento.setDiaCompleto("true".equals(resultado.getString(5).trim())?true:false);
                evento.setDescripcion(resultado.getString(6));
                listaResult.add(evento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            
        }finally{
            try {
                //System.out.println("Saliendo de la base de datos");
                conexion.close();
            } catch (SQLException ex) {
            }
        }
        return listaResult;
    }
}
