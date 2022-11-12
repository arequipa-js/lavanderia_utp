package com.lavanderia.utp.interfaces;

import com.lavanderia.utp.model.SolicitudDetalle;
import java.util.List;


public interface SolicitudDetalleInterface {
    public List<SolicitudDetalle> getAll();
    public List<SolicitudDetalle> search(String searchText);
    public void add(SolicitudDetalle data);
    public List<SolicitudDetalle> getBySolicitudId(int id);
    public void update(SolicitudDetalle data);
    public void delete(int id);
}
