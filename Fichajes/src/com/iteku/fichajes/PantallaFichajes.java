/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iteku.fichajes;

import com.iteku.basedatos.GestionFichajeBD;
import com.iteku.beans.ProfesorBean;
import com.iteku.utils.FechasUtils;
import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

/**
 *
 * @author vPalomo
 */
public class PantallaFichajes extends javax.swing.JFrame {

    public static String ERROR_PROFESOR_NULL="La tarjeta no está asiganada";
    private int tiempoError=10000;
    private int tiempoCorrecto=3000;
    /**
     * Creates new form PantallaFichajes
     */
    public PantallaFichajes() {
        this.setUndecorated(true);
        activaReloj();
        initComponents();
        this.getContentPane().setBackground(Color.BLACK);
        jLabel1.setText("Control horario Colegio San José");
        this.setExtendedState(MAXIMIZED_BOTH);
        //idTarjeta.requestFocus();
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
        relojPantalla = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        idTarjeta = new javax.swing.JTextField();
        nombreProfesor = new javax.swing.JLabel();
        estadoEntrada = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("jlabel");

        relojPantalla.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        relojPantalla.setForeground(new java.awt.Color(255, 255, 255));
        relojPantalla.setText(" ");

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        idTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTarjetaActionPerformed(evt);
            }
        });

        nombreProfesor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nombreProfesor.setForeground(new java.awt.Color(255, 255, 255));

        estadoEntrada.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        estadoEntrada.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombreProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estadoEntrada))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(relojPantalla, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(relojPantalla, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estadoEntrada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombreProfesor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(idTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void idTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTarjetaActionPerformed
        ProfesorBean profesor=GestionFichajeBD.putFichaje(idTarjeta.getText(),System.currentTimeMillis());
        if(profesor==null){
            nombreProfesor.setText(ERROR_PROFESOR_NULL);
            
            idTarjeta.setText("");
            estadoEntrada.setText("Error");
            estadoEntrada.setForeground(Color.red);
            borrarNombres(ERROR_PROFESOR_NULL,estadoEntrada.getText(),tiempoError);
            return;
        }
        nombreProfesor.setText(profesor.getNombre()+" "+profesor.getApellidos());
        if(!profesor.isDentro()){
            estadoEntrada.setText("Salida");
            GregorianCalendar c=new GregorianCalendar();
            c.setTimeInMillis(profesor.getCurrentTimeMillis());
            estadoEntrada.setText("Salida "+FechasUtils.getFechaString(c));
            estadoEntrada.setForeground(Color.RED);
        }else{
            GregorianCalendar c=new GregorianCalendar();
            c.setTimeInMillis(profesor.getCurrentTimeMillis());
            estadoEntrada.setText("Entrada "+FechasUtils.getFechaString(c));
            //estadoEntrada.setText("Entrada");
            estadoEntrada.setForeground(Color.GREEN);
        }
        System.out.println(idTarjeta.getText());        
        borrarNombres(profesor.getNombre()+" "+profesor.getApellidos(),estadoEntrada.getText(),tiempoCorrecto);
        idTarjeta.setText("");
    }//GEN-LAST:event_idTarjetaActionPerformed

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
            java.util.logging.Logger.getLogger(PantallaFichajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaFichajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaFichajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaFichajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.out.println("Creando");
                new PantallaFichajes().setVisible(true);
                System.out.println("Creado");
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel estadoEntrada;
    private javax.swing.JTextField idTarjeta;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel nombreProfesor;
    private javax.swing.JLabel relojPantalla;
    // End of variables declaration//GEN-END:variables

    private void activaReloj() {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        relojPantalla.setText(formateador.format(LocalDateTime.now()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
    }
    
    /**
     * Borra el texto del label donde aparece el nombre si coincide con el text que se pasa como
     * parametro. Lo borra pasado los milisegundos que pongamos en millis
     * @param text
     * @param millis Milisegundos que se espera antes de borrar el texto
     */
    private void borrarNombres(String text, String mensaje, int millis){
        //System.out.println("Nombre a comprobar: "+text);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                    try {
                        Thread.sleep(millis);
                        //System.out.println("Tiempo cumplido. Nombre del label: "+nombreProfesor.getText());
                        //System.out.println("                 Texto a borrar: "+text);
                        if(text.equals(nombreProfesor.getText()) && mensaje.equals(estadoEntrada.getText())){
                            nombreProfesor.setText("");
                            estadoEntrada.setText("");
                        }
                        //relojPantalla.setText(formateador.format(LocalDateTime.now()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        };
        Thread hilo2 = new Thread(runnable);
        hilo2.start();
        
    }
}