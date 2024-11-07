package control;

import dao.ConexionBD;
import dao.CredencialesDAO;
import modelo.Credenciales;

public class ServiciosCredenciales {
    
    private ConexionBD con;
    private CredencialesDAO credencialesDAO;
    
    public ServiciosCredenciales() {
        con = ConexionBD.getInstance();
        credencialesDAO = (CredencialesDAO) con.getCredencialesDAO();
    }
    
    public boolean validarPlanta(Credenciales cr) {
        boolean ret = false;
        if (cr.getUsuario().isEmpty()) return false;
        if (cr.getUsuario().length() < 3 || cr.getUsuario().length() > 20) return false;
        
        return true;
    }
    
    public int insertar (Credenciales cr) {
        return credencialesDAO.insertar(cr);
    }
}