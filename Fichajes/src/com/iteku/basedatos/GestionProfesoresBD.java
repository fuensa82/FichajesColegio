/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.basedatos;

import com.iteku.beans.FichaBean;
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
                    "select idProfesor, nombre, apellidos, idTarjeta, nombreCorto from profesores");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()){
                profesor=new ProfesorBean();
                profesor.setIdProfesor(resultado.getInt(1));
                profesor.setNombre(resultado.getString(2));
                profesor.setApellidos(resultado.getString(3));
                profesor.setIdTarjeta(resultado.getInt(4));
                profesor.setNombreCorto(resultado.getString(5));
                result.add(profesor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            
        }finally{
            try {
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
        return isDentro(idProfesor, FechasUtils.fechaHoyParaMysql());
    }
        
    public static boolean isDentro(int idProfesor, String fecha){
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
                return false;
            }
            
            return resultado.getString(2).trim().equalsIgnoreCase("true")?true:false;
            

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    public static long getUltimaCurrentTime(int idProfesor, String fecha){
        long currentTime=0;
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement(
                    "select idFichaje, currentTime "
                    + "from fichajes "
                    + "where fecha=? and idProfesor=? "
                    + "ORDER BY idFichaje DESC LIMIT 1");
            consulta.setString(1, fecha);
            consulta.setString(2, ""+idProfesor);
            ResultSet resultado = consulta.executeQuery();
            if(!resultado.next()){
                System.out.println("No hay currentTime, el profesor no está dentro. "+idProfesor);
                return 0;
            }
            currentTime=resultado.getLong(2);
            System.out.println("resultado base de datos: "+currentTime);
            

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
        return currentTime;
    }
    
//    public static boolean gestEstadoProfesor(String idProfesor){
//        return true;
//    }
    /**
     * Actualiza la base de datos con el datos que se pasan en el Bean, se actualizan los datos del id del 
     * profesor que se pasa como parámetro
     * @param profesor Debe contener todos los datos (id, nombre, apellidos y tarjeta)
     * @return 
     */
    public static boolean actualizaProfesor(ProfesorBean profesor){
        if(profesor==null){
            return false;
        }
        
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            //UPDATE `colsan`.`profesores` SET `nombre`='Mercedesa', `apellidos`='Palomo Silvaaa', `idTarjeta`='8652711' WHERE  `idProfesor`=3;
            PreparedStatement update = conexion.prepareStatement(
                    "UPDATE profesores SET nombre=?, apellidos=?, idTarjeta=?, nombreCorto=? WHERE idProfesor=?");

            update.setString(1, profesor.getNombre());
            update.setString(2, profesor.getApellidos());
            update.setString(3, ""+profesor.getIdTarjeta());
            update.setString(4, profesor.getNombreCorto());
            update.setString(5, ""+profesor.getIdProfesor());
            update.executeUpdate();

            return true; //Correcto

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static boolean crearProfesor(ProfesorBean profesor, ProfesorBean profesorHorarios){
        
        
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            //UPDATE `colsan`.`profesores` SET `nombre`='Mercedesa', `apellidos`='Palomo Silvaaa', `idTarjeta`='8652711' WHERE  `idProfesor`=3;
            PreparedStatement update = conexion.prepareStatement(
                    "UPDATE profesores SET nombre=?, apellidos=?, idTarjeta=?, nombreCorto=? WHERE idProfesor=?");

            update.setString(1, profesor.getNombre());
            update.setString(2, profesor.getApellidos());
            update.setString(3, ""+profesor.getIdTarjeta());
            update.setString(4, profesor.getNombreCorto());
            update.setString(5, ""+profesor.getIdProfesor());
            update.executeUpdate();

            return true; //Correcto

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    /**
     * Dado un id de profesor genera el objeto ProfesorBean con todos sus datos
     * @param idProfesor
     * @return 
     */
    public static ProfesorBean getProfesor(String idProfesor) {
        ProfesorBean profesor = new ProfesorBean();
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement(
                    "select idProfesor, nombre, apellidos, idTarjeta, nombreCorto "
                    + "from profesores "
                    + "where idProfesor=?");
            consulta.setString(1, idProfesor);
            ResultSet resultado = consulta.executeQuery();
            if(!resultado.next()){
                System.out.println("Id Profesor no econtrado. "+idProfesor);
                return null;
            }
            profesor.setIdProfesor(resultado.getInt(1));
            profesor.setNombre(resultado.getString(2));
            profesor.setApellidos(resultado.getString(3));
            profesor.setIdTarjeta(resultado.getInt(4));
            profesor.setNombreCorto(resultado.getString(5));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
            }
        }
        return profesor;
    }
/**
 * Genera la fichas de horario de todos los prefesores para el curso en curso.
 * @param profesor Con que contenga el idProfesor es suficiente.
 * @return 
 */
    public static ArrayList<FichaBean> getListaFichasCurso(ProfesorBean profesor, String tipoHora) {
        ArrayList<FichaBean> result;
        result = new ArrayList<>();
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            FichaBean ficha;
            PreparedStatement consulta = conexion.prepareStatement(
                    "SELECT idFicha, horaIni, horaFin, idProfesor, dia, tipoHora, curso FROM horarios WHERE idProfesor=? and curso=? and tipoHora=? order by dia, horaIni");
            consulta.setString(1, ""+profesor.getIdProfesor());
            consulta.setString(2, FechasUtils.getCursoActual());
            consulta.setString(3, tipoHora);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()){
                ficha=new FichaBean();
                ficha.setIdFicha(resultado.getInt(1));
                ficha.setHoraIni(resultado.getString(2));
                ficha.setHoraFin(resultado.getString(3));
                ficha.setIdProfesor(resultado.getInt(4));
                ficha.setDia(resultado.getString(5).charAt(0));
                ficha.setTipoHora(resultado.getString(6));
                ficha.setCurso(resultado.getString(7));
                result.add(ficha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
            }
        }
        return result;
    }

    public static String getFechaUltInforme(ProfesorBean profesor) {
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement consulta = conexion.prepareStatement(
                    "SELECT idInforme, fechaGeneracion, mes, idProfesor, observaciones, curso, horasL, horasNL, horasC FROM informes WHERE idProfesor=? AND curso=? order by fechaGeneracion DESC LIMIT 1");
            consulta.setString(1, ""+profesor.getIdProfesor());
            consulta.setString(2, FechasUtils.getCursoActual());
            ResultSet resultado = consulta.executeQuery();
            if(!resultado.next()){
                return null;
            }
            return resultado.getString(2);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
            }
        }
        return "";
    }
}
