package com.lavanderia.utp.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Movilidad {
    
    int id;
    String nombre;
    String marca;
    String modelo;
    int chofer_id;
    boolean disponible;
    Date fecha_creacion;
    String chofer;
    
    public Movilidad() {
    }

    public Movilidad(String nombre, String marca, String modelo, int chofer_id) {
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.chofer_id = chofer_id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getChoferId() {
        return chofer_id;
    }

    public void setChoferId(int chofer_id) {
        this.chofer_id = chofer_id;
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
    
    public String getChofer() {
        return chofer;
    }

    public void setChofer(String chofer) {
        this.chofer = chofer;
    }
    
}
