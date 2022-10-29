package com.lavanderia.utp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lavanderia.utp.utils.DBConnection;
import java.sql.SQLException;

import com.lavanderia.utp.model.Movilidad;
import java.util.ArrayList;
import java.util.List;
import com.lavanderia.utp.interfaces.GenericInterface;

public class MovilidadDAO implements GenericInterface<Movilidad> {

    static Connection con = DBConnection.getConnection();
    static PreparedStatement ps;
    static ResultSet rs;

    @Override
    public List<Movilidad> getAll() {
        List<Movilidad> list = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT m.id, m.nombre, m.marca, m.modelo, m.chofer_id, m.disponible, m.fecha_creacion, CONCAT(c.nombres, ' ', c.apellidos) AS chofer FROM movilidades m JOIN choferes c ON c.id = m.chofer_id ORDER BY m.id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Movilidad movilidad = new Movilidad();
                movilidad.setId(rs.getInt("id"));
                movilidad.setNombre(rs.getString("nombre"));
                movilidad.setMarca(rs.getString("marca"));
                movilidad.setModelo(rs.getString("modelo"));
                movilidad.setChoferId(rs.getInt("chofer_id"));
                movilidad.setDisponible(rs.getBoolean("disponible"));
                movilidad.setFechaCreacion(rs.getDate("fecha_creacion"));
                movilidad.setChofer(rs.getString("chofer"));
                list.add(movilidad);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public void add(Movilidad movilidad) {
        String sql = "INSERT INTO movilidades (nombre, marca, modelo, chofer_id) VALUES ('" + movilidad.getNombre() + "', '" + movilidad.getMarca() + "', '" + movilidad.getModelo() + "', " + movilidad.getChoferId() + ")";
        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Movilidad getById(int id) {
        Movilidad movilidad = new Movilidad();
        try {
            ps = con.prepareStatement("SELECT * FROM movilidades WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                movilidad.setId(rs.getInt("id"));
                movilidad.setNombre(rs.getString("nombre"));
                movilidad.setMarca(rs.getString("marca"));
                movilidad.setModelo(rs.getString("modelo"));
                movilidad.setChoferId(rs.getInt("chofer_id"));
                movilidad.setDisponible(rs.getBoolean("disponible"));
                movilidad.setFechaCreacion(rs.getDate("fecha_creacion"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return movilidad;
    }

    @Override
    public void update(Movilidad movilidad) {
        String sql = "UPDATE movilidades set nombre = '" + movilidad.getNombre() + "', marca = '" + movilidad.getMarca() + "', modelo = '" + movilidad.getModelo() + "', disponible = " + movilidad.getDisponible() + ", chofer_id = " + movilidad.getChoferId() + " WHERE id = " + movilidad.getId();
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
        String sql = "DELETE FROM movilidades WHERE id = " + id;
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Movilidad> search(String searchText) {
        return null;
    }
}
