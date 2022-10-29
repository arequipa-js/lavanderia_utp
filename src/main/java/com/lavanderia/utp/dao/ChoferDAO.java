package com.lavanderia.utp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lavanderia.utp.utils.DBConnection;
import java.sql.SQLException;

import com.lavanderia.utp.model.Chofer;
import java.util.ArrayList;
import java.util.List;
import com.lavanderia.utp.interfaces.GenericInterface;

public class ChoferDAO implements GenericInterface<Chofer> {

    static Connection con = DBConnection.getConnection();
    static PreparedStatement ps;
    static ResultSet rs;

    @Override
    public List<Chofer> getAll() {
        List<Chofer> list = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT * FROM choferes ORDER BY id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Chofer chofer = new Chofer();
                chofer.setId(rs.getInt("id"));
                chofer.setNombres(rs.getString("nombres"));
                chofer.setApellidos(rs.getString("apellidos"));
                chofer.setNroLicencia(rs.getString("nro_licencia"));
                chofer.setDisponible(rs.getBoolean("disponible"));
                chofer.setFechaCreacion(rs.getDate("fecha_creacion"));
                list.add(chofer);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public void add(Chofer chofer) {
        String sql = "INSERT INTO choferes (nombres, apellidos, nro_licencia) VALUES ('" + chofer.getNombres() + "', '" + chofer.getApellidos() + "', '" + chofer.getNroLicencia() + "')";
        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Chofer getById(int id) {
        Chofer chofer = new Chofer();
        try {
            ps = con.prepareStatement("SELECT * FROM choferes WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                chofer.setId(rs.getInt("id"));
                chofer.setNombres(rs.getString("nombres"));
                chofer.setApellidos(rs.getString("apellidos"));
                chofer.setNroLicencia(rs.getString("nro_licencia"));
                chofer.setDisponible(rs.getBoolean("disponible"));
                chofer.setFechaCreacion(rs.getDate("fecha_creacion"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return chofer;
    }

    @Override
    public void update(Chofer chofer) {
        String sql = "UPDATE choferes set nombres = '" + chofer.getNombres() + "', apellidos = '" + chofer.getApellidos() + "', nro_licencia = '" + chofer.getNroLicencia() + "', disponible = " + chofer.getDisponible() + " WHERE id = " + chofer.getId();
        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM choferes WHERE id = " + id;
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Chofer> search(String searchText) {
        return null;
    }
}
