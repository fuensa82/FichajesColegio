/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.backofficefichajes;

import com.iteku.basedatos.GestionFichajeBD;
import com.iteku.basedatos.GestionProfesoresBD;
import com.iteku.beans.ProfesorBean;
import com.iteku.recuento.Contabilizar;
import com.iteku.utils.FechasUtils;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vPalomo
 */
public class BackOfficeFichajes extends javax.swing.JFrame {

    private ArrayList<ProfesorBean> listaProfesores;
    private boolean seleccionFila = false;
    ProfesorBean profesorSeleccionado;

    
    /**
     * Creates new form BackOfficeFichajes
     */
    public BackOfficeFichajes() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BackOfficeFichajes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(BackOfficeFichajes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BackOfficeFichajes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(BackOfficeFichajes.class.getName()).log(Level.SEVERE, null, ex);
        }
        listaProfesores = new ArrayList<>();
        
        initComponents();
        iniciarMisComponentes();
        ponListenerTabla(tProfesores);
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextIdProfesor = new javax.swing.JTextField();
        jTextNombre = new javax.swing.JTextField();
        jTextApellidos = new javax.swing.JTextField();
        jTextIdTarjeta = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextNombreCorto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabelCurso = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tProfesores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Profesor", "Nombre Corto", "Nombre", "Apellidos", "Tarjeta", "Estado", "Hora Estado", "Alerta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tProfesores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tProfesores.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tProfesores);
        tProfesores.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tProfesores.getColumnModel().getColumnCount() > 0) {
            tProfesores.getColumnModel().getColumn(0).setPreferredWidth(1);
            tProfesores.getColumnModel().getColumn(1).setPreferredWidth(40);
            tProfesores.getColumnModel().getColumn(2).setPreferredWidth(60);
            tProfesores.getColumnModel().getColumn(3).setPreferredWidth(120);
            tProfesores.getColumnModel().getColumn(4).setPreferredWidth(60);
            tProfesores.getColumnModel().getColumn(5).setPreferredWidth(30);
            tProfesores.getColumnModel().getColumn(6).setPreferredWidth(30);
            tProfesores.getColumnModel().getColumn(7).setPreferredWidth(20);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos profesor seleccionado"));

        jLabel1.setText("idProfesor:");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Apellidos:");

        jLabel4.setText("Tarjeta:");

        jTextIdProfesor.setText("jTextField1");
        jTextIdProfesor.setEnabled(false);
        jTextIdProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextIdProfesorActionPerformed(evt);
            }
        });

        jTextNombre.setText("jTextField2");

        jTextApellidos.setText("jTextField3");

        jTextIdTarjeta.setText("jTextField4");

        jButton1.setText("Guardar cambio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Fichajes");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextNombreCorto.setText("jTextField1");

        jLabel5.setText("Nombre c.:");

        jButton4.setText("Horario");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("Horas añadidas");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextIdProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextIdTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextNombreCorto, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextIdProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextNombreCorto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jTextApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jTextIdTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        jButton3.setText("Actualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setText("Curso:");

        jLabelCurso.setText("jLabel7");

        jMenu1.setText("Archivo");

        jMenuItem3.setText("Crear curso nuevo");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Eventos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem1.setText("Informes");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Cargar horarios");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelCurso)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabelCurso))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JDialog frame = new JDialog(this, "Cargar fichajes", true);
        frame.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        //frame.setIconImage(new ImageIcon(getClass().getResource(icono)).getImage());
        frame.getContentPane().add(new ListaFichajesProfesor(profesorSeleccionado,this));
        frame.pack();
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
        //this.cambiarSesion(sesionSelecionada);
        cargarListaProfesores();
        frame.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Contabilizar contar=new Contabilizar();
        contar.contabilizarConMesYProfesor(profesorSeleccionado, 10);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!seleccionFila) {
            JOptionPane.showMessageDialog(null, "Primero debe seleccionar una fila con datos");
            return;
        }
        ProfesorBean p = new ProfesorBean(jTextIdProfesor.getText(), jTextNombre.getText(), jTextApellidos.getText(), jTextIdTarjeta.getText(), jTextNombreCorto.getText());
        if (GestionProfesoresBD.actualizaProfesor(p)) {
            cargarListaProfesores();
            jTextApellidos.setText("");
            jTextIdProfesor.setText("");
            jTextNombre.setText("");
            jTextIdTarjeta.setText("");
            jTextNombreCorto.setText("");
            seleccionFila = false;
            profesorSeleccionado=null;
        } else {
            JOptionPane.showMessageDialog(null, "La tarjeta o llave ya está asignada");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cargarListaProfesores();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextIdProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextIdProfesorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextIdProfesorActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        //opcion de menu "Cargar horarios"
        JDialog frame = new JDialog(this, "Cargar horarios", true);
        frame.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        //frame.setIconImage(new ImageIcon(getClass().getResource(icono)).getImage());
        frame.getContentPane().add(new VentanaCargarHorarios());
        frame.pack();
        frame.setVisible(true);
        //this.cambiarSesion(sesionSelecionada);
        cargarListaProfesores();
        frame.setVisible(false);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (!seleccionFila) {
            JOptionPane.showMessageDialog(null, "Primero debe seleccionar una fila con datos");
            return;
        }
        JDialog frame = new JDialog(this, "Horario profesor "+profesorSeleccionado.getNombreCorto(), true);
        frame.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        //frame.setIconImage(new ImageIcon(getClass().getResource(icono)).getImage());
        frame.getContentPane().add(new HorarioProfesor(jTextIdProfesor.getText()));
        frame.pack();
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
        //this.cambiarSesion(sesionSelecionada);
        //cargarListaProfesores();
        frame.setVisible(false);
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
if (!seleccionFila) {
            JOptionPane.showMessageDialog(null, "Primero debe seleccionar una fila con datos");
            return;
        }
        JDialog frame = new JDialog(this, "Lista de horas de "+profesorSeleccionado.getNombreCorto(), true);
        frame.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        //frame.setIconImage(new ImageIcon(getClass().getResource(icono)).getImage());
        frame.getContentPane().add(new ListaHorasExtras(profesorSeleccionado));
        frame.pack();
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
        //this.cambiarSesion(sesionSelecionada);
        //cargarListaProfesores();
        frame.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JDialog frame = new JDialog(this, "Curso nuevo", true);
        frame.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        frame.getContentPane().add(new CrearCursoNuevo());
        frame.pack();
        frame.setVisible(true);
        frame.setVisible(false); 
        iniciarMisComponentes();
        cargarListaProfesores();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        JDialog frame = new JDialog(this, "Gestion de eventos", true);
        frame.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        frame.getContentPane().add(new ListaEventos());
        frame.pack();
        frame.setVisible(true);
        iniciarMisComponentes();
        frame.setVisible(false);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

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
        listaProfesores = GestionProfesoresBD.getListaProfesores();
        DefaultTableModel datosTabla = (DefaultTableModel) tProfesores.getModel();
        for (int i = datosTabla.getRowCount(); i > 0; i--) {
            //filasTabla=0;
            datosTabla.removeRow(i - 1);

        }
        //datosTabla.addRow(new String[]{"","","","",""});
        for (int i = 0; i < listaProfesores.size(); i++) {
            if (listaProfesores.get(i).isDentro()) {
                listaProfesores.get(i).cargaCurrentTime();
            }
            
            datosTabla.addRow(new String[]{
                "" + listaProfesores.get(i).getIdProfesor(),
                listaProfesores.get(i).getNombreCorto(),
                listaProfesores.get(i).getNombre(),
                listaProfesores.get(i).getApellidos(),
                "" + listaProfesores.get(i).getIdTarjeta(),
                "" + (listaProfesores.get(i).isDentro() ? "Dentro" : "Ausente"),
                "" + listaProfesores.get(i).getHoraCurrentTime(),
                GestionFichajeBD.compruebaFichajesProfesor(listaProfesores.get(i), 10)?"":"Contienen errores"
            });
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelCurso;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextApellidos;
    private javax.swing.JTextField jTextIdProfesor;
    private javax.swing.JTextField jTextIdTarjeta;
    private javax.swing.JTextField jTextNombre;
    private javax.swing.JTextField jTextNombreCorto;
    private javax.swing.JTable tProfesores;
    // End of variables declaration//GEN-END:variables

    private void ponListenerTabla(JTable tProfesores) {
        tProfesores.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evento) {
                ListSelectionModel lsm = (ListSelectionModel) evento.getSource();
                int indice = lsm.getMinSelectionIndex();
                if (indice != -1) {
                    seleccionFila = true;
                    System.out.println("Indice: " + indice);
                    System.out.println("Profe: " + listaProfesores.get(indice).getNombre());
                    jTextIdProfesor.setText("" + listaProfesores.get(indice).getIdProfesor());
                    jTextNombre.setText("" + listaProfesores.get(indice).getNombre());
                    jTextApellidos.setText("" + listaProfesores.get(indice).getApellidos());
                    jTextIdTarjeta.setText("" + listaProfesores.get(indice).getIdTarjeta());
                    jTextNombreCorto.setText("" + listaProfesores.get(indice).getNombreCorto());
                    profesorSeleccionado = listaProfesores.get(indice);
                }
            }
        });
    }

    private void iniciarMisComponentes() {
        jLabelCurso.setText("".equals(FechasUtils.getCursoActual())?"Debe dar de alta el curso para poder empezar":FechasUtils.getCursoActual());
        jTextApellidos.setText("");
        jTextIdProfesor.setText("");
        jTextNombre.setText("");
        jTextIdTarjeta.setText("");
        jTextNombreCorto.setText("");
    }
}
