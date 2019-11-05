/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.basedatos;

import static com.iteku.basedatos.GestionEventosBD.deleteProfesoresDelEvento;
import com.iteku.beans.DetalleInformeBean;
import com.iteku.beans.EventoBean;
import com.iteku.beans.ProfesorBean;
import com.iteku.recuento.UtilsContabilizar;
import com.iteku.utils.FechasUtils;
import com.iteku.utils.Utils;
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
public class GestionDetallesInformesBD {
    public static ArrayList<DetalleInformeBean> getListaDetallesInformes(ProfesorBean profesor, int mes){
        ArrayList<DetalleInformeBean> listaDetalle=new ArrayList<DetalleInformeBean>();
        Connection conexion = null;
        try {
            conexion=ConectorBD.getConnection();
            DetalleInformeBean detalle;
            PreparedStatement consulta;
            
            consulta = conexion.prepareStatement("SELECT idDetalleInforme, idProfesor, totalHoras, horaIni, horaFin, fecha, tipoHora FROM detallesinformes WHERE idProfesor=? and MONTH(fecha)=?  ");
            consulta.setString(1, ""+profesor.getIdProfesor());
            consulta.setString(2, ""+mes);
            
            
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()){
                    detalle=new DetalleInformeBean();
                    detalle.setIdDetalleInforme(resultado.getInt(1));
                    detalle.setIdProfesor(resultado.getInt(2));
                    detalle.setTotalHoras(resultado.getInt(3));
                    detalle.setHoraIni(resultado.getString(4));
                    detalle.setHoraFin(resultado.getString(5));
                    detalle.setFecha(resultado.getString(6));
                    detalle.setTipoHora(resultado.getString(7));
                    listaDetalle.add(detalle);
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
        
        return listaDetalle;
    }
    /**
     * 
     * @param listaProfesores
     * @param evento
     * @return 
     */
    public static boolean guardaDetalleInforme(DetalleInformeBean detalle) {
        boolean result=false;
        
        Connection conexion = null;
        try {
            conexion = ConectorBD.getConnection();
            boolean ejecutar=false;

            PreparedStatement insert1 = conexion.prepareStatement(
                    "INSERT INTO `colsan`.`detallesinformes` (`idProfesor`, `totalHoras`, `horaIni`, `horaFin`,`fecha`,`tipoHora`, `totalHorasString`) VALUES (?, ?,?, ?, ?, ?, ?);"
            );
            insert1.setString(1, ""+detalle.getIdProfesor());
            insert1.setString(2, ""+detalle.getTotalHoras());
            insert1.setString(3, detalle.getHoraIni());
            insert1.setString(4, detalle.getHoraFin());
            insert1.setString(5, FechasUtils.fechaParaMysql(detalle.getFecha()));
            insert1.setString(6, detalle.getTipoHora());
            insert1.setString(7, Utils.convierteSegundos(detalle.getTotalHoras()));
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
