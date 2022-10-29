package com.lavanderia.utp.model;

public class PrendaEstado {
    
    int id;
    String estado;
    
    public PrendaEstado() {
    }

    public PrendaEstado(String estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
