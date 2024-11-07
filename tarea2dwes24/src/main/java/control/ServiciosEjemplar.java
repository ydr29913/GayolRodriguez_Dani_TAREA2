package control;

import dao.ConexionBD;
import dao.EjemplarDAO;
import modelo.Ejemplar;

public class ServiciosEjemplar {
    
    private ConexionBD con;
    private EjemplarDAO ejemplarDAO;
    
    public ServiciosEjemplar() {
        con = ConexionBD.getInstance();
        ejemplarDAO = (EjemplarDAO) con.getEjemlarDAO();
    }
    
    public boolean validarEjemplar(Ejemplar ej) {
        boolean ret = false;
        if (ej.getNombre().isEmpty()) return false;
        if (ej.getNombre().length() < 3 || ej.getNombre().length() > 20) return false;
        
        return true;
    }
    
    public int insertar (Ejemplar ej) {
        return ejemplarDAO.insertar(ej);
    }
}