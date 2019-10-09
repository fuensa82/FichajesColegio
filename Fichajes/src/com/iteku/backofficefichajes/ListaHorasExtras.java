/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.backofficefichajes;

import com.iteku.basedatos.GestionHorasExtrasBD;
import com.iteku.beans.HoraExtraBean;
import com.iteku.beans.ProfesorBean;
import com.iteku.utils.FechasUtils;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vPalomo
 */
public class ListaHorasExtras extends javax.swing.JPanel {

    /**
     * Creates new form ListaHorasExtras
     */
    ProfesorBean profesor;
    public ListaHorasExtras(ProfesorBean p) {
        profesor=p;
        initComponents();
        cargarListaHoras();
        
    }

    private void cargarListaHoras() {
        ArrayList<HoraExtraBean> listaHoras;
        listaHoras = GestionHorasExtrasBD.getHorasExtraProfesor(profesor);
        DefaultTableModel datosTabla = (DefaultTableModel) jTableHoras.getModel();
        for (int i = datosTabla.getRowCount(); i > 0; i--) {
            //filasTabla=0;
            datosTabla.removeRow(i - 1);

        }
        //datosTabla.addRow(new String[]{"","","","",""});
        for (int i = 0; i < listaHoras.size(); i++) {
            
            datosTabla.addRow(new String[]{
                ""+listaHoras.get(i).getIdHoraExtra(),
                listaHoras.get(i).getFecha(),
                listaHoras.get(i).getHoraIni(),
                listaHoras.get(i).getHoraFin(),
                "" + listaHoras.get(i).getMotivo(),
                "" + listaHoras.get(i).getFechaAlta()
            });
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHoras = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();

        jTableHoras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id Hora Extra", "Fecha", "Hora Inicio", "Hora Fin", "Motivo", "Fecha de alta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableHoras.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(jTableHoras);
        jTableHoras.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTableHoras.getColumnModel().getColumnCount() > 0) {
            jTableHoras.getColumnModel().getColumn(0).setPreferredWidth(15);
            jTableHoras.getColumnModel().getColumn(1).setPreferredWidth(40);
            jTableHoras.getColumnModel().getColumn(2).setPreferredWidth(35);
            jTableHoras.getColumnModel().getColumn(3).setPreferredWidth(35);
            jTableHoras.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTableHoras.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        jComboBox1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox1PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox1PropertyChange
        try {
            String mes=FechasUtils.getNumMes(jComboBox1.getItemAt(jComboBox1.getSelectedIndex()));
        } catch (Exception ex) {
            Logger.getLogger(ListaHorasExtras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBox1PropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableHoras;
    // End of variables declaration//GEN-END:variables
}
