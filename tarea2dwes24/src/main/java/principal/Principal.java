package principal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	}
	
	
	
	//Sirve para mostrar un menu de opciones por pantalla
	public void menu() {
		Scanner sc = new Scanner(System.in);
		
		int opcion = 0;

        do {
            try {
                System.out.println("\n\n\n\t\t\tGESTION DE VIVERO\n");
                System.out.println("\t\t\t\t1 - LOGIN");
                System.out.println("\t\t\t\t2 - AÑADIR PLANTAS");
                System.out.println("\t\t\t\t3 - ..............");
                System.out.println("\t\t\t\t4 - ..............");
                System.out.println("\t\t\t\t5 - ..............");
                System.out.println("\t\t\t\t9 - SALIR");

                opcion = sc.nextInt();
                switch (opcion) {
                	case 1:
                		logearse();
                		break;
                	case 2:
                        añadirPlanta();
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
	public void logearse() {
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
                		invitado();
                		break;
                	case 2:
                        //personal();
                        break;
                	case 3:
                		//administradorGeneral();
                    case 9:
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes introducir un número entero");
                sc.nextLine();
            }
        } while (opcion != 9);
	}
	
	
	//Menu del usuario invitado
	public void invitado() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n\tIntroduce el nombre del usuario: ");
			String usuario = sc.nextLine();
		System.out.println("\n\tIntroduce la contraseña del usuario: ");
			String password = sc.nextLine();
		
		
		
		int opcion = 0;

        do {
            try {
                System.out.println("\n\n\n\t\t\tGESTION VIVERO (INVITADO)\n");
                System.out.println("\t\t\t\t1 - ........");
                System.out.println("\t\t\t\t2 - ........");
                System.out.println("\t\t\t\t3 - ........");
                System.out.println("\t\t\t\t9 - SALIR");

                opcion = sc.nextInt();
                switch (opcion) {
                	case 1:
                		//........();
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
		
	
	
	//Sirve para añadir una nueva planta a la base de datos
	public void añadirPlanta() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Dame el codigo de la nueva planta: ");
		String codigo = sc.nextLine().trim().toUpperCase();
		
		System.out.println("Dame el nombre comun de la planta: ");
		String nombre_comun = sc.nextLine();
		
		System.out.println("Dame el nombre cientifico de la planta: ");
		String nombre_cientifico = sc.nextLine();
		
		Planta nueva = new Planta(codigo, nombre_comun, nombre_cientifico);
	}
	
}