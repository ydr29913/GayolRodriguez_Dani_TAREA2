package control;

import dao.ConexionBD;
import dao.MensajeDAO;
import modelo.Mensaje;

public class ServiciosMensaje {
    
    private ConexionBD con;
    private MensajeDAO mensajeDAO;
    
    public ServiciosMensaje() {
        con = ConexionBD.getInstance();
        mensajeDAO = (MensajeDAO) con.getMensajeDAO();
    }
    
    public boolean validarMensaje(Mensaje mj) {
        boolean ret = false;
        if (mj.getMensaje().isEmpty()) return false;
        if (mj.getMensaje().length() < 3 || mj.getMensaje().length() > 20) return false;
        
        return true;
    }
    
    public int insertar (Mensaje mj) {
        return mensajeDAO.insertar(mj);
    }
}