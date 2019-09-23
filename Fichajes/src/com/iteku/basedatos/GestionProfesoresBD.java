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
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author vPalomo
 */
public class GestionProfesoresBD {
    public static ArrayList<ProfesorBean> getListaProfesores(){
        ArrayList<ProfesorBean> result;
        result = new ArrayList();
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            ProfesorBean profesor;
            PreparedStatement consulta = conexion.prepareStatement(
                    "select idProfesor, nombre, apellidos, idTarjeta");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()){
                profesor=new ProfesorBean();
                profesor.setIdProfesor(resultado.getInt(1));
                profesor.setNombre(resultado.getString(2));
                profesor.setApellidos(resultado.getString(3));
                
                result.add(profesor);
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
    /**
     *  SELECT idFichaje, entrada 
        FROM fichajes 
        WHERE idProfesor=1
        ORDER BY idFichaje DESC LIMIT 1
     */
    public static boolean gestEstadoProfesor(String idProfesor){
        return true;
    }
}
