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
import java.awt.Frame;
import java.awt.Window;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private boolean seleccionFila=false;
    private HoraExtraBean horaSel;
    private ArrayList<HoraExtraBean> listaHorasExtra;
    
    
    public ListaHorasExtras(ProfesorBean p) {
        profesor=p;
        initComponents();
        comboValorDefault();
        cargarListaHoras();
        ponerListenerTabla(jTableHoras);
        
    }

    private void cargarListaHoras() {
        String mes=FechasUtils.dameMesFechaActual();
        cargarListaHoras(Integer.parseInt(mes));
    }
        
    /**
     * 
     * @param mes si mes vale 0 carga todas las horas sin tener en cuenta el mes.
     */
    private void cargarListaHoras(int mes) {
        listaHorasExtra = GestionHorasExtrasBD.getHorasExtraProfesor(profesor,mes);

        DefaultTableModel datosTabla = (DefaultTableModel) jTableHoras.getModel();
        for (int i = datosTabla.getRowCount(); i > 0; i--) {
            //filasTabla=0;
            datosTabla.removeRow(i - 1);

        }
        //datosTabla.addRow(new String[]{"","","","",""});
        for (int i = 0; i < listaHorasExtra.size(); i++) {
            
            datosTabla.addRow(new String[]{
                ""+listaHorasExtra.get(i).getIdHoraExtra(),
                listaHorasExtra.get(i).getFecha(),
                listaHorasExtra.get(i).getHoraIni(),
                listaHorasExtra.get(i).getHoraFin(),
                "" + listaHorasExtra.get(i).getMotivo(),
                "" + listaHorasExtra.get(i).getFechaAlta(),
                listaHorasExtra.get(i).getTipoHora()
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jTableHoras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Fecha", "H. Inicio", "H. Fin", "Motivo", "Fecha de alta", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableHoras);
        jTableHoras.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTableHoras.getColumnModel().getColumnCount() > 0) {
            jTableHoras.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableHoras.getColumnModel().getColumn(1).setPreferredWidth(50);
            jTableHoras.getColumnModel().getColumn(2).setPreferredWidth(40);
            jTableHoras.getColumnModel().getColumn(3).setPreferredWidth(40);
            jTableHoras.getColumnModel().getColumn(4).setPreferredWidth(300);
            jTableHoras.getColumnModel().getColumn(5).setPreferredWidth(60);
            jTableHoras.getColumnModel().getColumn(6).setPreferredWidth(60);
        }

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox1PropertyChange(evt);
            }
        });

        jButton1.setText("Ver todos los meses");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Añadir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Eliminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox1PropertyChange
        try {
            String mes=FechasUtils.getNumMes(jComboBox1.getItemAt(jComboBox1.getSelectedIndex()));
        } catch (Exception ex) {
            Logger.getLogger(ListaHorasExtras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBox1PropertyChange

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        try {
            String mes=FechasUtils.getNumMes(jComboBox1.getItemAt(jComboBox1.getSelectedIndex()));
            cargarListaHoras(Integer.parseInt(mes));
        } catch (Exception ex) {
            Logger.getLogger(ListaHorasExtras.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cargarListaHoras(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Window w = SwingUtilities.getWindowAncestor(this);
        w.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JDialog frame = new JDialog((Frame)null, "Añadir horas extra a "+profesor.getNombreCorto(), true);
        frame.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        //frame.setIconImage(new ImageIcon(getClass().getResource(icono)).getImage());
        frame.getContentPane().add(new PonerHoraExtra(profesor));
        frame.pack();
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
        //this.cambiarSesion(sesionSelecionada);
        cargarListaHoras();
        frame.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int i=JOptionPane.showConfirmDialog(null,"Está seguro de querer borrar la hora", "Eliminación",JOptionPane.YES_NO_OPTION);
        if(i!=1){
            GestionHorasExtrasBD.deleteHorasExtraProfesor(horaSel);
            cargarListaHoras();
        }
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableHoras;
    // End of variables declaration//GEN-END:variables

    private void comboValorDefault() {
        String mes=FechasUtils.dameMesFechaActual();
        jComboBox1.setSelectedIndex(Integer.parseInt(mes)-1);
    }

    private void ponerListenerTabla(JTable jTable1) {
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evento) {
                ListSelectionModel lsm = (ListSelectionModel) evento.getSource();
                int indice = lsm.getMinSelectionIndex();
                if (indice != -1) {
                    seleccionFila = true;
                    System.out.println("Indice: " + indice);
                    System.out.println("Id hora extra: " + listaHorasExtra.get(indice).getIdHoraExtra());
                    horaSel = listaHorasExtra.get(indice);
                }
            }
        });
    }
}
