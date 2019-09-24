/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.backofficefichajes;

import com.iteku.basedatos.GestionProfesoresBD;
import com.iteku.beans.ProfesorBean;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vPalomo
 */
public class BackOfficeFichajes extends javax.swing.JFrame {

    /**
     * Creates new form BackOfficeFichajes
     */
    public BackOfficeFichajes() {
        initComponents();
        cargarListaProfesores();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tProfesores = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tProfesores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Profesor", "Nombre", "Apellidos", "Tarjeta", "Estado", "Hora Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
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
        tProfesores.setColumnSelectionAllowed(true);
        tProfesores.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tProfesores);
        tProfesores.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tProfesores.getColumnModel().getColumnCount() > 0) {
            tProfesores.getColumnModel().getColumn(0).setPreferredWidth(30);
            tProfesores.getColumnModel().getColumn(1).setPreferredWidth(40);
            tProfesores.getColumnModel().getColumn(2).setPreferredWidth(90);
            tProfesores.getColumnModel().getColumn(3).setPreferredWidth(40);
            tProfesores.getColumnModel().getColumn(4).setPreferredWidth(30);
            tProfesores.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BackOfficeFichajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BackOfficeFichajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BackOfficeFichajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BackOfficeFichajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BackOfficeFichajes().setVisible(true);
            }

        });
    }

    private void cargarListaProfesores() {
        ArrayList<ProfesorBean> listaProfesores=GestionProfesoresBD.getListaProfesores();
        DefaultTableModel datosTabla=(DefaultTableModel) tProfesores.getModel();
        for (int i = datosTabla.getRowCount(); i >0 ; i--) {
            //filasTabla=0;
            datosTabla.removeRow(i-1);
            
        }
        //datosTabla.addRow(new String[]{"","","","",""});
        for (int i=0;i<listaProfesores.size();i++){
            datosTabla.addRow(new String[]{
                ""+listaProfesores.get(i).getIdProfesor(),
                listaProfesores.get(i).getNombre(),
                listaProfesores.get(i).getApellidos(),
                ""+listaProfesores.get(i).getIdTarjeta(),
                ""+(listaProfesores.get(i).isDentro()?"Dentro":"")
            });
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tProfesores;
    // End of variables declaration//GEN-END:variables
}
