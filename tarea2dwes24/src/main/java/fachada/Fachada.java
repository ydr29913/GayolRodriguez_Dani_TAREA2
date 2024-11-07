package fachada;

import dao.ConexionBD;
import dao.PlantaDAO;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import modelo.Planta;

public class Fachada {

    private static Fachada instancia;

    private Fachada() {
        
    }

    public static Fachada getInstancia() {
        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }

    public void mostrarMenu() {
        // Implementación de menú principal con opciones
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
                        //menuLogin();
                        break;
                    case 2:
                        //añadirPlanta();
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
    
    public void ejecutarOpcion(int opcion) {
        // Llamar a los métodos correspondientes para cada opción
    }

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
}