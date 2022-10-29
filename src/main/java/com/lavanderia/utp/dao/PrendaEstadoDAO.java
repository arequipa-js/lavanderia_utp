package com.lavanderia.utp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lavanderia.utp.utils.DBConnection;
import java.sql.SQLException;

import com.lavanderia.utp.model.PrendaEstado;
import java.util.ArrayList;
import java.util.List;
import com.lavanderia.utp.interfaces.GenericInterface;

public class PrendaEstadoDAO implements GenericInterface<PrendaEstado> {

    static Connection con = DBConnection.getConnection();
    static PreparedStatement ps;
    static ResultSet rs;

    @Override
    public List<PrendaEstado> getAll() {
        List<PrendaEstado> list = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT * FROM prenda_estados ORDER BY estado");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PrendaEstado prendaEstado = new PrendaEstado();
                prendaEstado.setId(rs.getInt("id"));
                prendaEstado.setEstado(rs.getString("estado"));
                list.add(prendaEstado);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    @Override
    public void add(PrendaEstado prenda) {
    }
    
    @Override
    public PrendaEstado getById(int id) {
        return null;
    }

    @Override
    public void update(PrendaEstado prenda) {
    }
    
    @Override
    public void delete(int id) {
    }
    
    @Override
    public List<PrendaEstado> search(String searchText) {
        return null;
    }
}
