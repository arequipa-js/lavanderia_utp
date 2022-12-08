package com.lavanderia.utp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lavanderia.utp.utils.DBConnection;
import java.sql.SQLException;

import com.lavanderia.utp.model.SolicitudDetalle;
import java.util.ArrayList;
import java.util.List;
import com.lavanderia.utp.interfaces.SolicitudDetalleInterface;

public class SolicitudDetalleDAO implements SolicitudDetalleInterface {

    static Connection con = DBConnection.getConnection();
    static PreparedStatement ps;
    static ResultSet rs;

    @Override
    public List<SolicitudDetalle> getAll() {
        return null;
    }

    public List<SolicitudDetalle> getByEstado(char estado) {
        List<SolicitudDetalle> list = new ArrayList<>();
        try {
            String sql = "SELECT sd.id, sd.solicitud_id, s.persona_id, s.fecha_solicitud, CONCAT(p.nombres, ' ', p.apellidos) AS cliente, sd.servicio_id, se.nombre as servicio, sd.observaciones, s.estado FROM solicitud_detalles sd JOIN solicitudes s on sd.solicitud_id = s.id JOIN personas p ON p.id = s.persona_id join servicios se on se.id = sd.servicio_id ";
            if (estado != '*') {
                sql += " WHERE s.estado = '" + estado + "'";
            }
            sql += " ORDER BY s.id";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SolicitudDetalle solicitud = new SolicitudDetalle();
                solicitud.setId(rs.getInt("id"));
                solicitud.setSolicitudId(rs.getInt("solicitud_id"));
                solicitud.setServicioId(rs.getInt("servicio_id"));
                solicitud.setObservaciones(rs.getString("observaciones"));
                solicitud.setCliente(rs.getString("cliente"));
                solicitud.setServicio(rs.getString("servicio"));
                solicitud.setFechaSolicitud(rs.getString("fecha_solicitud"));
                solicitud.setEstado(rs.getString("estado").charAt(0));
                list.add(solicitud);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public void add(SolicitudDetalle solicitudDetalle) {
        String sql = "INSERT INTO solicitud_detalles (solicitud_id, servicio_id, prenda_id, observaciones) VALUES (" + solicitudDetalle.getSolicitudId() + ", " + solicitudDetalle.getServicioId() + ", " + solicitudDetalle.getPrendaId() + ", '" + solicitudDetalle.getObservaciones() + "')";
        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SolicitudDetalle> getBySolicitudId(int id) {
        List<SolicitudDetalle> list = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT sd.id, sd.solicitud_id, s.persona_id, s.fecha_solicitud, CONCAT(p.nombres, ' ', p.apellidos) AS cliente, sd.servicio_id, se.nombre as servicio, tarifa, sd.observaciones, pre.cantidad, s.estado FROM solicitud_detalles sd JOIN solicitudes s on sd.solicitud_id = s.id JOIN personas p ON p.id = s.persona_id join servicios se on se.id = sd.servicio_id JOIN prendas pre ON pre.id = sd.prenda_id WHERE s.id = ? ORDER BY sd.id");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SolicitudDetalle solicitudDetalle = new SolicitudDetalle();
                solicitudDetalle.setId(rs.getInt("id"));
                solicitudDetalle.setSolicitudId(rs.getInt("solicitud_id"));
                solicitudDetalle.setServicioId(rs.getInt("servicio_id"));
                solicitudDetalle.setObservaciones(rs.getString("observaciones"));
                solicitudDetalle.setCliente(rs.getString("cliente"));
                solicitudDetalle.setServicio(rs.getString("servicio"));
                solicitudDetalle.setTarifa(rs.getInt("tarifa"));
                solicitudDetalle.setCantidad(rs.getInt("cantidad"));
                solicitudDetalle.setFechaSolicitud(rs.getString("fecha_solicitud"));
                solicitudDetalle.setEstado(rs.getString("estado").charAt(0));
                list.add(solicitudDetalle);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public void update(SolicitudDetalle solicitudDetalle) {
        String sql = "UPDATE solicitud_detalles set solicitud_id = " + solicitudDetalle.getSolicitudId() + ", servicio_id = " + solicitudDetalle.getServicioId() + ", observaciones = '" + solicitudDetalle.getObservaciones() + "', prenda_id = " + solicitudDetalle.getPrendaId() + " WHERE id = " + solicitudDetalle.getId();
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM solicitud_detalles WHERE id = " + id;
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<SolicitudDetalle> search(String searchText) {
        return null;
    }
}
