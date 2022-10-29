package com.lavanderia.utp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lavanderia.utp.utils.DBConnection;
import java.sql.SQLException;

import com.lavanderia.utp.model.Servicio;
import java.util.ArrayList;
import java.util.List;
import com.lavanderia.utp.interfaces.GenericInterface;

public class ServicioDAO implements GenericInterface<Servicio> {

    static Connection con = DBConnection.getConnection();
    static PreparedStatement ps;
    static ResultSet rs;

    @Override
    public List<Servicio> getAll() {
        List<Servicio> list = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT s.id, s.nombre, s.categoria_id, s.tarifa, s.activo, c.nombre as categoria FROM servicios s JOIN categorias c on c.id = s.categoria_id ORDER BY s.nombre");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Servicio servicio = new Servicio();
                servicio.setId(rs.getInt("id"));
                servicio.setNombre(rs.getString("nombre"));
                servicio.setCategoriaId(rs.getInt("categoria_id"));
                servicio.setTarifa(rs.getInt("tarifa"));
                servicio.setCategoria(rs.getString("categoria"));
                servicio.setActivo(rs.getBoolean("activo"));
                list.add(servicio);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public void add(Servicio servicio) {
        String sql = "INSERT INTO servicios (nombre, categoria_id, tarifa) VALUES ('" + servicio.getNombre() + "', " + servicio.getCategoriaId() + ", " + servicio.getTarifa() + ")";
        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Servicio getById(int id) {
        Servicio servicio = new Servicio();
        try {
            ps = con.prepareStatement("SELECT * FROM servicios WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                servicio.setId(rs.getInt("id"));
                servicio.setNombre(rs.getString("nombre"));
                servicio.setCategoriaId(rs.getInt("categoria_id"));
                servicio.setTarifa(rs.getInt("tarifa"));
                servicio.setActivo(rs.getBoolean("activo"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return servicio;
    }

    @Override
    public void update(Servicio servicio) {
        String sql = "UPDATE servicios set nombre = '" + servicio.getNombre() + "', categoria_id = " + servicio.getCategoriaId() + ", tarifa = " + servicio.getTarifa() + ", activo = " + servicio.getActivo() + " WHERE id = " + servicio.getId();
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM servicios WHERE id = " + id;
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Servicio> search(String searchText) {
        return null;
    }
}
