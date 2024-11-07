package dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {
    
    private Connection con;
    
    private static ConexionBD f;
    
    public static ConexionBD getInstance() {
        if (f == null)
            f = new ConexionBD();
        return f;
    }
    
    private ConexionBD() {
        Properties prop = new Properties();
        MysqlDataSource m = new MysqlDataSource();
        FileInputStream fis;
        
        try {
            fis = new FileInputStream("src/main/resources/db.properties");
            
            prop.load(fis);
            
            m.setUrl(prop.getProperty("url"));
            m.setPassword(prop.getProperty("password"));
            m.setUser(prop.getProperty("usuario"));
            
            con = m.getConnection();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Error al acceder al fichero properties " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al leer las propiedades del fichero properties " + e.getMessage());
        }
    }
    
    public PlantaDAO getPlantaDAO() {
        return new PlantaDAO(con);
    }
    
    public EjemplarDAO getEjemlarDAO() {
        return new EjemplarDAO(con);
    }
    
    public PersonaDAO getPersonaDAO() {
        return new PersonaDAO(con);
    }
    
    public MensajeDAO getMensajeDAO() {
        return new MensajeDAO(con);
    }
    
    public CredencialesDAO getCredencialesDAO() {
        return new CredencialesDAO(con);
    }

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}