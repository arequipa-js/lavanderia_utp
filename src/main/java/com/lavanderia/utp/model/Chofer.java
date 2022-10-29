package com.lavanderia.utp.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Chofer {
    
    int id;
    String nombres;
    String apellidos;
    String nro_licencia;
    boolean disponible;
    Date fecha_creacion;
    
    public Chofer() {
    }

    public Chofer(String nombres, String apellidos, String nro_licencia) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nro_licencia = nro_licencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNroLicencia() {
        return nro_licencia;
    }

    public void setNroLicencia(String nro_licencia) {
        this.nro_licencia = nro_licencia;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    public String getFechaCreacion() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strDate = dateFormat.format(fecha_creacion);
        return strDate;
    }

    public void setFechaCreacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    } 
    
}
