/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basedatos;

import com.beans.ProfesorBean;
import com.utils.FechasUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author vPalomo
 */
public class FichajeBD {

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
            
            consulta = conexion.prepareStatement("SELECT COUNT(*) FROM fichajes WHERE fecha=? and idProfesor=?");
            consulta.setString(1, FechasUtils.fechaHoyParaMysql());
            consulta.setString(2, ""+profesor.getIdProfesor());
            resultado = consulta.executeQuery();
            resultado.next();
            int numFicahjesHoy=resultado.getInt(1);
            profesor.setDentro((numFicahjesHoy%2)==0);

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
        ProfesorBean profesor=FichajeBD.getProfesor(idTarjeta);
        if(profesor==null){
            return null;
        }
        
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement insert1 = conexion.prepareStatement(
                    "INSERT INTO `colsan`.`fichajes` ( `currentTime`, `fecha`, `hora`, `idProfesor`, `terminal`,`entrada`) VALUES (?, ?, ?, ?,2,?)");
            //Long time=System.currentTimeMillis();
            profesor.setCurrentTimeMillis(time);
            insert1.setString(1, ""+time);
            insert1.setString(2, FechasUtils.fechaHoyParaMysql());
            insert1.setString(3, FechasUtils.horaAhora());
            insert1.setString(4, ""+profesor.getIdProfesor());
            insert1.setString(5, ""+profesor.isDentro());
            insert1.executeUpdate();

            return profesor; //Correcto

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
