package com.lavanderia.utp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lavanderia.utp.utils.DBConnection;
import java.sql.SQLException;

import com.lavanderia.utp.model.SolicitudDetalle;
import java.util.ArrayList;
import java.util.List;
import com.lavanderia.utp.interfaces.GenericInterface;

public class SolicitudDetalleDAO implements GenericInterface<SolicitudDetalle> {

    static Connection con = DBConnection.getConnection();
    static PreparedStatement ps;
    static ResultSet rs;

    @Override
    public List<SolicitudDetalle> getAll() {
        List<SolicitudDetalle> list = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT sd.id, sd.solicitud_id, s.persona_id, s.fecha_creacion as fc, CONCAT(p.nombres, ' ', p.apellidos) AS cliente, sd.servicio_id, se.nombre as servicio, sd.observaciones FROM solicitud_detalles sd JOIN solicitudes s on sd.solicitud_id = s.id JOIN personas p ON p.id = s.persona_id join servicios se on se.id = sd.servicio_id ORDER BY cliente");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SolicitudDetalle solicitud = new SolicitudDetalle();
                solicitud.setId(rs.getInt("id"));
                solicitud.setSolicitudId(rs.getInt("solicitud_id"));
                solicitud.setServicioId(rs.getInt("servicio_id"));
                solicitud.setObservaciones(rs.getString("observaciones"));
                solicitud.setCliente(rs.getString("cliente"));
                solicitud.setServicio(rs.getString("servicio"));
                solicitud.setFechaCreacion(rs.getDate("fc"));
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
    public SolicitudDetalle getById(int id) {
        SolicitudDetalle solicitud = new SolicitudDetalle();
        try {
            ps = con.prepareStatement("SELECT * FROM solicitud_detalles WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                solicitud.setId(rs.getInt("id"));
                solicitud.setSolicitudId(rs.getInt("solicitud_id"));
                solicitud.setServicioId(rs.getInt("servicio_id"));
                solicitud.setPrendaId(rs.getInt("prenda_id"));
                solicitud.setObservaciones(rs.getString("observaciones"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return solicitud;
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
        String sql = "DELETE FROM solicitudes WHERE id = " + id;
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