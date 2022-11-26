package com.lavanderia.utp.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Solicitud {
    
    int id;
    int persona_id;
    Date fecha_creacion;
    String cliente;
    String fecha_solicitud;
    int servicio_id;
    int prenda_id;
    String observaciones;
    char estado;

    public Solicitud() {
    }

    public Solicitud(int persona_id) {
        this.persona_id = persona_id;
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

    public String getFechaSolicitud() {
        return fecha_solicitud;
    }

    public void setFechaSolicitud(String fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }

    public int getServicioId() {
        return servicio_id;
    }

    public void setServicioId(int servicio_id) {
        this.servicio_id = servicio_id;
    }

    public int getPrendaId() {
        return prenda_id;
    }

    public void setPrendaId(int prenda_id) {
        this.prenda_id = prenda_id;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public String getFechaCreacion() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strDate = dateFormat.format(fecha_creacion);
        return strDate;
    }

    public void setFechaCreacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    } 

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }
}
