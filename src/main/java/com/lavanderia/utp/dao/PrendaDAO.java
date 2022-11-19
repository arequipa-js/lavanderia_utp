package com.lavanderia.utp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lavanderia.utp.utils.DBConnection;
import java.sql.SQLException;

import com.lavanderia.utp.model.Prenda;
import java.util.ArrayList;
import java.util.List;
import com.lavanderia.utp.interfaces.GenericInterface;

public class PrendaDAO implements GenericInterface<Prenda> {

    static Connection con = DBConnection.getConnection();
    static PreparedStatement ps;
    static ResultSet rs;

    @Override
    public List<Prenda> getAll() {
        List<Prenda> list = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT p.id, p.persona_id, p.tipo_id, p.color_id, p.material_id, p.estado_id, p.marca, p.peso,  p.observaciones, p.cantidad, CONCAT(per.nombres, ' ', per.apellidos) AS cliente, pc.color, pt.tipo, pm.material, pe.estado FROM prendas p JOIN personas per on per.id = p.persona_id JOIN prenda_colores pc on pc.id = p.color_id JOIN prenda_tipos pt on pt.id = p.tipo_id JOIN prenda_materiales pm on pm.id = p.material_id JOIN prenda_estados pe on pe.id = p.estado_id ORDER BY p.id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prenda prenda = new Prenda();
                prenda.setId(rs.getInt("id"));
                prenda.setPersonaId(rs.getInt("persona_id"));
                prenda.setTipoId(rs.getInt("tipo_id"));
                prenda.setColorId(rs.getInt("color_id"));
                prenda.setMaterialId(rs.getInt("material_id"));
                prenda.setEstadoId(rs.getInt("estado_id"));
                prenda.setMarca(rs.getString("marca"));
                prenda.setPeso(rs.getFloat("peso"));
                prenda.setObservaciones(rs.getString("observaciones"));
                prenda.setCantidad(rs.getInt("cantidad"));
                prenda.setCliente(rs.getString("cliente"));
                prenda.setColor(rs.getString("color"));
                prenda.setTipo(rs.getString("tipo"));
                prenda.setMaterial(rs.getString("material"));
                prenda.setEstado(rs.getString("estado"));
                list.add(prenda);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Prenda> getByClienteId(int clienteId) {
        List<Prenda> list = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT p.id, p.persona_id, p.tipo_id, p.color_id, p.material_id, p.estado_id, p.marca, p.peso,  p.observaciones, p.cantidad, CONCAT(per.nombres, ' ', per.apellidos) AS cliente, pc.color, pt.tipo, pm.material, pe.estado FROM prendas p JOIN personas per on per.id = p.persona_id JOIN prenda_colores pc on pc.id = p.color_id JOIN prenda_tipos pt on pt.id = p.tipo_id JOIN prenda_materiales pm on pm.id = p.material_id JOIN prenda_estados pe on pe.id = p.estado_id WHERE per.id = ? ORDER BY p.id");
            ps.setInt(1, clienteId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prenda prenda = new Prenda();
                prenda.setId(rs.getInt("id"));
                prenda.setPersonaId(rs.getInt("persona_id"));
                prenda.setTipoId(rs.getInt("tipo_id"));
                prenda.setColorId(rs.getInt("color_id"));
                prenda.setMaterialId(rs.getInt("material_id"));
                prenda.setEstadoId(rs.getInt("estado_id"));
                prenda.setMarca(rs.getString("marca"));
                prenda.setPeso(rs.getFloat("peso"));
                prenda.setObservaciones(rs.getString("observaciones"));
                prenda.setCantidad(rs.getInt("cantidad"));
                prenda.setCliente(rs.getString("cliente"));
                prenda.setColor(rs.getString("color"));
                prenda.setTipo(rs.getString("tipo"));
                prenda.setMaterial(rs.getString("material"));
                prenda.setEstado(rs.getString("estado"));
                list.add(prenda);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public void add(Prenda prenda) {
        String sql = "INSERT INTO prendas (persona_id, tipo_id, color_id, material_id, marca, estado_id, peso, observaciones, cantidad) VALUES (" + prenda.getPersonaId() + ", " + prenda.getTipoId() + ", " + prenda.getColorId() + ", " + prenda.getMaterialId() + ", '" + prenda.getMarca() + "', " + prenda.getEstadoId() + ", " + prenda.getPeso() + ", '" + prenda.getObservaciones() + "', " + prenda.getCantidad() + ")";
        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Prenda getById(int id) {
        Prenda prenda = new Prenda();
        try {
            ps = con.prepareStatement("SELECT * FROM prendas WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                prenda.setId(rs.getInt("id"));
                prenda.setPersonaId(rs.getInt("persona_id"));
                prenda.setTipoId(rs.getInt("tipo_id"));
                prenda.setColorId(rs.getInt("color_id"));
                prenda.setMaterialId(rs.getInt("material_id"));
                prenda.setEstadoId(rs.getInt("estado_id"));
                prenda.setMarca(rs.getString("marca"));
                prenda.setPeso(rs.getFloat("peso"));
                prenda.setObservaciones(rs.getString("observaciones"));
                prenda.setCantidad(rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return prenda;
    }

    @Override
    public void update(Prenda prenda) {
        String sql = "UPDATE prendas set persona_id = " + prenda.getPersonaId() + ", tipo_id = " + prenda.getTipoId() + ", color_id = " + prenda.getColorId() + ", material_id = " + prenda.getMaterialId() + ", estado_id = " + prenda.getEstadoId() + ", marca = '" + prenda.getMarca() + "', peso = " + prenda.getPeso() + ", observaciones = '" + prenda.getObservaciones() + "', cantidad = " + prenda.getCantidad() + " WHERE id = " + prenda.getId();
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM prendas WHERE id = " + id;
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Prenda> search(String searchText) {
        return null;
    }
}
