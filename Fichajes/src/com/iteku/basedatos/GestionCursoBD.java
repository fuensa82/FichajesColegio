/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.basedatos;

import com.iteku.beans.ProfesorBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Víctor
 */
public class GestionCursoBD {
    public static boolean guardarCursoNuevo(String cursoNuevo){
        boolean result=false;
        
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement insert1 = conexion.prepareStatement(
                    "INSERT INTO `colsan`.`cursos` (`curso`) VALUES (?);");

            insert1.setString(1, cursoNuevo);
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
     * Busca en la base de datos el ultimo curso dado de alta. Formato 2019-2020
     *
     * @return
     */
    public static String getCursoActual() {
        String result = "";
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            ProfesorBean profesor;
            PreparedStatement consulta = conexion.prepareStatement(
                    "SELECT curso FROM cursos order by curso desc LIMIT 1");
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                result = resultado.getString(1);
            }
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
        return result;
    }
}
