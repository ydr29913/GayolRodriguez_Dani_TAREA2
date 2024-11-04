package principal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.cj.jdbc.MysqlDataSource;

import modelo.Planta;

public class Principal {
	
	public static void main(String[] args) {
		Principal p = new Principal();
		
		//Sirve para llamar al metodo para mostrar el menu
		p.menu();
		
		/*
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Dame el codigo de la nueva planta: ");
		String codigo = sc.nextLine().trim().toUpperCase();
		
		System.out.println("Dame el nombre comun de la planta: ");
		String nombre_comun = sc.nextLine();
		
		System.out.println("Dame el nombre cientifico de la planta: ");
		String nombre_cientifico = sc.nextLine();
		
		Planta nueva = new Planta(codigo, nombre_comun, nombre_cientifico);
		
		Connection con;
		MysqlDataSource m = new MysqlDataSource();
		Properties prop = null;
		FileInputStream fis;
		
		String url;
		String usuario;
		String password;
		
		try {
			fis = new FileInputStream("src/main/resources/db.properties");
			prop.load(fis);
			url = prop.getProperty("url");
			usuario = prop.getProperty("usuario");
			password = prop.getProperty("password");
			m.setUrl(url);
			m.setUser(usuario);
			m.setPassword(password);
			
			con = m.getConnection();
			
			String sql = "INSERT INTO plantas(codigo, nombreComun, nombreCientifico) VALUES (" + nueva.getCodigo() + ", " + nueva.getNombreComun() + ", " + nueva.getNombreCientifico();
			String sql2 = "INSERT INTO plantas(codigo, nombreComun, nombreCientifico) VALUES (?, ?, ?)";
			
			PreparedStatement ps = con.prepareStatement(sql2);
			ps.setString(1, nueva.getCodigo());
			ps.setString(2, nueva.getNombreComun());
			ps.setString(3, nueva.getNombreCientifico());
			
			ps.executeUpdate();
			ps.close();
			con.close();
			
			
		} catch(SQLException e) {
			System.out.println("Se ha producido una SQLException: " + e.getLocalizedMessage());
			e.printStackTrace();
			
		} catch(FileNotFoundException e) {
			System.out.println("Se ha producido un FileNotFoundException: " + e.getLocalizedMessage());
			e.printStackTrace();
			
		} catch(IOException e) {
			System.out.println("Se ha producido un IOException: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		*/
	}
	
	
	
	//Sirve para mostrar un menu de opciones por pantalla
    public void menu() {
        Scanner sc = new Scanner(System.in);

        int opcion = 0;

        do {
            try {
                System.out.println("\n\n\n\t\t\tGESTION DE VIVERO\n");
                System.out.println("\t\t\t\t1 - LOGIN");
                System.out.println("\t\t\t\t2 - ..............");
                System.out.println("\t\t\t\t3 - ..............");
                System.out.println("\t\t\t\t4 - ..............");
                System.out.println("\t\t\t\t5 - ..............");
                System.out.println("\t\t\t\t9 - SALIR");

                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        menuLogin();
                        break;
                    case 9:
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes introducir un número entero");
                sc.nextLine();
            }
        } while (opcion != 9);
    }
	
	
	
    //Sirve para logearse en la aplicacion
    public void menuLogin() {
        Scanner sc = new Scanner(System.in);

        int opcion = 0;

        do {
            try {
                System.out.println("\n\n\n\t\t\tLOGIN\n");
                System.out.println("\t\t\t\t1 - INVITADO");
                System.out.println("\t\t\t\t2 - PERSONAL");
                System.out.println("\t\t\t\t3 - ADMINISTRADOR GENERAL");
                System.out.println("\t\t\t\t9 - SALIR");

                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        menuInvitado();
                        break;
                    case 2:
                        logearse();
                        break;
                    case 3:
                        logearse();
                    case 9:
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes introducir un número entero");
                sc.nextLine();
            }
        } while (opcion != 9);
    }
    
    
    
    public void logearse() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce tu nombre de usuario: ");
        String usuario = sc.nextLine().trim();

        System.out.println("Introduce tu contraseña: ");
        String password = sc.nextLine().trim();

        Connection con = null;
        MysqlDataSource m = new MysqlDataSource();
        Properties prop = new Properties();
        FileInputStream fis;

        try {
            fis = new FileInputStream("src/main/resources/db.properties");
            prop.load(fis);

            String url = prop.getProperty("url");
            String dbUsuario = prop.getProperty("usuario");
            String dbPassword = prop.getProperty("password");
            m.setUrl(url);
            m.setUser(dbUsuario);
            m.setPassword(dbPassword);

            con = m.getConnection();

            String sql = "SELECT * FROM credenciales WHERE usuario = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Login exitoso. Bienvenido " + rs.getString("usuario"));
                /*
                String rol = rs.getString("rol");
                if ("ADMINISTRADOR".equals(rol)) {
                    menuAdministrador();
                } else if ("PERSONAL".equals(rol)) {
                    menuPersonal();
                } else {
                    menuInvitado();
                }
                */
            } else {
                System.out.println("Nombre de usuario o contraseña incorrectos.");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Se ha producido una SQLException: " + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Se ha producido un FileNotFoundException: " + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Se ha producido un IOException: " + e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
	
	
  //Menu del usuario invitado
    public void menuInvitado() {
        Scanner sc = new Scanner(System.in);

        int opcion = 0;

        do {
            try {
                System.out.println("\n\n\n\t\t\tGESTION VIVERO (INVITADO)\n");
                System.out.println("\t\t\t\t1 - VER PLANTAS");
                System.out.println("\t\t\t\t2 - ........");
                System.out.println("\t\t\t\t3 - ........");
                System.out.println("\t\t\t\t9 - SALIR");

                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        verPlantas();
                        break;
                    case 2:
                        //........();
                        break;
                    case 3:
                    //........();
                    case 9:
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes introducir un número entero");
                sc.nextLine();
            }
        } while (opcion != 9);
    }
    
    
    
    public void verPlantas() {
        Connection con = null;
        MysqlDataSource m = new MysqlDataSource();
        Properties prop = new Properties();
        FileInputStream fis;

        try {
            fis = new FileInputStream("src/main/resources/db.properties");
            prop.load(fis);

            String url = prop.getProperty("url");
            String usuario = prop.getProperty("usuario");
            String password = prop.getProperty("password");
            m.setUrl(url);
            m.setUser(usuario);
            m.setPassword(password);

            con = m.getConnection();

            String sql = "SELECT codigo, nombreComun, nombreCientifico FROM plantas ORDER BY nombreComun ASC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n\t\tLISTADO DE PLANTAS");
            while (rs.next()) {
                String codigo = rs.getString("codigo");
                String nombreComun = rs.getString("nombreComun");
                String nombreCientifico = rs.getString("nombreCientifico");
                System.out.println("Código: " + codigo + ", Nombre Común: " + nombreComun + ", Nombre Científico: " + nombreCientifico);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Se ha producido una SQLException: " + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Se ha producido un FileNotFoundException: " + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Se ha producido un IOException: " + e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    
    public void menuPersonal() {
        Scanner sc = new Scanner(System.in);

        int opcion = 0;

        do {
            try {
                System.out.println("\n\n\n\t\t\tGESTION VIVERO (PESONAL)\n");
                System.out.println("\t\t\t\t1 - VER PLANTAS");
                System.out.println("\t\t\t\t2 - ........");
                System.out.println("\t\t\t\t3 - ........");
                System.out.println("\t\t\t\t9 - SALIR");

                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        verPlantas();
                        break;
                    case 2:
                        //........();
                        break;
                    case 3:
                    	//........();
                    case 9:
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes introducir un número entero");
                sc.nextLine();
            }
        } while (opcion != 9);
    }
    
    
    
    public void menuAdministrador() {
        Scanner sc = new Scanner(System.in);

        int opcion = 0;

        do {
            try {
                System.out.println("\n\n\n\t\t\tGESTION VIVERO (ADMINISTRADOR)\n");
                System.out.println("\t\t\t\t1 - VER PLANTAS");
                System.out.println("\t\t\t\t2 - REGISTRAR PERSONA");
                System.out.println("\t\t\t\t3 - AÑADIR PLANTA");
                System.out.println("\t\t\t\t9 - SALIR");

                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        verPlantas();
                        break;
                    case 2:
                        //registrarPersona();
                        break;
                    case 3:
                        //añadirPlanta();
                    case 9:
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes introducir un número entero");
                sc.nextLine();
            }
        } while (opcion != 9);
    }

	
}