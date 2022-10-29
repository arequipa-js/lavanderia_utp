package com.lavanderia.utp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lavanderia.utp.utils.DBConnection;
import java.sql.SQLException;

import com.lavanderia.utp.model.Visita;
import java.util.ArrayList;
import java.util.List;
import com.lavanderia.utp.interfaces.GenericInterface;

public class VisitaDAO implements GenericInterface<Visita> {

    static Connection con = DBConnection.getConnection();
    static PreparedStatement ps;
    static ResultSet rs;

    @Override
    public List<Visita> getAll() {
        List<Visita> list = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT v.id, v.movilidad_id, v.solicitud_id, v.fecha_recojo, v.hora_recojo, v.fecha_creacion, m.nombre as movilidad, CONCAT(p.nombres, ' ', p.apellidos) AS cliente FROM visitas v JOIN movilidades m on m.id = v.movilidad_id JOIN solicitudes s on s.id = v.solicitud_id JOIN personas p on p.id = s.persona_id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Visita visita = new Visita();
                visita.setId(rs.getInt("id"));
                visita.setMovilidadId(rs.getInt("movilidad_id"));
                visita.setFechaRecojo(rs.getString("fecha_recojo"));
                visita.setHoraRecojo(rs.getString("hora_recojo"));
                visita.setSolicitudId(rs.getInt("solicitud_id"));
                visita.setFechaCreacion(rs.getDate("fecha_creacion"));
                visita.setCliente(rs.getString("cliente"));
                visita.setMovilidad(rs.getString("movilidad"));
                list.add(visita);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public void add(Visita visita) {
        String sql = "INSERT INTO visitas (solicitud_id, movilidad_id, fecha_recojo, hora_recojo) VALUES (" + visita.getSolicitudId() + ", " + visita.getMovilidadId() + ", '" + visita.getFechaRecojo() + "', '" + visita.getHoraRecojo() + "')";
        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Visita getById(int id) {
        Visita visita = new Visita();
        try {
            ps = con.prepareStatement("SELECT * FROM visitas WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                visita.setId(rs.getInt("id"));
                visita.setMovilidadId(rs.getInt("movilidad_id"));
                visita.setFechaRecojo(rs.getString("fecha_recojo"));
                visita.setHoraRecojo(rs.getString("hora_recojo"));
                visita.setSolicitudId(rs.getInt("solicitud_id"));
                visita.setFechaCreacion(rs.getDate("fecha_creacion"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return visita;
    }

    @Override
    public void update(Visita visita) {
        String sql = "UPDATE visitas set movilidad_id = " + visita.getMovilidadId() + ", solicitud_id = " + visita.getSolicitudId() + ", fecha_recojo = " + visita.getFechaRecojo() + ", hora_recojo = '" + visita.getHoraRecojo() + "' WHERE id = " + visita.getId();
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM visitas WHERE id = " + id;
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Visita> search(String searchText) {
        return null;
    }
}
