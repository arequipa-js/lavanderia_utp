package com.lavanderia.utp.model;

public class Prenda {
    
    int id;
    int persona_id;
    int tipo_id;
    int color_id;
    int material_id;
    int estado_id;
    String marca;
    Float peso;
    String observaciones;
    Float costo;
    int cantidad;
    String cliente;
    String color;
    String tipo;
    String material;
    String estado;
    
    public Prenda() {
    }

    public Prenda(int persona_id, int tipo_id, int color_id, int material_id, int estado_id, String marca, Float peso, String observaciones, Float costo, int cantidad) {
        this.persona_id = persona_id;
        this.tipo_id = tipo_id;
        this.color_id = color_id;
        this.material_id = material_id;
        this.estado_id = estado_id;
        this.marca = marca;
        this.peso = peso;
        this.observaciones = observaciones;
        this.costo = costo;
        this.cantidad = cantidad;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonaId() {
        return persona_id;
    }

    public void setPersonaId(int persona_id) {
        this.persona_id = persona_id;
    }

    public int getTipoId() {
        return tipo_id;
    }

    public void setTipoId(int tipo_id) {
        this.tipo_id = tipo_id;
    }

    public int getColorId() {
        return color_id;
    }

    public void setColorId(int color_id) {
        this.color_id = color_id;
    }

    public int getMaterialId() {
        return material_id;
    }

    public void setMaterialId(int material_id) {
        this.material_id = material_id;
    }

    public int getEstadoId() {
        return estado_id;
    }

    public void setEstadoId(int estado_id) {
        this.estado_id = estado_id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    } 

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
   
}
