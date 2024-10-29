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
		//p.menu();
		
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

}