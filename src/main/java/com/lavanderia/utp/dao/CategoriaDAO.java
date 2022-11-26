package com.lavanderia.utp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lavanderia.utp.utils.DBConnection;
import java.sql.SQLException;

import com.lavanderia.utp.model.Categoria;
import java.util.ArrayList;
import java.util.List;
import com.lavanderia.utp.interfaces.GenericInterface;

public class CategoriaDAO implements GenericInterface<Categoria> {

    static Connection con = DBConnection.getConnection();
    static PreparedStatement ps;
    static ResultSet rs;

    @Override
    public List<Categoria> getAll() {
        return null;
    }

    public List<Categoria> getByActivo(boolean filtrarActivos) {
        List<Categoria> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM categorias";
            if (filtrarActivos) {
                sql += " WHERE activo = true";
            }
            sql += " ORDER BY id";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                categoria.setActivo(rs.getBoolean("activo"));
                list.add(categoria);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public void add(Categoria categoria) {
        String sql = "INSERT INTO categorias (nombre, descripcion) VALUES ('" + categoria.getNombre() + "', '" + categoria.getDescripcion() + "')";
        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Categoria getById(int id) {
        Categoria categoria = new Categoria();
        try {
            ps = con.prepareStatement("SELECT * FROM categorias WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                categoria.setActivo(rs.getBoolean("activo"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return categoria;
    }

    @Override
    public void update(Categoria categoria) {
        String sql = "UPDATE categorias set nombre = '" + categoria.getNombre() + "', descripcion = '" + categoria.getDescripcion() + "', activo = " + categoria.getActivo() + " WHERE id = " + categoria.getId();
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
        String sql = "DELETE FROM categorias WHERE id = " + id;
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Categoria> search(String searchText) {
        return null;
    }
}
