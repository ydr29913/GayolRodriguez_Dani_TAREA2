package modelo;

public class Ejemplar {
	
	private int id;
	private String nombre;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public Ejemplar(int id, String nombre) {
		super();
	}
	
	@Override
	public String toString() {
		return "Ejemplar [id=" + id + ", nombre=" + nombre + "]";
	}
}