package com.lavanderia.utp.model;

public class PrendaTipo {
    
    int id;
    String tipo;
    
    public PrendaTipo() {
    }

    public PrendaTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
