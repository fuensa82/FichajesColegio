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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author vPalomo
 */
public class GestionInformesBD {
    public static boolean guardaInforme(ProfesorBean p, String obser, int segundosL, int segundosNL, int segundosC, int mes){
        boolean result=false;
        
        Connection conexion = null;
        try {
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
}
