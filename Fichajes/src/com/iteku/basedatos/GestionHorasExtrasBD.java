/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.basedatos;

import com.iteku.beans.HoraExtraBean;
import com.iteku.beans.ProfesorBean;
import com.iteku.utils.FechasUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Víctor
 */
public class GestionHorasExtrasBD {
    public static boolean ponHorasExtra(HoraExtraBean hora, ProfesorBean profesor){
        boolean result=false;
        
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement insert1 = conexion.prepareStatement(
                    "INSERT INTO `colsan`.`horasextra` (`fecha`, `horaIni`, `horaFin`, `motivo`, `idProfesor`, `tipoHora`, `curso`) VALUES (?, ?, ?, ?, ?, ?, ?);");

            insert1.setString(1, hora.getFechaMysql());
            insert1.setString(2, hora.getHoraIniMysql());
            insert1.setString(3, hora.getHoraFinMysql());
            insert1.setString(4, hora.getMotivo());
            insert1.setString(5, ""+profesor.getIdProfesor());
            insert1.setString(6, hora.getTipoHora());
            insert1.setString(7, FechasUtils.getCursoActual());
            insert1.executeUpdate();

            return true; //Correcto

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        } finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestionHorasExtrasBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }
    /**
     * 
     * @param p
     * @param mes del 1 al 12
     * @return 
     */
    public static ArrayList<HoraExtraBean> getHorasExtraProfesor(ProfesorBean p, int mes){
        ArrayList<HoraExtraBean> result;
        result = new ArrayList();
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            HoraExtraBean hora;
            PreparedStatement consulta;
            if(mes==0){
                consulta = conexion.prepareStatement(
                    "SELECT idHoraExtra, idProfesor, fecha, horaIni, horaFin, motivo, fechaAlta, tipoHora FROM horasextra WHERE idProfesor=?");
                consulta.setString(1, ""+p.getIdProfesor());
            }else{
                consulta= conexion.prepareStatement(
                    "SELECT idHoraExtra, idProfesor, fecha, horaIni, horaFin, motivo, fechaAlta, tipoHora FROM horasextra WHERE idProfesor=? and MONTH(fecha)=?");
                consulta.setString(1, ""+p.getIdProfesor());
                consulta.setString(2, ""+mes);
            }            
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()){
                hora=new HoraExtraBean();
                hora.setIdHoraExtra(resultado.getInt(1));
                hora.setIdProfesor(resultado.getInt(2));
                hora.setFecha(FechasUtils.fecha(resultado.getString(3)));
                hora.setHoraIni(resultado.getString(4));
                hora.setHoraFin(resultado.getString(5));
                hora.setMotivo(resultado.getString(6));
                hora.setFechaAlta(resultado.getString(7));
                hora.setTipoHora(resultado.getString(8));
                result.add(hora);
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
        return result;
    }
}
