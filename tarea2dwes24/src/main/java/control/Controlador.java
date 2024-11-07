package control;

import java.sql.SQLException;

import modelo.Planta;

public class Controlador {

    public static Controlador servicios;

    private ServiciosPlanta servPlanta;
    private ServiciosEjemplar servEj;
    private ServiciosPersona servPersona;
    private ServiciosMensaje servMensaje;

    public static Controlador getServicios() {
        if (servicios == null) {
            servicios = new Controlador();
        }
        return servicios;
    }

    private Controlador() {
        servPlanta = new ServiciosPlanta();
        servEj = new ServiciosEjemplar();
        servPersona = new ServiciosPersona();
        servMensaje = new ServiciosMensaje();
    }
    
    public boolean insertarPlanta(Planta p) {
        try {
            return servPlanta.insertarPlanta(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean modificarPlanta(Planta p) {
        try {
            return servPlanta.modificarPlanta(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    

    public ServiciosPlanta getServiciosPlanta() {
        return servPlanta;
    }

    public ServiciosEjemplar getServiciosEjemplar() {
        return servEj;
    }

    public ServiciosPersona getServiciosPersona() {
        return servPersona;
    }

    public ServiciosMensaje getServiciosMensaje() {
        return servMensaje;
    }
}