/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forenceappv1;

import javax.swing.JFrame;
import metodos.conexionMysql;
import modelos.Perito;
import modelos.Persona;
import modelos.Solicitante;
import metodos.tablasMysql;

/**
 *
 * @author joelc
 */
public class IngresoDatosPerito extends javax.swing.JFrame {

    /**
     * Creates new form IngresoDatosPerito
     */
    tablasMysql t = new tablasMysql();
    public static String nombrePerito;//
    public static String apellidoPerito;//
    public static String direccionPerito;//
    public static String telefonoFijoPerito;//
    public static String telefonoCelPerito;//
    public static String correoPerito;//
    public static String cedulaPerito;//
    public static String numCasoIntPerito;
        
    public IngresoDatosPerito() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        t.crearBaseDatos();
    }                                                          

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelDataSolicitante = new javax.swing.JLabel();
        txtNombreSolicitante = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtApellidosSolicitante = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDireccionSolicitante = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTelefFijoSolicitante = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTelefCelularSolicitante = new javax.swing.JTextField();
        txtCorreoSolicitante = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCedulaSolitante = new javax.swing.JTextField();
        txtNumCasoInterno = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnSiguienteSolicitante = new javax.swing.JButton();
        btnCancelarSolicitante = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelDataSolicitante.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        labelDataSolicitante.setText("DATOS DEL PERITO");
        getContentPane().add(labelDataSolicitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        txtNombreSolicitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreSolicitanteActionPerformed(evt);
            }
        });
        getContentPane().add(txtNombreSolicitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 150, -1));

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setText("Nombres: ");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));
        getContentPane().add(txtApellidosSolicitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 150, -1));

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setText("Apellidos:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel10.setText("Direccion:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));
        getContentPane().add(txtDireccionSolicitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 150, -1));

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel11.setText("Teléfono Fijo:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));
        getContentPane().add(txtTelefFijoSolicitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 150, -1));

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel12.setText("Teléfono celular");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));
        getContentPane().add(txtTelefCelularSolicitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 150, -1));
        getContentPane().add(txtCorreoSolicitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 150, -1));

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel13.setText("Correo:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        jLabel14.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel14.setText("Cédula o pasaporte:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));
        getContentPane().add(txtCedulaSolitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 150, -1));
        getContentPane().add(txtNumCasoInterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 150, -1));

        jLabel18.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel18.setText("Número de caso interno:");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 170, 30));

        btnSiguienteSolicitante.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        btnSiguienteSolicitante.setText("SIGUIENTE >>");
        btnSiguienteSolicitante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSiguienteSolicitanteMousePressed(evt);
            }
        });
        btnSiguienteSolicitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteSolicitanteActionPerformed(evt);
            }
        });
        getContentPane().add(btnSiguienteSolicitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 390, -1, -1));

        btnCancelarSolicitante.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        btnCancelarSolicitante.setText("CANCELAR");
        btnCancelarSolicitante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCancelarSolicitanteMousePressed(evt);
            }
        });
        btnCancelarSolicitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarSolicitanteActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelarSolicitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreSolicitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreSolicitanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreSolicitanteActionPerformed

    private void btnSiguienteSolicitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteSolicitanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSiguienteSolicitanteActionPerformed

    private void btnCancelarSolicitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarSolicitanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarSolicitanteActionPerformed

    private void btnSiguienteSolicitanteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSiguienteSolicitanteMousePressed
        nombrePerito = txtNombreSolicitante.getText();//
        apellidoPerito = txtApellidosSolicitante.getText();//
        direccionPerito = txtDireccionSolicitante.getText();//
        telefonoFijoPerito = txtTelefFijoSolicitante.getText();//
        telefonoCelPerito = txtTelefCelularSolicitante.getText();//
        correoPerito = txtCorreoSolicitante.getText();//
        cedulaPerito = txtCedulaSolitante.getText();//
        numCasoIntPerito = txtNumCasoInterno.getText();

        
        JFrame peritoFrame = new IngresoDatosSolicitante();
        peritoFrame.setLocationRelativeTo(null);
        peritoFrame.setResizable(false);
        peritoFrame.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_btnSiguienteSolicitanteMousePressed

    private void btnCancelarSolicitanteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarSolicitanteMousePressed

        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarSolicitanteMousePressed

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
            java.util.logging.Logger.getLogger(IngresoDatosPerito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IngresoDatosPerito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IngresoDatosPerito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IngresoDatosPerito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IngresoDatosPerito().setVisible(true);
            }
        });
    }

    public static String getNombrePerito() {
        return nombrePerito;
    }

    public static String getApellidoPerito() {
        return apellidoPerito;
    }

    public static String getDireccionPerito() {
        return direccionPerito;
    }

    public static String getTelefonoFijoPerito() {
        return telefonoFijoPerito;
    }

    public static String getTelefonoCelPerito() {
        return telefonoCelPerito;
    }

    public static String getCorreoPerito() {
        return correoPerito;
    }

    public static String getCedulaPerito() {
        return cedulaPerito;
    }

    public static String getNumCasoIntPerito() {
        return numCasoIntPerito;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarSolicitante;
    private javax.swing.JButton btnSiguienteSolicitante;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel labelDataSolicitante;
    private javax.swing.JTextField txtApellidosSolicitante;
    private javax.swing.JTextField txtCedulaSolitante;
    private javax.swing.JTextField txtCorreoSolicitante;
    private javax.swing.JTextField txtDireccionSolicitante;
    private javax.swing.JTextField txtNombreSolicitante;
    private javax.swing.JTextField txtNumCasoInterno;
    private javax.swing.JTextField txtTelefCelularSolicitante;
    private javax.swing.JTextField txtTelefFijoSolicitante;
    // End of variables declaration//GEN-END:variables
}
