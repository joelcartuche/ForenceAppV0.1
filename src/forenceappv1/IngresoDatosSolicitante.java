/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forenceappv1;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import jpaController.PeritoJpaController;
import jpaController.PersonaJpaController;
import jpaController.SolicitanteJpaController;
import metodos.utilidades;

import modelos.Perito;
import modelos.Persona;
import modelos.Solicitante;

/**
 *
 * @author joelc
 */

public class IngresoDatosSolicitante extends javax.swing.JFrame {

    public static String nombreSol;//
    public static String apellidoSol;//
    public static String direccionSol;//
    public static String telefonoFijoSol;//
    public static String telefonoCelSol;//
    public static String correoSol;//
    public static String cedulaSol;//
    public static String numProcesoSol;
    public static String unidadPerteneceSol;
    public static String rutaPericia;

    /**
     * Creates new form IngresoDatos
     */
    public IngresoDatosSolicitante() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        labelDataPerito = new javax.swing.JLabel();
        txtNombreSol = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtApellidoSol = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDireccionSol = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTelefonoFijoSol = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTelefonoCelularSol = new javax.swing.JTextField();
        txtCorreoSol = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCedulaSol = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtNumProcesoSol = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtUnidadSol = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        fcObjetoSol = new javax.swing.JFileChooser();
        btnGenerarInfo = new javax.swing.JButton();
        btnAtrasPerito = new javax.swing.JButton();

        jLabel8.setText("jLabel8");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelDataPerito.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        labelDataPerito.setText("DATOS DEL SOLICITANTE");
        getContentPane().add(labelDataPerito, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));
        getContentPane().add(txtNombreSol, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 150, -1));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel1.setText("Nombres: ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));
        getContentPane().add(txtApellidoSol, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 150, -1));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setText("Apellidos:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setText("Direccion:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));
        getContentPane().add(txtDireccionSol, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 150, -1));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setText("Teléfono Fijo:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));
        getContentPane().add(txtTelefonoFijoSol, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 150, -1));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setText("Teléfono celular");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));
        getContentPane().add(txtTelefonoCelularSol, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 150, -1));
        getContentPane().add(txtCorreoSol, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 150, -1));

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setText("Correo:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setText("Cédula o pasaporte:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));
        getContentPane().add(txtCedulaSol, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 150, -1));

        jLabel15.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel15.setText("Objeto de la pericia:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, -1, -1));
        getContentPane().add(txtNumProcesoSol, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 150, -1));

        jLabel16.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel16.setText("Número de proceso:");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));
        getContentPane().add(txtUnidadSol, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 150, -1));

        jLabel17.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel17.setText("Unidad a la que pertenece:");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));
        getContentPane().add(fcObjetoSol, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 440, 290));

        btnGenerarInfo.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        btnGenerarInfo.setText("GENERAR INFORME");
        btnGenerarInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGenerarInfoMousePressed(evt);
            }
        });
        btnGenerarInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarInfoActionPerformed(evt);
            }
        });
        getContentPane().add(btnGenerarInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 420, -1, -1));

        btnAtrasPerito.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        btnAtrasPerito.setText("<< ATRÁS");
        btnAtrasPerito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAtrasPeritoMousePressed(evt);
            }
        });
        btnAtrasPerito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasPeritoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAtrasPerito, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarInfoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGenerarInfoActionPerformed

    private void btnAtrasPeritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasPeritoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtrasPeritoActionPerformed

    private void btnGenerarInfoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerarInfoMousePressed
        if(fcObjetoSol.getSelectedFile().getAbsolutePath()!=null){
            rutaPericia = fcObjetoSol.getSelectedFile().getAbsolutePath();
            nombreSol = txtNombreSol.getText();//
            apellidoSol = txtApellidoSol.getText();//
            direccionSol = txtDireccionSol.getText();//
            telefonoFijoSol = txtTelefonoFijoSol.getText();//
            telefonoCelSol = txtTelefonoCelularSol.getText();//
            correoSol = txtCorreoSol.getText();//
            cedulaSol = txtCedulaSol.getText();//
            numProcesoSol = txtNumProcesoSol.getText();
            unidadPerteneceSol = txtUnidadSol.getText();
            JFrame perciaFrame = new IngresoDatosPericia();

            perciaFrame.setLocationRelativeTo(null);
            perciaFrame.setResizable(false);
            perciaFrame.setVisible(true);
            this.setVisible(false);
                       
        }else{
            JOptionPane.showMessageDialog(null, "Asegurese de aver llenado todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGenerarInfoMousePressed

    private void btnAtrasPeritoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasPeritoMousePressed
        JFrame peritoFrame = new IngresoDatosPerito();
        peritoFrame.setLocationRelativeTo(null);
        peritoFrame.setResizable(false);
        peritoFrame.setVisible(true);
        this.setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_btnAtrasPeritoMousePressed

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
            java.util.logging.Logger.getLogger(IngresoDatosSolicitante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IngresoDatosSolicitante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IngresoDatosSolicitante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IngresoDatosSolicitante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IngresoDatosSolicitante().setVisible(true);
            }
        });
    }
    
    public JFileChooser getFcObjetoPericia() {
        return fcObjetoSol;
    }

    public String getRutaPericia() {
        return rutaPericia;
    }

    public static String getUnidadPerteneceSol() {
        return unidadPerteneceSol;
    }

    public static String getNombreSol() {
        return nombreSol;
    }

    public static String getApellidoSol() {
        return apellidoSol;
    }

    public static String getDireccionSol() {
        return direccionSol;
    }

    public static String getTelefonoFijoSol() {
        return telefonoFijoSol;
    }

    public static String getTelefonoCelSol() {
        return telefonoCelSol;
    }

    public static String getCorreoSol() {
        return correoSol;
    }

    public static String getCedulaSol() {
        return cedulaSol;
    }

    public static String getNumProcesoSol() {
        return numProcesoSol;
    }
    

    
    public File getArchivoPericia(){
        return fcObjetoSol.getSelectedFile();
    }

    public JTextField getTxtApellidoPerito() {
        return txtApellidoSol;
    }

    public JTextField getTxtCedulaPerito() {
        return txtCedulaSol;
    }

    public JTextField getTxtCorreoPerito() {
        return txtCorreoSol;
    }

    public JTextField getTxtDireccionPerito() {
        return txtDireccionSol;
    }

    public JTextField getTxtNombrePerito() {
        return txtNombreSol;
    }

    public JTextField getTxtTelefonoCelularPerito() {
        return txtTelefonoCelularSol;
    }

    public JTextField getTxtTelefonoFijoPerito() {
        return txtTelefonoFijoSol;
    }

    public JTextField getTxtUnidad() {
        return txtUnidadSol;
    }

    public JTextField getTxtNumProcesoPerito() {
        return txtNumProcesoSol;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtrasPerito;
    private javax.swing.JButton btnGenerarInfo;
    private javax.swing.JFileChooser fcObjetoSol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel labelDataPerito;
    private javax.swing.JTextField txtApellidoSol;
    private javax.swing.JTextField txtCedulaSol;
    private javax.swing.JTextField txtCorreoSol;
    private javax.swing.JTextField txtDireccionSol;
    private javax.swing.JTextField txtNombreSol;
    private javax.swing.JTextField txtNumProcesoSol;
    private javax.swing.JTextField txtTelefonoCelularSol;
    private javax.swing.JTextField txtTelefonoFijoSol;
    private javax.swing.JTextField txtUnidadSol;
    // End of variables declaration//GEN-END:variables
}
