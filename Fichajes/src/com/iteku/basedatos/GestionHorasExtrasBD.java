/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.basedatos;

import com.iteku.beans.HoraExtraBean;
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
 * @author Víctor
 */
public class GestionHorasExtrasBD {
    public static boolean ponHorasExtra(HoraExtraBean hora){
        boolean result=false;
        
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            PreparedStatement insert1 = conexion.prepareStatement(
                    "INSERT INTO `colsan`.`horasextra` (`fecha`, `horaIni`, `horaFin`, `motivo`) VALUES (?, ?, ?, ?);");

            insert1.setString(1, hora.getFechaMysql());
            insert1.setString(2, hora.getHoraIniMysql());
            insert1.setString(3, hora.getHoraFinMysql());
            insert1.setString(4, hora.getMotivo());
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
