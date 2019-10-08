/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.backofficefichajes;

import com.iteku.basedatos.GestionProfesoresBD;
import com.iteku.beans.FichaBean;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Víctor
 */
public class HorarioProfesor extends javax.swing.JPanel {

    /**
     * Creates new form HorarioProfesor
     */
    public HorarioProfesor(String idProfesor) {
        initComponents();
        cargarDatos(idProfesor);
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
        tFichas = new javax.swing.JTable();

        tFichas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "IdFicha", "Hora Ini", "Hora Fin", "Dia", "Tipo Hora"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tFichas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tFichas;
    // End of variables declaration//GEN-END:variables

    private void cargarDatos(String idProfesor) {
        ArrayList<FichaBean> listaFichas = GestionProfesoresBD.getListaFichasCurso(idProfesor);
        DefaultTableModel datosTabla = (DefaultTableModel) tFichas.getModel();
        for (int i = datosTabla.getRowCount(); i > 0; i--) {
            //filasTabla=0;
            datosTabla.removeRow(i - 1);

        }
        //datosTabla.addRow(new String[]{"","","","",""});
        for (int i = 0; i < listaFichas.size(); i++) {
            
            datosTabla.addRow(new String[]{
                "" + listaFichas.get(i).getIdFicha(),
                listaFichas.get(i).getHoraIni(),
                listaFichas.get(i).getHoraFin(),
                ""+listaFichas.get(i).getDia(),
                listaFichas.get(i).getTipoHora()
            });
        }
    }
}