package fachada;

import dao.ConexionBD;
import dao.PlantaDAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.cj.jdbc.MysqlDataSource;

import control.Controlador;
import modelo.Planta;

public class Fachada {

    private static Fachada instancia;
    private Controlador controlador;

    private Fachada() {
        controlador = Controlador.getServicios();
    }

    public static Fachada getInstancia() {
        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }

    public void mostrarMenu() {
    	
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
                        menuLogin();
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
                        //logearse();
                        break;
                    case 3:
                        //logearse();
                    case 9:
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes introducir un número entero");
                sc.nextLine();
            }
        } while (opcion != 9);
    }
    
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
    
    
    public void añadirPlanta() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nIngrese los datos de la nueva planta:");

        System.out.println("Código de la planta: ");
        String codigo = sc.nextLine();

        System.out.println("Nombre común: ");
        String nombreComun = sc.nextLine();

        System.out.println("Nombre científico: ");
        String nombreCientifico = sc.nextLine();

        Planta planta = new Planta(codigo, nombreComun, nombreCientifico);

        try {
            boolean exito = controlador.insertarPlanta(planta);

            if (exito) {
                System.out.println("Planta añadida correctamente.");
            } else {
                System.out.println("No se pudo añadir la planta. Verifique que el código sea único.");
            }

        } catch (Exception e) {
            System.out.println("Ocurrió un error al intentar añadir la planta: " + e.getMessage());
        }
    }
   
    
    public void ejecutarOpcion(int opcion) {
        // Llamar a los métodos correspondientes para cada opción
    }

    /*
    public void verPlantas() {
        PlantaDAO plantaDAO = new PlantaDAO(ConexionBD.getConnection());
        try {
            List<Planta> plantas = plantaDAO.findAll();
            for (Planta planta : plantas) {
                System.out.println(planta.getCodigo() + ": " + planta.getNombreComun());
            }
        } catch (SQLException e) {
            System.out.println("Error al recuperar las plantas: " + e.getMessage());
        }
    }
    */
}