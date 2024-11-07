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
		//p.menu();

		fachada.Fachada.getInstancia().mostrarMenu();
		
	}
		
}