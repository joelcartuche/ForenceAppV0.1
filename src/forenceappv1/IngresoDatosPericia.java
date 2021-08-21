/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forenceappv1;

import freemarker.template.TemplateException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JFrame;
import javax.swing.JTextField;
import jpaController.PeritoJpaController;
import jpaController.PersonaJpaController;
import jpaController.ProcesoJpaController;
import jpaController.SolicitanteJpaController;
import metodos.utilidades;
import modelos.Perito;
import modelos.Persona;
import modelos.Proceso;
import modelos.Solicitante;

/**
 *
 * @author joelc
 */
public class IngresoDatosPericia extends javax.swing.JFrame {
    public static String rutaArchivo;
    /**
     * Creates new form IngresoDatosPericia
     */
    public IngresoDatosPericia() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jFValorPericia = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jfFechaSolicitud = new javax.swing.JFormattedTextField();
        jFFechaLimite = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Fecha solicitud:");

        jLabel2.setText("Fecha limite:");

        jLabel3.setText("Valor pericia:");

        jFValorPericia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jButton1.setText("Generar informe");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Pericia");

        jButton2.setText("<< Atrás");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });

        jfFechaSolicitud.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        jFFechaLimite.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1))
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel4))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jfFechaSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jFValorPericia, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))
                        .addComponent(jFFechaLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jfFechaSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jFFechaLimite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jFValorPericia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        
        //Creacion de datos del solicitante
        IngresoDatosPerito  dataPerito = new IngresoDatosPerito();
        PersonaJpaController personaPerJpa = new PersonaJpaController();
        //creamos la persona solicitante
        Persona personaSol = new Persona(dataPerito.getNombrePerito()+" "+dataPerito.getApellidoPerito(), 
                dataPerito.getDireccionPerito(), dataPerito.getTelefonoFijoPerito(), dataPerito.getTelefonoCelPerito(),
                dataPerito.getCedulaPerito(), dataPerito.getCorreoPerito());
        personaPerJpa.create(personaSol);
        
        PeritoJpaController peritoJpa = new PeritoJpaController();
        Perito perito = new Perito(dataPerito.getNumCasoIntPerito(), personaSol);
        peritoJpa.create(perito);
        //Fin de creacion de datos del solicitante
        
        
        //Inicio de creacion de datos del perito
        IngresoDatosSolicitante dataSol = new IngresoDatosSolicitante();
        PersonaJpaController personaSolicitanteJpa = new PersonaJpaController();
        //creamos la persona del perito

        Persona personaPerito = new Persona(dataSol.getNombreSol()+" "+ dataSol.getApellidoSol()
                , dataSol.getDireccionSol()
                , dataSol.getTelefonoFijoSol()
                , dataSol.getTelefonoCelSol()
                , dataSol.getCedulaSol()
                , dataSol.getCorreoSol());

        personaSolicitanteJpa.create(personaPerito);
        //creamos perito
        utilidades util = new utilidades();
        String rutaArchivoPericia = util.guardarArchivo(dataSol.getRutaPericia());
        SolicitanteJpaController solicitanteJpa = new SolicitanteJpaController();
        Solicitante solicitante = new Solicitante(rutaArchivoPericia,
                 dataSol.getNumProcesoSol(),
                 dataSol.getUnidadPerteneceSol(),
                 personaPerito);
        
        solicitanteJpa.create(solicitante);
        //fin de creacion de perito
        
        //creacion de la pericia
        ProcesoJpaController procesoJpa = new ProcesoJpaController();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaSolicitud = null;
        Date fechaLimite = null;
        
        try {
            fechaSolicitud = formato.parse(jfFechaSolicitud.getText());
            fechaLimite = formato.parse(jFFechaLimite.getText());
        } catch (ParseException ex) {
            Logger.getLogger(IngresoDatosPericia.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        Proceso pericia = new Proceso(fechaSolicitud
                ,fechaLimite
                ,Double.parseDouble(jFValorPericia.getText()),perito,solicitante);
        procesoJpa.create(pericia);
        
        // fin de la creacion de la pericia
        
        try {
            util.manipularWord(rutaArchivoPericia, personaPerito, personaSol, perito, solicitante,pericia);
        } catch (IOException ex) {
            Logger.getLogger(IngresoDatosPericia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TemplateException ex) {
            Logger.getLogger(IngresoDatosPericia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(IngresoDatosPericia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        JFrame solicitanteFrame = new IngresoDatosSolicitante();
        solicitanteFrame.setLocationRelativeTo(null);
        solicitanteFrame.setResizable(false);
        solicitanteFrame.setVisible(true);
        this.setVisible(false);// TODO add your handling code here:// TODO add your handling code here:
    }//GEN-LAST:event_jButton2MousePressed

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
            java.util.logging.Logger.getLogger(IngresoDatosPericia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IngresoDatosPericia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IngresoDatosPericia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IngresoDatosPericia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IngresoDatosPericia().setVisible(true);
            }
        });
    }

    public static String getRutaArchivo() {
        return rutaArchivo;
    }

    public static void setRutaArchivo(String rutaArchivo) {
        IngresoDatosPericia.rutaArchivo = rutaArchivo;
    }

   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFormattedTextField jFFechaLimite;
    private javax.swing.JFormattedTextField jFValorPericia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JFormattedTextField jfFechaSolicitud;
    // End of variables declaration//GEN-END:variables
}
