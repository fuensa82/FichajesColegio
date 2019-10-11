/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.basedatos;

import com.iteku.beans.FichaBean;
import com.iteku.beans.FichajeBean;
import com.iteku.beans.ProfesorBean;
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
public class GestionFichajeBD {

    /**
     * Busca los datos del profesor que coincide con la tarjeta pasada
     *
     * @param idTarjeta
     * @return
     */
    public static ProfesorBean getProfesor(String idTarjeta) {
        ProfesorBean profesor = new ProfesorBean();
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement(
                    "select idProfesor, nombre, apellidos, idTarjeta "
                    + "from profesores "
                    + "where idTarjeta=?");
            consulta.setString(1, idTarjeta);
            ResultSet resultado = consulta.executeQuery();
            if(!resultado.next()){
                System.out.println("Id tarjeta no econtrado. "+idTarjeta);
                return null;
            }
            profesor.setIdProfesor(resultado.getInt(1));
            profesor.setNombre(resultado.getString(2));
            profesor.setApellidos(resultado.getString(3));
            profesor.setIdTarjeta(resultado.getInt(4));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
        } finally {
            try {
                //System.out.println("Saliendo de la base de datos");
                conexion.close();
            } catch (SQLException ex) {
            }
        }
        return profesor;
    }
    /**
     * 
     * @param idTarjeta
     * @param entrada
     * @return 
     */
    public static ProfesorBean putFichaje(String idTarjeta, long time) {
        ProfesorBean profesor=GestionFichajeBD.getProfesor(idTarjeta);
        if(profesor==null){
            return null;
        }
        
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement insert1 = conexion.prepareStatement(
                    "INSERT INTO `colsan`.`fichajes` ( `currentTime`, `fecha`, `hora`, `idProfesor`, `terminal`,`dentro`,`curso`) VALUES (?, ?, ?, ?,2,?,?)");
            //Long time=System.currentTimeMillis();
            profesor.setCurrentTimeMillis(time);
            insert1.setString(1, ""+time);
            insert1.setString(2, FechasUtils.fechaHoyParaMysql());
            insert1.setString(3, FechasUtils.horaAhora());
            insert1.setString(4, ""+profesor.getIdProfesor());
            insert1.setString(5, ""+!profesor.isDentro());
            insert1.setString(6, FechasUtils.getCursoActual());
            insert1.executeUpdate();

            return profesor; //Correcto

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<FichajeBean> getListaFichajesProfesor(ProfesorBean profesor, int mes) {
        ArrayList<FichajeBean> listaResult;
        listaResult = new ArrayList<>();
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            FichajeBean fichaje;
            PreparedStatement consulta = conexion.prepareStatement(
                    "SELECT idFichaje, currentTime, fecha, hora, idProfesor, terminal, dentro, curso FROM fichajes WHERE idProfesor=? and curso=? and MONTH(fecha)=? order by fecha, hora");
            consulta.setString(1, ""+profesor.getIdProfesor());
            consulta.setString(2, FechasUtils.getCursoActual());
            consulta.setString(3, ""+mes);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()){
                fichaje=new FichajeBean();
                fichaje.setIdFichaje(resultado.getInt(1));
                fichaje.setCurrentTime(resultado.getLong(2));
                fichaje.setFecha(resultado.getString(3));
                fichaje.setHora(resultado.getString(4));
                fichaje.setIdProfesor(resultado.getInt(5));
                fichaje.setTerminal(resultado.getInt(6));
                fichaje.setEsEntrada("true".equals(resultado.getString(7).trim())?true:false);
                fichaje.setCurso(resultado.getString(8));
                listaResult.add(fichaje);
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
