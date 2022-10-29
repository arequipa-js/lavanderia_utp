package com.lavanderia.utp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lavanderia.utp.utils.DBConnection;
import java.sql.SQLException;

import com.lavanderia.utp.model.PrendaTipo;
import java.util.ArrayList;
import java.util.List;
import com.lavanderia.utp.interfaces.GenericInterface;

public class PrendaTipoDAO implements GenericInterface<PrendaTipo> {

    static Connection con = DBConnection.getConnection();
    static PreparedStatement ps;
    static ResultSet rs;

    @Override
    public List<PrendaTipo> getAll() {
        List<PrendaTipo> list = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT * FROM prenda_tipos ORDER BY tipo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PrendaTipo prendaTipo = new PrendaTipo();
                prendaTipo.setId(rs.getInt("id"));
                prendaTipo.setTipo(rs.getString("tipo"));
                list.add(prendaTipo);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    @Override
    public void add(PrendaTipo prenda) {
    }
    
    @Override
    public PrendaTipo getById(int id) {
        return null;
    }

    @Override
    public void update(PrendaTipo prenda) {
    }
    
    @Override
    public void delete(int id) {
    }
    
    @Override
    public List<PrendaTipo> search(String searchText) {
        return null;
    }
}
