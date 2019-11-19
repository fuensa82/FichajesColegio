/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.basedatos;

import com.iteku.beans.EventoBean;
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
     * @param isCompleto
     * @param profesor
     * @param mes
     * @return 
     */
    public static ArrayList<EventoBean> getListaEventosProfesor(boolean isCompleto, ProfesorBean profesor, int mes){
        ArrayList<EventoBean> listaResult = new ArrayList<>();
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            EventoBean evento;
            PreparedStatement consulta;
            
            consulta = conexion.prepareStatement("SELECT eventos.idEvento, fecha, horaIni, horaFin, diaCompleto, descripcion FROM eventoprofesor, eventos" +
                                                " WHERE eventoprofesor.idProfesor=? AND eventoprofesor.idEvento=eventos.idEvento" +
                                                " AND curso=? AND MONTH(fecha)=?");
            consulta.setString(2, FechasUtils.getCursoActual());
            consulta.setString(1, ""+profesor.getIdProfesor());
            consulta.setString(3, ""+mes);
            
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()){
                if("true".equals(resultado.getString(5).trim())==isCompleto){
                    evento=new EventoBean();
                    evento.setIdEvento(resultado.getInt(1));
                    evento.setFecha(FechasUtils.fecha(resultado.getString(2), "/"));
                    evento.setHoraIni(resultado.getString(3));
                    evento.setHoraFin(resultado.getString(4));
                    evento.setDiaCompleto("true".equals(resultado.getString(5).trim())?true:false);
                    evento.setDescripcion(resultado.getString(6));
                    listaResult.add(evento);
                }
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
                consulta = conexion.prepareStatement("SELECT idEvento, fecha, horaIni, horaFin, diaCompleto, descripcion FROM eventos WHERE curso=? ORDER by idEvento DESC ");
                consulta.setString(1, FechasUtils.getCursoActual());
            }else{
                consulta = conexion.prepareStatement("SELECT idEvento, fecha, horaIni, horaFin, diaCompleto, descripcion FROM eventos WHERE curso=? and MONTH(fecha)=? ORDER by idEvento DESC ");
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
    
    public static ArrayList<ProfesorBean> getListaProfesores(EventoBean evento){
        ArrayList<ProfesorBean> listaProfesoresEvento=getListaProfesoresEnEvento(evento, true);
        listaProfesoresEvento.addAll(getListaProfesoresEnEvento(evento, false));
        return listaProfesoresEvento;
    }
    /**
     *  SELECT profesores.idProfesor, profesores.nombre, profesores.nombreCorto FROM profesores, eventoprofesor 
     *  WHERE eventoprofesor.idEvento=? AND eventoprofesor.idProfesor=profesores.idProfesor
     */
    public static ArrayList<ProfesorBean> getListaProfesoresEnEvento(EventoBean evento, boolean enEvento){
        ArrayList<ProfesorBean> result;
        result = new ArrayList();
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            ProfesorBean profesor;
            PreparedStatement consulta;
            if(enEvento){
                consulta = conexion.prepareStatement(
                    "SELECT profesores.idProfesor, profesores.nombre, profesores.apellidos, profesores.nombreCorto FROM profesores, eventoprofesor " +
                    "WHERE eventoprofesor.idEvento=? AND eventoprofesor.idProfesor=profesores.idProfesor");
                
            }else{
                consulta=conexion.prepareStatement("SELECT profesores.idProfesor, profesores.nombre, profesores.apellidos, profesores.nombreCorto FROM profesores "+
                    "WHERE idProfesor not IN" +
                        " (SELECT profesores.idProfesor FROM profesores, eventoprofesor " +
                        " WHERE eventoprofesor.idEvento=? AND eventoprofesor.idProfesor=profesores.idProfesor)");
            }
            consulta.setString(1, ""+evento.getIdEvento());
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()){
                profesor=new ProfesorBean();
                profesor.setIdProfesor(resultado.getInt(1));
                profesor.setNombre(resultado.getString(2));
                profesor.setApellidos(resultado.getString(3));
                profesor.setNombreCorto(resultado.getString(4));
                profesor.setEnEvento(enEvento);
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

    public static boolean guardaProfesoresEvento(ArrayList<ProfesorBean> listaProfesores, EventoBean evento) {
        deleteProfesoresDelEvento(evento);
        boolean result=false;
        
        Connection conexion = null;
        try {
            if(listaProfesores.size()>0){
                conexion = ConectorBD.getConnection();
                String sql="INSERT INTO `eventoprofesor` (`idEvento`, `idProfesor`) VALUES";
                boolean ejecutar=false;
                for (ProfesorBean profesor : listaProfesores) {
                    if(profesor.isEnEvento()){
                        sql+="("+evento.getIdEvento()+","+profesor.getIdProfesor()+"),";
                        ejecutar=true;
                    }
                }
                PreparedStatement insert1 = conexion.prepareStatement(sql.substring(0,sql.length()-1));
                System.out.println("sql: "+insert1);
                if(ejecutar){
                    insert1.executeUpdate();
                }
            }
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
    
    public static void deleteProfesoresDelEvento(EventoBean evento){
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            PreparedStatement consulta;
            consulta = conexion.prepareStatement(
                "DELETE FROM eventoprofesor WHERE idEvento=?");
            consulta.setString(1, ""+evento.getIdEvento());
            int resultInt=consulta.executeUpdate();
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
    }

    public static boolean putEvento(EventoBean evento) {
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement insert1 = conexion.prepareStatement(
                    "INSERT INTO `colsan`.`eventos` (`fecha`, `horaIni`, `horaFin`, `diaCompleto`, `descripcion`, `curso`) VALUES (?, ?, ?, ?, ?, ?)");
            //Long time=System.currentTimeMillis();
            //profesor.setCurrentTimeMillis(time);
            insert1.setString(1, FechasUtils.fechaParaMysql(evento.getFecha()));
            insert1.setString(2, evento.isDiaCompleto()?null:evento.getHoraIni());
            insert1.setString(3, evento.isDiaCompleto()?null:evento.getHoraFin());
            insert1.setString(4, ""+evento.isDiaCompleto());
            insert1.setString(5, evento.getDescripcion());
            insert1.setString(6, evento.getCurso());
            insert1.executeUpdate();

            return true; //Correcto

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean modificarEvento(EventoBean evento) {
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement insert1 = conexion.prepareStatement(
                    "UPDATE `colsan`.`eventos` SET `fecha`=?, `horaIni`=?, `horaFin`=?, `diaCompleto`=?, `descripcion`=?, `curso`=? WHERE  `idEvento`=?;");
            insert1.setString(1, FechasUtils.fechaParaMysql(evento.getFecha()));
            insert1.setString(2, evento.isDiaCompleto()?null:evento.getHoraIni());
            insert1.setString(3, evento.isDiaCompleto()?null:evento.getHoraFin());
            insert1.setString(4, ""+evento.isDiaCompleto());
            insert1.setString(5, evento.getDescripcion());
            insert1.setString(6, evento.getCurso());
            insert1.setString(7, ""+evento.getIdEvento());
            insert1.executeUpdate();

            return true; //Correcto

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public static boolean borrarEvento(EventoBean evento) {
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement insert1 = conexion.prepareStatement(
                    "DELETE from `colsan`.`eventos` where idEvento=?");
            insert1.setString(1, ""+evento.getIdEvento());
            insert1.executeUpdate();
            insert1 = conexion.prepareStatement(
                    "DELETE from `colsan`.`eventoprofesor` where idEvento=?");
            insert1.setString(1, ""+evento.getIdEvento());
            insert1.executeUpdate();

            return true; //Correcto

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
