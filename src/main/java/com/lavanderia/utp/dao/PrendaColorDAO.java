package com.lavanderia.utp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lavanderia.utp.utils.DBConnection;
import java.sql.SQLException;

import com.lavanderia.utp.model.PrendaColor;
import java.util.ArrayList;
import java.util.List;
import com.lavanderia.utp.interfaces.GenericInterface;

public class PrendaColorDAO implements GenericInterface<PrendaColor> {

    static Connection con = DBConnection.getConnection();
    static PreparedStatement ps;
    static ResultSet rs;

    @Override
    public List<PrendaColor> getAll() {
        List<PrendaColor> list = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT * FROM prenda_colores ORDER BY color");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PrendaColor prendaColor = new PrendaColor();
                prendaColor.setId(rs.getInt("id"));
                prendaColor.setColor(rs.getString("color"));
                list.add(prendaColor);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    @Override
    public void add(PrendaColor prenda) {
    }
    
    @Override
    public PrendaColor getById(int id) {
        return null;
    }

    @Override
    public void update(PrendaColor prenda) {
    }
    
    @Override
    public void delete(int id) {
    }
    
    @Override
    public List<PrendaColor> search(String searchText) {
        return null;
    }
}
