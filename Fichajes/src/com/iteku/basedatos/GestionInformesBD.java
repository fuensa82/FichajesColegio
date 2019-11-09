/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.basedatos;

import com.iteku.beans.InformeBean;
import com.iteku.beans.ProfesorBean;
import com.iteku.utils.FechasUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author vPalomo
 */
public class GestionInformesBD {
    public static int deleteInforme(ProfesorBean profesor, int mes){
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            PreparedStatement consulta;
            consulta = conexion.prepareStatement(
                "DELETE FROM informes WHERE idProfesor=? and mes=?");
            consulta.setString(1, ""+profesor.getIdProfesor());
            consulta.setString(2, ""+mes);
            int resultInt=consulta.executeUpdate();
            return resultInt;
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
        return 0;
    }
    
    public static boolean guardaInforme(ProfesorBean p, String obser, int segundosL, int segundosNL, int segundosC, int mes){
        boolean result=false;
        
        Connection conexion = null;
        try {
            GestionInformesBD.deleteInforme(p, mes);
            conexion = ConectorBD.getConnection();
            String sql="INSERT INTO `colsan`.`informes` (`mes`, `idProfesor`, `observaciones`, `curso`, `horasL`, `horasNL`, `horasC`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement insert1 = conexion.prepareStatement(sql);
            insert1.setString(1, ""+mes);
            insert1.setString(2, ""+p.getIdProfesor());
            insert1.setString(3, obser);
            insert1.setString(4, FechasUtils.getCursoActual());
            insert1.setString(5, ""+segundosL);
            insert1.setString(6, ""+segundosNL);
            insert1.setString(7, ""+segundosC);
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
    
    public static InformeBean getTotalInformes(ProfesorBean profesor, int mes){
        Connection conexion = null;
        
        try {
            conexion=ConectorBD.getConnection();
            InformeBean informe=new InformeBean();
            PreparedStatement consulta;
            
            consulta = conexion.prepareStatement("SELECT idInforme, fechaGeneracion, mes, idProfesor, observaciones, curso, horasL, horasNL, horasC FROM informes where idProfesor=? AND mes=? ORDER BY fechaGeneracion desc LIMIT 1");
            consulta.setString(1, ""+profesor.getIdProfesor());
            consulta.setString(2, ""+mes);
            
            
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                informe.setIdInforme(resultado.getInt(1));
                informe.setFechaGeneracion(FechasUtils.fechaYHora(resultado.getString(2)));
                informe.setMes(resultado.getInt(3));
                informe.setIdProfesor(resultado.getInt(4));
                informe.setObservaciones(resultado.getString(5));
                informe.setCurso(resultado.getString(6));
                informe.setHorasL(resultado.getInt(7));
                informe.setHorasNL(resultado.getInt(8));
                informe.setHorasC(resultado.getInt(9));
            return informe;
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
        
        return null;
    }
    
    
}
