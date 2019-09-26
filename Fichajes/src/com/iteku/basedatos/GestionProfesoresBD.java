/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.basedatos;

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
public class GestionProfesoresBD {
    public static ArrayList<ProfesorBean> getListaProfesores(){
        ArrayList<ProfesorBean> result;
        result = new ArrayList();
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            ProfesorBean profesor;
            PreparedStatement consulta = conexion.prepareStatement(
                    "select idProfesor, nombre, apellidos, idTarjeta from profesores");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()){
                profesor=new ProfesorBean();
                profesor.setIdProfesor(resultado.getInt(1));
                profesor.setNombre(resultado.getString(2));
                profesor.setApellidos(resultado.getString(3));
                profesor.setIdTarjeta(resultado.getInt(4));
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
    /**
     * 
     * @param idProfesor
     * @return 
     */
    public static boolean isDentro(int idProfesor){
        //System.out.println("Buscando profesor "+idProfesor);
        return isDentro(idProfesor, FechasUtils.fechaHoyParaMysql());
    }
        
    public static boolean isDentro(int idProfesor, String fecha){
        //System.out.println("Buscando en fecha "+fecha);
        boolean result=false;
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement(
                    "select idFichaje, dentro "
                    + "from fichajes "
                    + "where fecha=? and idProfesor=? "
                    + "ORDER BY idFichaje DESC LIMIT 1");
            consulta.setString(1, fecha);
            consulta.setString(2, ""+idProfesor);
            ResultSet resultado = consulta.executeQuery();
            if(!resultado.next()){
                System.out.println("Id tarjeta no econtrado. "+idProfesor);
                return false;
            }
            String auxDentro=resultado.getString(2);
            System.out.println("resultado base de datos: "+auxDentro.trim().length());
            System.out.println("Trinario: "+auxDentro.trim().equalsIgnoreCase("false"));
            //boolean 
            if(auxDentro.equalsIgnoreCase("true")){
                
            }
            
            return resultado.getString(2).trim().equalsIgnoreCase("true")?true:false;
            

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
            try {
                //System.out.println("Saliendo de la base de datos");
                conexion.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    public static boolean gestEstadoProfesor(String idProfesor){
        return true;
    }
}
