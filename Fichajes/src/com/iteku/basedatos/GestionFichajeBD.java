/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.basedatos;

import com.iteku.backofficefichajes.Config;
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
     * Guarda el fichaje. Se pasa la tarjeta y el metodo busca el profesor correspondiente. Guarda si es entrada, salida, el
     * nombre de la máquina donde se ejecuta el fichaje
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
                    "INSERT INTO `colsan`.`fichajes` ( `currentTime`, `fecha`, `hora`, `idProfesor`, `terminal`,`dentro`,`curso`) VALUES (?, ?, ?, ?, ?, ?, ?)");
            //Long time=System.currentTimeMillis();
            profesor.setCurrentTimeMillis(time);
            insert1.setString(1, ""+time);
            insert1.setString(2, FechasUtils.fechaHoyParaMysql());
            insert1.setString(3, FechasUtils.horaAhora());
            insert1.setString(4, ""+profesor.getIdProfesor());
            insert1.setString(5, Config.getMaquina());
            insert1.setString(6, ""+!profesor.isDentro());
            insert1.setString(7, FechasUtils.getCursoActual());
            insert1.executeUpdate();
            return profesor; //Correcto
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean putFichaje(FichajeBean fichaje) {
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement insert1 = conexion.prepareStatement(
                    "INSERT INTO `colsan`.`fichajes` ( `currentTime`, `fecha`, `hora`, `idProfesor`, `terminal`,`dentro`,`curso`, `motivo`) VALUES (?, ?, ?, ?,?,?,?,?)");
            //Long time=System.currentTimeMillis();
            //profesor.setCurrentTimeMillis(time);
            insert1.setString(1, ""+fichaje.getCurrentTime());
            insert1.setString(2, FechasUtils.fechaParaMysql(fichaje.getFecha()));
            insert1.setString(3, fichaje.getHora());
            insert1.setString(4, ""+fichaje.getIdProfesor());
            insert1.setString(5, ""+fichaje.getTerminal());
            insert1.setString(6, ""+fichaje.isEsEntrada());
            insert1.setString(7, fichaje.getCurso());
            insert1.setString(8, fichaje.getMotivo());
            insert1.executeUpdate();
            return true; //Correcto
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    /**
     * Genera la lista de los fichajes de un mes del profesor.
     * @param profesor Con que contenga el idProfesor es suficiente
     * @param mes
     * @return 
     */
    public static ArrayList<FichajeBean> getListaFichajesProfesor(ProfesorBean profesor, int mes) {
        ArrayList<FichajeBean> listaResult;
        listaResult = new ArrayList<>();
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            FichajeBean fichaje;
            PreparedStatement consulta = conexion.prepareStatement(
                    "SELECT idFichaje, currentTime, fecha, hora, idProfesor, terminal, dentro, curso, motivo FROM fichajes WHERE idProfesor=? and curso=? and MONTH(fecha)=? order by fecha, hora");
            consulta.setString(1, ""+profesor.getIdProfesor());
            consulta.setString(2, FechasUtils.getCursoActual());
            consulta.setString(3, ""+mes);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()){
                fichaje=new FichajeBean();
                fichaje.setIdFichaje(resultado.getInt(1));
                fichaje.setCurrentTime(resultado.getLong(2));
                fichaje.setFecha(FechasUtils.fecha(resultado.getString(3), "/"));
                fichaje.setHora(resultado.getString(4));
                fichaje.setIdProfesor(resultado.getInt(5));
                fichaje.setTerminal(resultado.getString(6));
                fichaje.setEsEntrada("true".equals(resultado.getString(7).trim())?true:false);
                fichaje.setCurso(resultado.getString(8));
                fichaje.setMotivo(resultado.getString(9));
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

    public static boolean modificarFichaje(FichajeBean fichaje) {
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement insert1 = conexion.prepareStatement(
                    "UPDATE `colsan`.`fichajes` set currentTime=?, fecha=?, hora=?, terminal=?,dentro=?, motivo=? where idFichaje=?");
            //Long time=System.currentTimeMillis();
            //profesor.setCurrentTimeMillis(time);
            insert1.setString(1, ""+fichaje.getCurrentTime());
            insert1.setString(2, FechasUtils.fechaParaMysql(fichaje.getFecha()));
            insert1.setString(3, fichaje.getHora());
            insert1.setString(4, ""+fichaje.getTerminal());
            insert1.setString(5, ""+fichaje.isEsEntrada());
            insert1.setString(6, fichaje.getMotivo());
            insert1.setString(7, ""+fichaje.getIdFichaje());
            insert1.executeUpdate();
            return true; //Correcto
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static boolean borrarFichaje(FichajeBean fichaje) {
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement insert1 = conexion.prepareStatement(
                    "DELETE from `colsan`.`fichajes` where idFichaje=?");
            insert1.setString(1, ""+fichaje.getIdFichaje());
            insert1.executeUpdate();
            return true; //Correcto
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    /**
     * 
     * @param p
     * @param mes
     * @return 
     */
    public static boolean compruebaFichajesProfesor(ProfesorBean p, int mes){
        boolean result=true;
        ArrayList<FichajeBean> lista=getListaFichajesProfesor(p, mes);
        String fechaAnterior="";
        boolean isDentro=true;
        int contFichaje=0;
        for(int i=0;i<lista.size() && result;i++){
            if(!lista.get(i).getFecha().equals(fechaAnterior)){
                isDentro=true;
                if(contFichaje%2!=0){
                    if(!fechaAnterior.equals(FechasUtils.fechaActualString("/"))){
                        result=false;
                    }
                }
                contFichaje=0;
            }
            if(lista.get(i).isEsEntrada()!=isDentro){
                if(!fechaAnterior.equals(FechasUtils.fechaActualString("/"))){
                    result=false;
                }
            }else{
                isDentro=!isDentro;
            }
            fechaAnterior=lista.get(i).getFecha();
            contFichaje++;
        }
        return result;
    }
}
