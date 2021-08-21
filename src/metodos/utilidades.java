/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.AbstractMap;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.swing.JFileChooser;
import jpaController.PersonaJpaController;
import modelos.Perito;
import modelos.Persona;
import modelos.Proceso;
import modelos.Solicitante;

/**
 *
 * @author joelc
 */
public class utilidades {

    public utilidades() {
    }
    
    private String rutaProject = System.getProperty("user.dir");
    
    public String guardarArchivo(String rutaArchivo) {
        File directorio = new File(rutaProject+"/peritosInformaticos/pericias");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        File archivo = new File(rutaArchivo);
        System.out.println("#####################################");
        System.out.println(archivo.getName());
        System.out.println("#####################################");
        Date fechaActual = new Date();
        String nombreArchivo = fechaActual.getDay()+"_"+fechaActual.getMonth()+"_"
                +fechaActual.getYear()+"_"+fechaActual.getHours()
                +"_"+fechaActual.getMinutes()+"_"+fechaActual.getSeconds()+"_"+
                archivo.getName() ;
        
        
        copiarArchivoBase(rutaArchivo, directorio.getAbsolutePath()+"/"+nombreArchivo);
        String ruta = directorio.getAbsolutePath()+"/"+nombreArchivo;
       
        return ruta;    
    }
    
    public void manipularWord(String rutaArchivoEml,Persona personaPerito,Persona personaSol,Perito perito, Solicitante sol,Proceso pericia) throws IOException, TemplateException, MessagingException {
        File directorio = new File(rutaProject+"/peritosInformaticos/plantilla");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("directorio plantilla creado");
            } else {
                System.out.println("Error al crear directorio plantilla");
            }
        }
        File directorioBase = new File(directorio.getAbsolutePath()+"/base");
        if (!directorioBase.exists()) {
            if (directorioBase.mkdirs()) {
                System.out.println("Directorio base creados");
                copiarArchivoBase( rutaProject+"/base.ftl",directorioBase.getAbsolutePath()+"/base.ftl");
            } else {
                System.out.println("Error al crear directorio base");
            }
        }
        
        System.out.println("data Personas\n"+ personaSol.toString() +"\n"+personaPerito.toString());

        MimeMessage archivoMl = leerMl(rutaArchivoEml);
        Map<String,String> datosIngresar = new HashMap<String, String>();
        //datosPerito [#if persona1?has_content] ${persona1}: [/#if]
        datosIngresar.put("nombresSolicitante",personaSol.getNombreApellido());
        datosIngresar.put("numeroDeProceso", sol.getNumeroProceso());
        datosIngresar.put("nombrePerito", personaPerito.getNombreApellido());
        datosIngresar.put("fechaCaducidad", pericia.getFechaLimiteEntrega().toString());
        datosIngresar.put("direccionPerito", personaPerito.getDireccion());
        datosIngresar.put("telefonoFijo", personaPerito.getTelefonoFijo());
        datosIngresar.put("telefonoCelular", personaPerito.getTelefonoCelular());
        datosIngresar.put("correoElectronico", personaPerito.getCorreo());
        datosIngresar.put("fechaHoraCorreo", archivoMl.getSentDate().toString());
        datosIngresar.put("remitente", archivoMl.getFrom().toString());
        if(archivoMl.getSender()!=null){
        datosIngresar.put("ipRemitente", archivoMl.getSender().toString());
        }
        datosIngresar.put("asunto", archivoMl.getSubject());
        datosIngresar.put("id", archivoMl.getContentID());
        datosIngresar.put("contentMd5", archivoMl.getContentMD5());
        datosIngresar.put("contentType", archivoMl.getContentType());
        datosIngresar.put("descripcion", archivoMl.getDescription());
        datosIngresar.put("disposicion", archivoMl.getDisposition());
        datosIngresar.put("encoding", archivoMl.getEncoding());
        datosIngresar.put("tamano", archivoMl.getSize()+"");
        
        File f = new File(rutaArchivoEml);
        FileReader fr = new  FileReader(f);
        BufferedReader bf = new BufferedReader(fr);
        String linea = "";
        String data = "";
        while((linea = bf.readLine())!= null){
            data += linea +"\n";
        };
        
        datosIngresar.put("contenido", "AÑADA EL CONTENIDO DEL ARCHIVO .EML DESCARGADO");
        
        Date fechaActual = new Date();
        String nombreSalida = fechaActual.getDay()+"_"+fechaActual.getMonth()+"_"
                +fechaActual.getYear()+"_"+fechaActual.getHours()
                +"_"+fechaActual.getMinutes()+"_"+fechaActual.getSeconds();
        
  
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");

        configuration.setDirectoryForTemplateLoading(new File(directorioBase.getAbsolutePath()));


        //Salida del archivo
        File outFile = new File(directorio.getAbsolutePath()+"/"+nombreSalida+".xml");

        Template t = configuration.getTemplate("base.ftl", "utf-8");
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);
        t.process(datosIngresar, out);
        out.close();
        
    }
    
    public MimeMessage leerMl(String rutaArchivoEml){
        Properties props = System.getProperties();
        File emlFile = new File(rutaArchivoEml);
        InputStream source;
        try {
            Session mailSession = Session.getInstance(props,null);
            source = new FileInputStream(emlFile);
            MimeMessage message = new MimeMessage(mailSession, source);
            return message;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utilidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void copiarArchivoBase(String rutaOrigen,String rutaDestino) {
        try {
            
            File ficheroCopiar = new File(rutaOrigen);
            File ficheroDestino = new File(rutaDestino);
            if (ficheroCopiar.exists()) {
                Files.copy(Paths.get(ficheroCopiar.getAbsolutePath()), Paths.get(ficheroDestino.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
            } else {
                System.out.println("El fichero " + rutaOrigen + " no existe en el directorio " + rutaOrigen);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
