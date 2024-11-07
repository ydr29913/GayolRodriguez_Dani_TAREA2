package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Planta;

public class PlantaDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public PlantaDAO(Connection con) {
        this.con = con;
    }

    public int insertar(Planta p) throws SQLException {
        try {
            ps = con.prepareStatement("insert into plantas (codigo, nombreComun, nombreCientifico) values (?,?,?)");
            ps = setString(1, p.getCodigo());
            ps = setString(2, p.getNombreComun());
            ps = setString(3, p.getNombreCientifico());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar en plantas: " + e.getMessage());
        }
        return 0;
    }

    public int modificar(Planta p) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int eliminar(Planta ej) {
        try {
            ps = con.prepareStatement("delete from plantas where codigo = ?");
            ps.setString(1, ej.getCodigo());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar planta " + e.getMessage());
        }
        return 0;
    }

    public Planta findByCodigo(String codigo) throws SQLException {
        Planta planta = null;
        String query = "SELECT * FROM plantas WHERE codigo = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    planta = new Planta(
                        rs.getString("codigo"),
                        rs.getString("nombreComun"),
                        rs.getString("nombreCientifico")
                    );
                }
            }
        }
        return planta;
    }

    public List<Planta> findAll() throws SQLException {
        List<Planta> plantas = new ArrayList<>();
        String query = "SELECT * FROM plantas ORDER BY nombreComun";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                plantas.add(new Planta(
                        rs.getString("codigo"),
                        rs.getString("nombreComun"),
                        rs.getString("nombreCientifico")
                ));
            }
        }
        return plantas;
    }

    public ArrayList<Planta> findByNombreComun(String nombre) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArrayList<Planta> findByNombreCientifico(String nombre) {
        // TODO Auto-generated method stub
        return null;
    }

    private PreparedStatement setString(int index, String value) throws SQLException {
        ps.setString(index, value);
        return ps;
    }
}
