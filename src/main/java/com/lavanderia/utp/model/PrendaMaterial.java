package com.lavanderia.utp.model;

public class PrendaMaterial {
    
    int id;
    String material;
    
    public PrendaMaterial() {
    }

    public PrendaMaterial(String material) {
        this.material = material;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
    
}
