package com.lavanderia.utp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lavanderia.utp.utils.DBConnection;
import java.sql.SQLException;

import com.lavanderia.utp.model.Solicitud;
import java.util.ArrayList;
import java.util.List;
import com.lavanderia.utp.interfaces.GenericInterface;

public class SolicitudDAO implements GenericInterface<Solicitud> {

    static Connection con = DBConnection.getConnection();
    static PreparedStatement ps;
    static ResultSet rs;

    @Override
    public List<Solicitud> getAll() {
        List<Solicitud> list = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT s.id, s.persona_id, s.fecha_creacion as fc, CONCAT(p.nombres, ' ', p.apellidos) AS cliente FROM solicitudes s JOIN personas p ON p.id = s.persona_id ORDER BY s.id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Solicitud solicitud = new Solicitud();
                solicitud.setId(rs.getInt("id"));
                solicitud.setPersonaId(rs.getInt("persona_id"));
                solicitud.setFechaCreacion(rs.getDate("fc"));
                solicitud.setCliente(rs.getString("cliente"));
                list.add(solicitud);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public void add(Solicitud solicitud) {
        String sql = "INSERT INTO solicitudes (persona_id) VALUES (" + solicitud.getPersonaId() + ")";
        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Solicitud getById(int id) {
        Solicitud solicitud = new Solicitud();
        try {
            ps = con.prepareStatement("SELECT * FROM solicitudes WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                solicitud.setId(rs.getInt("id"));
                solicitud.setPersonaId(rs.getInt("persona_id"));
                solicitud.setFechaCreacion(rs.getDate("fecha_creacion"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return solicitud;
    }

    @Override
    public void update(Solicitud solicitud) {
        String sql = "UPDATE solicitudes set persona_id = " + solicitud.getPersonaId() + " WHERE id = " + solicitud.getId();
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM solicitudes WHERE id = " + id;
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Solicitud> search(String searchText) {
        return null;
    }
}
