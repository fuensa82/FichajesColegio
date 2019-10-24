/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.backofficefichajes;

import com.iteku.basedatos.GestionFichajeBD;
import com.iteku.beans.FichajeBean;
import com.iteku.beans.ProfesorBean;
import com.iteku.utils.FechasUtils;
import java.awt.Window;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author vPalomo
 */
public class MttoFichajes extends javax.swing.JPanel {

    private boolean nuevo;
    private FichajeBean fichaje;
    private ProfesorBean profesor;

    /**
     * Creates new form MttoFichajes
     */
    public MttoFichajes(FichajeBean fichaje, ProfesorBean profesor) {
        this.profesor = profesor;
        this.fichaje = fichaje;
        if (fichaje == null) {
            nuevo = true;
        } else {
            nuevo = false;
        }
        initComponents();
        cargarDatos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextFecha = new javax.swing.JFormattedTextField();
        jComboBoxTipoFichaje = new javax.swing.JComboBox<>();
        jFormattedHora = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaMotivo = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setText("Profesor:");

        jLabel2.setText("Fecha:");

        jLabel3.setText("Tipo fichaje:");

        jLabel4.setText("Hora:");

        jFormattedTextFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        jComboBoxTipoFichaje.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entrada", "Salida" }));

        jFormattedHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm:ss"))));

        jLabel5.setText("dd/mm/aaaa");

        jLabel6.setText("hh:mm:ss");

        jTextAreaMotivo.setColumns(20);
        jTextAreaMotivo.setRows(5);
        jScrollPane1.setViewportView(jTextAreaMotivo);

        jLabel7.setText("Motivo:");

        jLabelNombre.setText("jLabel8");

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jFormattedTextFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5))
                            .addComponent(jComboBoxTipoFichaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jFormattedHora, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addComponent(jLabelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(104, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jFormattedTextFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxTipoFichaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jFormattedHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(7, 7, 7)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Window w = SwingUtilities.getWindowAncestor(this);
                w.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if("".equalsIgnoreCase(jFormattedTextFecha.getText())){
            JOptionPane.showMessageDialog(null, "Debe escribir una fecha en formato dd/mm/aaaa");
            return;
        }else if("".equals(jFormattedHora.getText())){
            JOptionPane.showMessageDialog(null, "Debe escribir una hora de inicio en formato hh:mm");
            return;
        }else if("".equals(jTextAreaMotivo.getText())){
            JOptionPane.showMessageDialog(null, "Debe escribir un motivo para el cambio");
            return;
        }else{
            if(nuevo){
                fichaje=new FichajeBean();
            }
   
            
            fichaje.setCurso(FechasUtils.getCursoActual());
            fichaje.setFecha(jFormattedTextFecha.getText());
            fichaje.setHora(jFormattedHora.getText());
            fichaje.setEsEntrada(jComboBoxTipoFichaje.getSelectedIndex()==0?true:false);
            fichaje.setIdProfesor(profesor.getIdProfesor());
            fichaje.setMotivo(jTextAreaMotivo.getText());
            fichaje.setTerminal(0);
            boolean result;
            if(nuevo){
                result=GestionFichajeBD.putFichaje(fichaje);
            }else{
                result=GestionFichajeBD.modificarFichaje(fichaje);
            }
            if(result){
                JOptionPane.showMessageDialog(null, "Guardado correctamente");
                Window w = SwingUtilities.getWindowAncestor(this);
                w.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "Error guardando las horas.");
                return;
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBoxTipoFichaje;
    private javax.swing.JFormattedTextField jFormattedHora;
    private javax.swing.JFormattedTextField jFormattedTextFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaMotivo;
    // End of variables declaration//GEN-END:variables

    private void cargarDatos() {
        jLabelNombre.setText(profesor.getNombre());
        if (!nuevo) {
            jFormattedHora.setText(fichaje.getHora());
            jFormattedTextFecha.setText(fichaje.getFecha());
            if (fichaje.isEsEntrada()) {
                jComboBoxTipoFichaje.setSelectedIndex(0);
            } else {
                jComboBoxTipoFichaje.setSelectedIndex(1);
            }
        }
    }
}
