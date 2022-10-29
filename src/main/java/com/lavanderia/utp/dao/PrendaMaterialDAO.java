package com.lavanderia.utp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lavanderia.utp.utils.DBConnection;
import java.sql.SQLException;

import com.lavanderia.utp.model.PrendaMaterial;
import java.util.ArrayList;
import java.util.List;
import com.lavanderia.utp.interfaces.GenericInterface;

public class PrendaMaterialDAO implements GenericInterface<PrendaMaterial> {

    static Connection con = DBConnection.getConnection();
    static PreparedStatement ps;
    static ResultSet rs;

    @Override
    public List<PrendaMaterial> getAll() {
        List<PrendaMaterial> list = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT * FROM prenda_materiales ORDER BY material");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PrendaMaterial prendaMaterial = new PrendaMaterial();
                prendaMaterial.setId(rs.getInt("id"));
                prendaMaterial.setMaterial(rs.getString("material"));
                list.add(prendaMaterial);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    @Override
    public void add(PrendaMaterial prenda) {
    }
    
    @Override
    public PrendaMaterial getById(int id) {
        return null;
    }

    @Override
    public void update(PrendaMaterial prenda) {
    }
    
    @Override
    public void delete(int id) {
    }
    
    @Override
    public List<PrendaMaterial> search(String searchText) {
        return null;
    }
}
