/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor.
 */
package com.iteku.fichajes;

import com.iteku.backofficefichajes.Config;
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
public class PantallaFichajesConLogo extends javax.swing.JFrame {

    public static String ERROR_PROFESOR_NULL="Tarjeta no asignada";
    private int tiempoError=10000;
    private int tiempoCorrecto=3000;
    /**
     * Creates new form PantallaFichajes
     */
    public PantallaFichajesConLogo() {
        this.setUndecorated(true);
        activaReloj();
        initComponents();
        sombraTexto.setVisible(false);
        nombreProfesor.setText("");
        estadoEntrada.setText("");
        this.getContentPane().setBackground(Color.BLACK);
        jLabel1.setText(Config.getNombreApp()+" - "+Config.getNombreCol());
        this.setExtendedState(MAXIMIZED_BOTH);
        //idTarjeta.requestFocus();
        idTarjeta.requestFocusInWindow();
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
        sombraReloj = new javax.swing.JLabel();
        sombraTexto = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Control");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 391, 38);

        relojPantalla.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        relojPantalla.setForeground(new java.awt.Color(255, 255, 255));
        relojPantalla.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        relojPantalla.setText(" ");
        getContentPane().add(relojPantalla);
        relojPantalla.setBounds(290, 60, 150, 38);

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(440, 280, 25, 22);

        idTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTarjetaActionPerformed(evt);
            }
        });
        getContentPane().add(idTarjeta);
        idTarjeta.setBounds(10, 154, 385, 0);

        nombreProfesor.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        nombreProfesor.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(nombreProfesor);
        nombreProfesor.setBounds(15, 165, 420, 44);

        estadoEntrada.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        estadoEntrada.setText("jLabel2");
        getContentPane().add(estadoEntrada);
        estadoEntrada.setBounds(15, 130, 430, 34);

        sombraReloj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/iteku/recursos/sombra2.png"))); // NOI18N
        sombraReloj.setText("jLabel6");
        getContentPane().add(sombraReloj);
        sombraReloj.setBounds(310, 60, 140, 40);

        sombraTexto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/iteku/recursos/sombra2.png"))); // NOI18N
        sombraTexto.setText("jLabel4");
        getContentPane().add(sombraTexto);
        sombraTexto.setBounds(10, 130, 440, 80);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("v 0.3");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(390, 290, 40, 13);

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/iteku/recursos/logo.jpg"))); // NOI18N
        fondo.setText("jLabel3");
        fondo.setPreferredSize(new java.awt.Dimension(480, 320));
        getContentPane().add(fondo);
        fondo.setBounds(0, 0, 480, 320);

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
            java.util.logging.Logger.getLogger(PantallaFichajesConLogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaFichajesConLogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaFichajesConLogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaFichajesConLogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.out.println("Creando");
                new PantallaFichajesConLogo().setVisible(true);
                System.out.println("Creado");
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel estadoEntrada;
    private javax.swing.JLabel fondo;
    private javax.swing.JTextField idTarjeta;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel nombreProfesor;
    private javax.swing.JLabel relojPantalla;
    private javax.swing.JLabel sombraReloj;
    private javax.swing.JLabel sombraTexto;
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
        sombraTexto.setVisible(true);
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
                            sombraTexto.setVisible(false);
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
