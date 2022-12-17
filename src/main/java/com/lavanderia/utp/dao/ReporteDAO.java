package com.lavanderia.utp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lavanderia.utp.utils.DBConnection;
import java.sql.SQLException;

import com.lavanderia.utp.model.SolicitudDetalle;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAO {

    static Connection con = DBConnection.getConnection();
    static PreparedStatement ps;
    static ResultSet rs;

    public List<SolicitudDetalle> search(int personaId, int categoriaId, int servicioId, String fechaSolicitud) {
        List<SolicitudDetalle> list = new ArrayList<>();
        try {
            String sql = "SELECT sd.id, sd.solicitud_id, s.persona_id, s.fecha_solicitud, CONCAT(p.nombres, ' ', p.apellidos) AS cliente, sd.servicio_id, cat.nombre as categoria, se.nombre as servicio, tarifa \n" +
"FROM solicitud_detalles sd \n" +
"JOIN solicitudes s on sd.solicitud_id = s.id \n" +
"JOIN personas p ON p.id = s.persona_id \n" +
"JOIN servicios se on se.id = sd.servicio_id \n" +
"JOIN prendas pre ON pre.id = sd.prenda_id \n" +
"JOIN categorias cat on cat.id = se.categoria_id WHERE 1=1";

            if (personaId != 0) {
                sql += " AND p.id = " + personaId + "";
            }
            if (categoriaId != 0) {
                sql += " AND cat.id = " + categoriaId + "";
            }
            if (servicioId != 0) {
                sql += " AND se.id = " + servicioId + "";
            }
            if (!"".equals(fechaSolicitud)) {
                sql += " AND s.fecha_solicitud = '" + fechaSolicitud + "'";
            }
            System.out.println(sql);

            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SolicitudDetalle solicitudDetalle = new SolicitudDetalle();
                solicitudDetalle.setId(rs.getInt("id"));
                solicitudDetalle.setCliente(rs.getString("cliente"));
                solicitudDetalle.setCategoria(rs.getString("categoria"));
                solicitudDetalle.setServicio(rs.getString("servicio"));
                solicitudDetalle.setTarifa(rs.getInt("tarifa"));
                solicitudDetalle.setFechaSolicitud(rs.getString("fecha_solicitud"));
                list.add(solicitudDetalle);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
