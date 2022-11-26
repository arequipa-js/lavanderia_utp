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
import com.lavanderia.utp.model.Persona;
import com.lavanderia.utp.model.Servicio;
import com.lavanderia.utp.model.SolicitudDetalle;
import com.lavanderia.utp.utils.Common;
import com.lavanderia.utp.utils.EmailService;

public class SolicitudDAO implements GenericInterface<Solicitud> {

    static Connection con = DBConnection.getConnection();
    static PreparedStatement ps;
    static ResultSet rs;
    PersonaDAO personaDAO = new PersonaDAO();
    ServicioDAO servicioDAO = new ServicioDAO();

    @Override
    public List<Solicitud> getAll() {
        return null;
    }

    public List<Solicitud> getByEstado(char estado) {
        List<Solicitud> list = new ArrayList<>();
        try {
            String sql = "SELECT s.id, s.persona_id, s.fecha_creacion as fc, CONCAT(p.nombres, ' ', p.apellidos) AS cliente, s.estado FROM solicitudes s JOIN personas p ON p.id = s.persona_id";
            if (estado != '*') {
                sql += " WHERE s.estado = '" + estado + "'";
            }
            sql += " ORDER BY s.id";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Solicitud solicitud = new Solicitud();
                solicitud.setId(rs.getInt("id"));
                solicitud.setPersonaId(rs.getInt("persona_id"));
                solicitud.setFechaCreacion(rs.getDate("fc"));
                solicitud.setCliente(rs.getString("cliente"));
                solicitud.setEstado(rs.getString("estado").charAt(0));
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
        int solicitudId = 0;
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps = con.prepareStatement("SELECT id FROM solicitudes ORDER BY id DESC LIMIT 1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                solicitudId = rs.getInt("id");
            }

            SolicitudDetalle solicitudDetalle = new SolicitudDetalle();
            solicitudDetalle.setSolicitudId(solicitudId);
            solicitudDetalle.setServicioId(solicitud.getServicioId());
            solicitudDetalle.setPrendaId(solicitud.getPrendaId());
            solicitudDetalle.setObservaciones(solicitud.getObservaciones());

            SolicitudDetalleDAO solicitudDetalleDAO = new SolicitudDetalleDAO();
            solicitudDetalleDAO.add(solicitudDetalle);

            try {
                Persona persona = personaDAO.getById(solicitud.getPersonaId());
                Servicio servicio = servicioDAO.getById(solicitud.getServicioId());
                String toEmail = persona.getEmail();
                String subject = Common.SOLICITUD_ASUNTO;
                String message = "<h1>Estimado(a): " + persona.getNombres() + " " + persona.getApellidos() + "</h1><br>";
                message += Common.SOLICITUD_MENSAJE + solicitudId + "<br>";
                message += Common.SERVICIO_MENSAJE + servicio.getNombre() + "<br>";
                message += "<br><br>" + Common.GRACIAS;
                EmailService emailService = new EmailService();
                emailService.sendMail(toEmail, subject, message);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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
                solicitud.setEstado(rs.getString("estado").charAt(0));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return solicitud;
    }

    @Override
    public void update(Solicitud solicitud) {
        String sql = "UPDATE solicitudes set persona_id = " + solicitud.getPersonaId() + ", estado = '" + solicitud.getEstado() + "' WHERE id = " + solicitud.getId();
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
