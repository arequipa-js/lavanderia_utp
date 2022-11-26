package com.lavanderia.utp.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Visita {
    
    int id;
    int movilidad_id;
    String fecha_recojo;
    String hora_recojo;
    int solicitud_id;
    String cliente;
    String movilidad;
    char estado;

    public Visita() {
    }

    public Visita(int movilidad_id, String fecha_recojo, String hora_recojo, int solicitud_id) {
        this.movilidad_id = movilidad_id;
        this.fecha_recojo = fecha_recojo;
        this.hora_recojo = hora_recojo;
        this.solicitud_id = solicitud_id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovilidadId() {
        return movilidad_id;
    }

    public void setMovilidadId(int movilidad_id) {
        this.movilidad_id = movilidad_id;
    }

    public String getFechaRecojo() {
        return fecha_recojo;
    }

    public void setFechaRecojo(String fecha_recojo) {
        this.fecha_recojo = fecha_recojo;
    }

    public String getHoraRecojo() {
        return hora_recojo;
    }

    public void setHoraRecojo(String hora_recojo) {
        this.hora_recojo = hora_recojo;
    }

    public int getSolicitudId() {
        return solicitud_id;
    }

    public void setSolicitudId(int solicitud_id) {
        this.solicitud_id = solicitud_id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMovilidad() {
        return movilidad;
    }

    public void setMovilidad(String movilidad) {
        this.movilidad = movilidad;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }
}
