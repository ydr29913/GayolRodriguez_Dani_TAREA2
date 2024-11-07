package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Credenciales;

public class CredencialesDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public CredencialesDAO(Connection con) {
        this.con = con;
    }

    public int insertar(Credenciales cr) {
        try {
            ps = con.prepareStatement("insert into credenciales (id, usuario, password) values (?,?,?)");
            ps = setInt(1, cr.getId());
            ps = setString(2, cr.getUsuario());
            ps = setString(3, cr.getPassword());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar en credenciales: " + e.getMessage());
        }
        return 0;
    }

    public int modificar(Credenciales cr) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int eliminar(Credenciales cr) {
        try {
            ps = con.prepareStatement("delete from credenciales where id = ?");
            ps.setInt(1, cr.getId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar credencial " + e.getMessage());
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