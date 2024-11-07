package control;

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