package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import modelo.Mensaje;

public class MensajeDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public MensajeDAO(Connection con) {
        this.con = con;
    }

    public int insertar(Mensaje mj) {
        try {
            ps = con.prepareStatement("insert into mensajes (id, fechaHora, mensaje) values (?,?,?)");
            ps = setInt(1, mj.getId());
            ps = setLocalDateTime(2, mj.getFechaHora());
            ps = setString(3, mj.getMensaje());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar en mensajes: " + e.getMessage());
        }
        return 0;
    }

    public int modificar(Mensaje mj) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int eliminar(Mensaje mj) {
        try {
            ps = con.prepareStatement("delete from mensajes where id = ?");
            ps.setInt(1, mj.getId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar mensaje " + e.getMessage());
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

    private PreparedStatement setDate(int i, Date value) throws SQLException {
        ps.setDate(i, new java.sql.Date(value.getTime()));
        return ps;
    }

    private PreparedStatement setLocalDateTime(int i, Date date) throws SQLException {
        /*
        ps.setDate(i, new java.sql.Date(value.getLocalDateTime()));
        return ps;
        */
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}