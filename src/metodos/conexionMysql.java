/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

/**
 *
 * @author joelc
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DeveloperTwo
 */
public class conexionMysql {

    public Connection conexion = null;
    public Statement sentencia;
    public ResultSet resultado;

    public void ConectarBasedeDatos() {
        final String URL = "jdbc:mysql://localhost:3306/forenceapp";
        final String USER = "root";
        final String CLAVE = "12345";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection(URL, USER, CLAVE);
            System.out.println("Conexion establecida");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: en la conexion "+ex);
        } catch (SQLException ex) {
            System.out.println("Error: en la conexion"+ex);
        }
        

    }

    public void DesconectarBasedeDatos() {
        try {
            if (conexion != null) {
                if (sentencia != null) {
                    sentencia.close();
                }
                conexion.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Excepcion", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public Connection getConnection() {
        return conexion;
    }
}
