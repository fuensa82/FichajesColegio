/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basedatos;

import com.utils.FechasUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;



/**
 *
 * @author vPalomo
 */
public class FichajeBD {
    
    public static String putFichaje(String idTarjeta){
        
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            PreparedStatement insert1 = conexion.prepareStatement(
                "INSERT INTO `colsan`.`registros` ( `currentTime`, `fecha`, `hora`, `idPersonal`, `terminal`   ) VALUES (?, ?, ?, ?,2)");            
            insert1.setString(1, "2");
            insert1.setString(2, FechasUtils.fechaParaMysql(FechasUtils.fechaActualString()));
            insert1.setString(3, "01");
            insert1.setString(4, idTarjeta);
            
            insert1.executeUpdate();
                        
            return ""; //Correcto
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return "Error";
    }
}
