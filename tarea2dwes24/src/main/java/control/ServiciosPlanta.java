package control;

import dao.ConexionBD;
import dao.PlantaDAO;
import java.sql.SQLException;
import java.util.List;
import modelo.Planta;

public class ServiciosPlanta {

    private ConexionBD con;
    private PlantaDAO plantaDAO;

    public ServiciosPlanta() {
        con = ConexionBD.getInstance();
        plantaDAO = (PlantaDAO) con.getPlantaDAO();
    }

    public boolean validarPlanta(Planta p) {
        boolean ret = false;
        if (p.getCodigo() == null || p.getCodigo().isEmpty()) {
            System.out.println("El código de la planta no puede estar vacío.");
            return false;
        }
        if (p.getCodigo().length() < 3 || p.getCodigo().length() > 20) {
            return false;
        }
        
        return true;
    }

    /*
    public boolean validarPlanta(Planta p) {
        boolean ret = true;
        if (p.getCodigo().isEmpty() || p.getCodigo().length() < 3 || p.getCodigo().length() > 20) {
            ret = false;
        }
        // Aquí puedes agregar una validación para verificar si la planta ya existe en la BD
        if (ret) {
            Planta plantaExistente = plantaDAO.findByCodigo(p.getCodigo());
            if (plantaExistente != null) {
                ret = false; // Ya existe una planta con ese código
            }
        }
        return ret;
    }
    */
    
    // Método para mostrar las plantas
    public void mostrarPlantas() throws SQLException {
        List<Planta> plantas = plantaDAO.findAll();
        for (Planta planta : plantas) {
            System.out.println("Código: " + planta.getCodigo() + ", Nombre Común: " + planta.getNombreComun() +
                    ", Nombre Científico: " + planta.getNombreCientifico());
        }
    }
    
    public int insertar(Planta e) throws SQLException {
        return plantaDAO.insertar(e);
    }
}