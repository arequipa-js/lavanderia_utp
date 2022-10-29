package com.lavanderia.utp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lavanderia.utp.utils.DBConnection;
import java.sql.SQLException;

import com.lavanderia.utp.model.Distrito;
import java.util.ArrayList;
import java.util.List;
import com.lavanderia.utp.interfaces.GenericInterface;

public class DistritoDAO implements GenericInterface<Distrito> {

    static Connection con = DBConnection.getConnection();
    static PreparedStatement ps;
    static ResultSet rs;

    @Override
    public List<Distrito> getAll() {
        List<Distrito> list = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT * FROM distritos ORDER BY distrito");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Distrito distrito = new Distrito();
                distrito.setId(rs.getInt("id"));
                distrito.setDistrito(rs.getString("distrito"));
                list.add(distrito);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    @Override
    public void add(Distrito distrito) {
    }
    
    @Override
    public Distrito getById(int id) {
        return null;
    }

    @Override
    public void update(Distrito prenda) {
    }
    
    @Override
    public void delete(int id) {
    }
    
    @Override
    public List<Distrito> search(String searchText) {
        return null;
    }
}
