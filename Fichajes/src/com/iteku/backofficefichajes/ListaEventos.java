/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.backofficefichajes;

import com.iteku.basedatos.GestionEventosBD;
import com.iteku.beans.EventoBean;
import com.iteku.beans.ProfesorBean;
import java.awt.Frame;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vPalomo
 */
public class ListaEventos extends javax.swing.JPanel {

    
    EventoBean evento;
    private boolean seleccionFila=false;
    private EventoBean eventoSel;
    private ArrayList<EventoBean> listaEventos;
    private ArrayList<ProfesorBean> listaProfesores;
    /**
     * Creates new form ListaEventos
     */
    public ListaEventos() {
        initComponents();
        cargarListaEventos(0);
        ponerListenerTablaEventos();
    }
    
    private void ponerListenerTablaEventos() {
        jTableEventos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evento) {
                ListSelectionModel lsm = (ListSelectionModel) evento.getSource();
                int indice = lsm.getMinSelectionIndex();
                if (indice != -1) {
                    seleccionFila = true;
                    System.out.println("Evento: " + indice);
                    System.out.println("Id evento: " + listaEventos.get(indice).getIdEvento());
                    eventoSel = listaEventos.get(indice);
                    cargarListaProfesores(eventoSel);
                }
            }
        });
    }
    /**
     * el mes debe ser un valor entre 1 y 12, tambien vale el 0 para cargar todos los meses.
     * @param mes 
     */
    private void cargarListaEventos(int mes) {
        listaEventos = GestionEventosBD.getListaEventos(mes);
        DefaultTableModel datosTabla = (DefaultTableModel) jTableEventos.getModel();
        for (int i = datosTabla.getRowCount(); i > 0; i--) {
            datosTabla.removeRow(i - 1);
        }
        for (EventoBean evento : listaEventos){
            datosTabla.addRow(new Object[]{
                ""+evento.getIdEvento(),
                evento.getDescripcion(),
                evento.getFecha(),
                evento.getHoraIni(),
                evento.getHoraFin(),
                evento.isDiaCompleto()
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
        jTableEventos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProfesores = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        jTableEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id Evento", "Descripcion", "Fecha", "Hora Ini", "Hora Fin", "Dia Completo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
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
        jTableEventos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableEventosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableEventos);
        if (jTableEventos.getColumnModel().getColumnCount() > 0) {
            jTableEventos.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTableEventos.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTableEventos.getColumnModel().getColumn(2).setPreferredWidth(60);
            jTableEventos.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTableEventos.getColumnModel().getColumn(4).setPreferredWidth(50);
            jTableEventos.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        jTableProfesores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Nombre C.", "Nombre", "Apellidos", "Id Prof"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableProfesores);
        if (jTableProfesores.getColumnModel().getColumnCount() > 0) {
            jTableProfesores.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTableProfesores.getColumnModel().getColumn(1).setPreferredWidth(500);
            jTableProfesores.getColumnModel().getColumn(2).setPreferredWidth(500);
            jTableProfesores.getColumnModel().getColumn(3).setPreferredWidth(1000);
            jTableProfesores.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        jButton1.setText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Borrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Profesores afectados por el evento seleccionado en la tabla de eventos");

        jLabel2.setText("Tabla de eventos");

        jButton4.setText("Guardar cambios");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Salir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton6.setText("Seleccionar todo");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton7.setText("Borrar todo");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTableEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEventosMouseClicked
        if(evt.getClickCount()==2){
            cargarListaProfesores(eventoSel);
        }
        System.out.println("Raton: "+evt.getClickCount());
    }//GEN-LAST:event_jTableEventosMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        guardarProfesoresEnEvento();
        cargarListaProfesores(eventoSel);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        DefaultTableModel datosTabla=(DefaultTableModel) jTableProfesores.getModel();
        for(int i=0;i<datosTabla.getRowCount();i++){
            datosTabla.setValueAt(true, i, 0);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        DefaultTableModel datosTabla=(DefaultTableModel) jTableProfesores.getModel();
        for(int i=0;i<datosTabla.getRowCount();i++){
            datosTabla.setValueAt(false, i, 0);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Window w = SwingUtilities.getWindowAncestor(this);
        w.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JDialog frame = new JDialog((Frame)null, "Añadir evento", true);
        frame.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        frame.getContentPane().add(new MttoEvento(null));
        frame.pack();
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
        frame.setVisible(false);
        cargarListaEventos(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (!seleccionFila) {
            JOptionPane.showMessageDialog(null, "Primero debe seleccionar una fila con datos");
            return;
        }
        JDialog frame = new JDialog((Frame)null, "Modificar evento", true);
        frame.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        frame.getContentPane().add(new MttoEvento(eventoSel));
        frame.pack();
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
        frame.setVisible(false);
        cargarListaEventos(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        int i=JOptionPane.showConfirmDialog(null,"Está seguro de querer borrar el evento", "Eliminación",JOptionPane.YES_NO_OPTION);
        if(i!=1){
            if(!GestionEventosBD.borrarEvento(eventoSel)){
                JOptionPane.showMessageDialog(null, "Error eliminando el evento.");
            }
            cargarListaEventos(i);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void guardarProfesoresEnEvento(){
        DefaultTableModel datosTabla=(DefaultTableModel) jTableProfesores.getModel();
        ArrayList<ProfesorBean> listaProfesores2=new ArrayList<>();
        for (int i=0;i<datosTabla.getRowCount();i++){
            ProfesorBean profesor=new ProfesorBean();
            profesor.setIdProfesor((int)datosTabla.getValueAt(i,4));
            profesor.setEnEvento((boolean)datosTabla.getValueAt(i,0));
            listaProfesores2.add(profesor);
        }
        GestionEventosBD.guardaProfesoresEvento(listaProfesores2,eventoSel);
        System.out.println(listaProfesores);
        System.out.println(""+datosTabla.getValueAt(0,0));
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableEventos;
    private javax.swing.JTable jTableProfesores;
    // End of variables declaration//GEN-END:variables

    private void cargarListaProfesores(EventoBean eventoSel) {
        listaProfesores = eventoSel.getListaProfesores();
        DefaultTableModel datosTabla = (DefaultTableModel) jTableProfesores.getModel();
        for (int i = datosTabla.getRowCount(); i > 0; i--) {
            datosTabla.removeRow(i - 1);
        }
        for (ProfesorBean profesor : listaProfesores){
            datosTabla.addRow(new Object[]{
                profesor.isEnEvento(),
                profesor.getNombreCorto(),
                profesor.getNombre(),
                profesor.getApellidos(),
                profesor.getIdProfesor()
            });
        }
    }
}
