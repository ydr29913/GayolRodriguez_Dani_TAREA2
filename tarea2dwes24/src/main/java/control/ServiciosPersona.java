package control;

import dao.ConexionBD;
import dao.PersonaDAO;
import dao.PlantaDAO;
import modelo.Persona;

public class ServiciosPersona {
    
    private ConexionBD con;
    private PersonaDAO personaDAO;
    
    public ServiciosPersona() {
        con = ConexionBD.getInstance();
        personaDAO = (PersonaDAO) con.getPersonaDAO();
    }
    
    public boolean validarPersona(Persona pe) {
        boolean ret = false;
        if (pe.getNombre().isEmpty()) return false;
        if (pe.getNombre().length() < 3 || pe.getNombre().length() > 20) return false;
        
        return true;
    }
    
    public int insertar (Persona pe) {
        return personaDAO.insertar(pe);
    }
}