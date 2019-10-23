/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.backofficefichajes;

import com.iteku.basedatos.GestionEventosBD;
import com.iteku.basedatos.GestionHorasExtrasBD;
import com.iteku.beans.EventoBean;
import com.iteku.beans.HoraExtraBean;
import com.iteku.beans.ProfesorBean;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEventos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProfesores = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButtonCargarProfesores = new javax.swing.JButton();

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

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jButtonCargarProfesores, org.jdesktop.beansbinding.ELProperty.create("${action.enabled}"), jTableEventos, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "Nombre C.", "Nombre", "Apellidos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        }

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        jButton3.setText("jButton3");

        jButtonCargarProfesores.setText("jButton4");
        jButtonCargarProfesores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargarProfesoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonCargarProfesores, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(340, 340, 340)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonCargarProfesores, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCargarProfesoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargarProfesoresActionPerformed
        cargarListaProfesores(eventoSel);
        
    }//GEN-LAST:event_jButtonCargarProfesoresActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonCargarProfesores;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableEventos;
    private javax.swing.JTable jTableProfesores;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private void cargarListaProfesores(EventoBean eventoSel) {
        listaProfesores = eventoSel.getListaProfesores();
        DefaultTableModel datosTabla = (DefaultTableModel) jTableProfesores.getModel();
        for (int i = datosTabla.getRowCount(); i > 0; i--) {
            datosTabla.removeRow(i - 1);
        }
        for (ProfesorBean profesor : listaProfesores){
            datosTabla.addRow(new Object[]{
                true,
                profesor.getNombreCorto(),
                profesor.getNombre(),
                profesor.getApellidos()
            });
        }
    }
}