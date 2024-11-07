package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Persona;

public class PersonaDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public PersonaDAO(Connection con) {
        this.con = con;
    }

    public int insertar(Persona pe) {
        try {
            ps = con.prepareStatement("insert into personas (id, nombre, email) values (?,?,?)");
            ps = setInt(1, pe.getId());
            ps = setString(2, pe.getNombre());
            ps = setString(3, pe.getEmail());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar en personas: " + e.getMessage());
        }
        return 0;
    }

    public int modificar(Persona pe) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int eliminar(Persona pe) {
        try {
            ps = con.prepareStatement("delete from personas where id = ?");
            ps.setInt(1, pe.getId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar persona " + e.getMessage());
        }
        return 0;
    }

    private PreparedStatement setInt(int index, int value) throws SQLException {
        ps.setInt(index, value);
        return ps;
    }

    private PreparedStatement setString(int index, String value) throws SQLException {
        ps.setString(index, value);
        return ps;
    }
}
