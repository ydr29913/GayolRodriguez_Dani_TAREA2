package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Ejemplar;

public class EjemplarDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public EjemplarDAO(Connection con) {
        this.con = con;
    }

    public int insertar(Ejemplar ej) {
        try {
            ps = con.prepareStatement("insert into ejemplares (id, nombre) values (?,?)");
            ps = setInt(1, ej.getId());
            ps = setString(2, ej.getNombre());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar en ejemplares: " + e.getMessage());
        }
        return 0;
    }

    public int modificar(Ejemplar ej) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int eliminar(Ejemplar ej) {
        try {
            ps = con.prepareStatement("delete from ejemplares where id = ?");
            ps.setInt(1, ej.getId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar ejemplar " + e.getMessage());
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