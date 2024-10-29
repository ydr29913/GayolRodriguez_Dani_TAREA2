package modelo;

public class Planta {

	private String codigo;
	private String nombreComun;
	private String nombreCientifico;
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getNombreComun() {
		return nombreComun;
	}
	public void setNombreComun(String nombreComun) {
		this.nombreComun = nombreComun;
	}
	
	public String getNombreCientifico() {
		return nombreCientifico;
	}
	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}
	
	
	public Planta(String codigo, String nombre_comun, String nombre_cientifico) {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Planta [codigo=" + codigo + ", nombreComun=" + nombreComun + ", nombreCientifico=" + nombreCientifico
				+ "]";
	}
}