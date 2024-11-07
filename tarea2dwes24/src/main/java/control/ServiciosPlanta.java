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
        if (p.getCodigo() == "") {
            System.out.println("El código de la planta no puede estar vacío.");
            return false;
        }
        
        return true;
    }

    public boolean esCodigoUnico(String codigo) throws SQLException {
        Planta plantaExistente = plantaDAO.findByCodigo(codigo);
        return plantaExistente == null;
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
            System.out.println("Código: " + planta.getCodigo() + ", Nombre Común: " + planta.getNombreComun() + ", Nombre Científico: " + planta.getNombreCientifico());
        }
    }
    
    /*
    public int insertar(Planta e) throws SQLException {
        return plantaDAO.insertar(e);
    }
    */
    
    public boolean insertarPlanta(Planta e) throws SQLException {
        if (!validarPlanta(e)) {
            return false;
        }

        if (!esCodigoUnico(e.getCodigo())) {
            System.out.println("El código de la planta ya existe en la base de datos.");
            return false;
        }

        return plantaDAO.insertar(e) > 0;
    }
    
    public boolean modificarPlanta(Planta e) throws SQLException {
        if (!validarPlanta(e)) {
            return false;
        }

        Planta plantaExistente = plantaDAO.findByCodigo(e.getCodigo());
        if (plantaExistente == null) {
            System.out.println("La planta con el código " + e.getCodigo() + " no existe.");
            return false;
        }

        return plantaDAO.modificar(e) > 0;
    }
}