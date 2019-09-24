/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.basedatos;

//import com.mysql.jdbc.jdbc2.optional.MysqlDataSourc;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;

public class ConectorBD {

    private static String baseDatos="colsan";
    private static String usuario="root";
    private static String contrasenia="Hijo34Luna";

    
    public static Connection getConnection() throws NamingException, SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(ConectorBD.usuario);
        dataSource.setPassword(ConectorBD.contrasenia);
        dataSource.setDatabaseName(ConectorBD.baseDatos);
        //dataSource.setServerName("localhost");
        dataSource.setServerName("192.168.1.42");

        Connection conexion = dataSource.getConnection();
        return conexion;
    }
}
