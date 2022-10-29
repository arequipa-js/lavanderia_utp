package com.lavanderia.utp.model;

public class Servicio {
    
    int id;
    String nombre;
    int categoria_id;
    String categoria;
    boolean activo;
    int tarifa;
    
    public Servicio() {
    }

    public Servicio(String nombre, int categoria_id) {
        this.nombre = nombre;
        this.categoria_id = categoria_id;
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
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCategoriaId() {
        return categoria_id;
    }

    public void setCategoriaId(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public int getTarifa() {
        return tarifa;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }
    
    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
