package com.lavanderia.utp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lavanderia.utp.utils.DBConnection;
import java.sql.SQLException;

import com.lavanderia.utp.model.Persona;
import java.util.ArrayList;
import java.util.List;
import com.lavanderia.utp.interfaces.PersonaInterface;
import com.lavanderia.utp.utils.Common;
import com.lavanderia.utp.utils.EmailService;
import com.lavanderia.utp.utils.Functions;

public class PersonaDAO implements PersonaInterface {

    static Connection con = DBConnection.getConnection();
    static PreparedStatement ps;
    static ResultSet rs;

    @Override
    public List<Persona> getPersonas(char tipo, boolean filtrarActivos) {
        List<Persona> list = new ArrayList<>();
        try {
            String sql = "SELECT p.id, nombres, apellidos, dni, distrito, distrito_id, direccion, email, telefono, sexo, fecha_creacion, p.activo FROM PERSONAS p LEFT JOIN DISTRITOS d on d.id = p.distrito_id WHERE tipo_persona = '" + tipo + "'";
            if (filtrarActivos) {
                sql += " AND p.activo = true";
            }
            sql += " ORDER BY p.id";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setId(rs.getInt("id"));
                persona.setNombres(rs.getString("nombres"));
                persona.setApellidos(rs.getString("apellidos"));
                persona.setDni(rs.getInt("dni"));
                persona.setDistrito(rs.getString("distrito"));
                persona.setDistritoId(rs.getInt("distrito_id"));
                persona.setDireccion(rs.getString("direccion"));
                persona.setEmail(rs.getString("email"));
                persona.setTelefono(rs.getInt("telefono"));
                if (rs.getString("sexo") != null) {
                    persona.setSexo(rs.getString("sexo").charAt(0));
                }
                persona.setFechaCreacion(rs.getDate("fecha_creacion"));
                persona.setActivo(rs.getBoolean("activo"));
                list.add(persona);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public List<Persona> search(String searchText) {
        List<Persona> list = new ArrayList<>();
        int dni = Functions.toInteger(searchText);
        String sql = "SELECT p.id, nombres, apellidos, dni, distrito, distrito_id, direccion, email, telefono, sexo, fecha_creacion, p.activo FROM PERSONAS p LEFT JOIN DISTRITOS d on d.id = p.distrito_id WHERE (nombres ILIKE " + "'%" + searchText + "%'" + " OR apellidos ILIKE " + "'%" + searchText + "%'" + " OR DNI = " + dni + ") AND tipo_persona='C'";

        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setId(rs.getInt("id"));
                persona.setNombres(rs.getString("nombres"));
                persona.setApellidos(rs.getString("apellidos"));
                persona.setDni(rs.getInt("dni"));
                persona.setDireccion(rs.getString("direccion"));
                persona.setEmail(rs.getString("email"));
                persona.setTelefono(rs.getInt("telefono"));
                if (rs.getString("sexo") != null) {
                    persona.setSexo(rs.getString("sexo").charAt(0));
                }
                persona.setFechaCreacion(rs.getDate("fecha_creacion"));
                persona.setActivo(rs.getBoolean("activo"));
                list.add(persona);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public void add(Persona persona) {
        String sql = "INSERT INTO personas (nombres, apellidos, dni, distrito_id, direccion, email, telefono, sexo, tipo_persona, password) " + "VALUES ('" + persona.getNombres() + "', '" + persona.getApellidos() + "', " + persona.getDni() + ", " + persona.getDistritoId() + ", '" + persona.getDireccion() + "', '" + persona.getEmail() + "', " + persona.getTelefono() + ", '" + persona.getSexo() + "', 'C', " + "'" + Common.DEFAULT_PASSWORD + "'" + ")";
        System.out.println(sql);

        try {
            String toEmail = persona.getEmail();
            String subject = Common.CLIENTE_ASUNTO;
            String message = "<h1>Estimado(a): " + persona.getNombres() + " " + persona.getApellidos() + "</h1><br>";
            message += Common.CLIENTE_MENSAJE + "<br>";
            message += "<br><br>" + Common.GRACIAS;
            EmailService emailService = new EmailService();
            emailService.sendMail(toEmail, subject, message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Persona getById(int id) {
        Persona persona = new Persona();
        try {
            ps = con.prepareStatement("SELECT * FROM personas WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                persona.setId(rs.getInt("id"));
                persona.setNombres(rs.getString("nombres"));
                persona.setApellidos(rs.getString("apellidos"));
                persona.setDni(rs.getInt("dni"));
                persona.setDistritoId(rs.getInt("distrito_id"));
                persona.setDireccion(rs.getString("direccion"));
                persona.setEmail(rs.getString("email"));
                persona.setTelefono(rs.getInt("telefono"));
                persona.setSexo(rs.getString("sexo").charAt(0));
                persona.setFechaCreacion(rs.getDate("fecha_creacion"));
                persona.setActivo(rs.getBoolean("activo"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return persona;
    }

    @Override
    public void update(Persona persona) {
        int clienteId = Functions.getSessionClienteId();
        boolean activo = clienteId == 0 ? persona.getActivo() : true;
        String sql = "UPDATE personas set nombres = '" + persona.getNombres() +
                "', apellidos = '" + persona.getApellidos() +
                "', dni = " + persona.getDni() +
                ", distrito_id = " + persona.getDistritoId() +
                ", direccion = '" + persona.getDireccion() +
                "', email = '" + persona.getEmail() +
                "', telefono = " + persona.getTelefono() +
                ", sexo = '" + persona.getSexo() +
                "', activo = " + activo +
                " WHERE id = " + persona.getId();
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM personas WHERE id = " + id;
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Persona login(String email, String password) {
        Persona persona = null;
        try {
            ps = con.prepareStatement("SELECT * FROM personas WHERE email=? and password=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            int rowcount = 0;
            if (rs.last()) {
              rowcount = rs.getRow();
              rs.beforeFirst();
            }
            if (rowcount > 0) {
              persona = new Persona();
            }
            while (rs.next()) {
                persona.setId(rs.getInt("id"));
                persona.setNombres(rs.getString("nombres"));
                persona.setApellidos(rs.getString("apellidos"));
                persona.setTipoPersona(rs.getString("tipo_persona").charAt(0));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return persona;
    }

    @Override
    public void addAdmin(Persona persona) {
        String sql = "INSERT INTO personas (email, password, nombres, apellidos, tipo_persona) VALUES ('" + persona.getEmail() + "', '" + persona.getPassword() + "', '" + persona.getNombres() + "', '" + persona.getApellidos() + "', 'A')";
        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAdmin(Persona persona) {
        String sql = "UPDATE personas set email = '" + persona.getEmail() + "', password = '" + persona.getPassword() + "', nombres = '" + persona.getNombres() + "', apellidos = '" + persona.getApellidos() + "' WHERE id = " + persona.getId();
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
