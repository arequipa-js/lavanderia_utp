package com.lavanderia.utp.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SolicitudDetalle {
    
    int id;
    int solicitud_id;
    int servicio_id;
    int prenda_id;
    String observaciones;
    String cliente;
    String servicio;
    int tarifa;
    int cantidad;
    Date fecha_creacion;
    
    public SolicitudDetalle() {
    }

    public SolicitudDetalle(int solicitud_id, int servicio_id, int prenda_id, String observaciones) {
        this.solicitud_id = solicitud_id;
        this.servicio_id = servicio_id;
        this.prenda_id = prenda_id;
        this.observaciones = observaciones;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSolicitudId() {
        return solicitud_id;
    }

    public void setSolicitudId(int solicitud_id) {
        this.solicitud_id = solicitud_id;
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

    public int getTarifa() {
        return tarifa;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
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
