/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import metodos.conexionMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
/**
 *
 * @author joelc
 */
public class tablasMysql {

    conexionMysql c = new conexionMysql();
    
    public void crearBaseDatos(){
        try {
            c.ConectarBasedeDatos();
            Statement s = c.getConnection().createStatement();
            String consulta = "CREATE DATABASE if not exists forenceapp";
            s.execute(consulta);
            crearTablas(s);
            System.out.println("Base de datos creada con exito!!");
            c.DesconectarBasedeDatos();
        } catch (SQLException ex) {
            System.out.println("Error al crear la base de datos!");         
        }
    }
    
    public void crearTablas(Statement s) {
        try {
            String sqlPersona = "CREATE TABLE if not exists persona("
                    +"idPersona int not null AUTO_INCREMENT,"
                    +"nombreApellido VARCHAR(100),"
                    +"direccion VARCHAR(100),"
                    +"telefonoFijo VARCHAR(10),"
                    +"telefonoCelular VARCHAR(10),"
                    +"cedula VARCHAR(10),"
                    +"correo VARCHAR(60),"
                    + "PRIMARY KEY(idPersona)"
                    +");";
            
            String sqlSolicitante = "CREATE TABLE if not exists solicitante("
                    +"idSolicitante int not null AUTO_INCREMENT,"
                    +"urlPericia VARCHAR(5000),"
                    + "numeroProceso VARCHAR(10),"
                    + "unidadPertenece VARCHAR(200),"
                    + "idPersona int NOT NULL,"
                    + "PRIMARY KEY(idSolicitante),"
                    + "FOREIGN KEY(idPersona) "
                    + "references persona(idPersona)" 
                    + ");";
            
            String sqlPerito = "CREATE TABLE if not exists perito("
                    +"idPerito int not null AUTO_INCREMENT,"
                    +"numeroCasoInterno VARCHAR(10),"
                    + "idPersona int NOT NULL,"
                    + "PRIMARY KEY(idPerito),"
                    + "FOREIGN KEY(idPersona) "
                    + "references persona(idPersona)" 
                    + ");";
            String sqlProceso= "CREATE TABLE if not exists proceso("
                    +"idProceso int not null AUTO_INCREMENT,"
                    +"fechaSolicitud DATE,"
                    + "fechaLimiteEntrega DATE,"
                    + "valorPericia DOUBLE(5,2),"
                    + "idPerito int NOT NULL,"
                    + "idSolicitante int NOT  NULL,"
                    + "PRIMARY KEY(idProceso),"
                    + "FOREIGN KEY(idPerito) "
                    + "references perito(idPerito),"
                    + "FOREIGN KEY (idSolicitante) "
                    + "references solicitante(idSolicitante)" 
                    + ");";
            s.execute(sqlPersona);
            s.execute(sqlSolicitante);
            s.execute(sqlPerito);
            s.execute(sqlProceso);
            
            System.out.println("Tablas creadas con exito");
        } catch (SQLException ex) {
            System.out.println("Error al crear las tablas de la base de datos! : "
                    + ex);         
            
        }
    }
}
