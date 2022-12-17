package com.lavanderia.utp.interfaces;

import com.lavanderia.utp.model.Persona;
import java.util.List;


public interface PersonaInterface {
    public List<Persona> getPersonas(char tipo, boolean filtrarActivos);
    public List<Persona> search(String searchText);
    public void add(Persona persona);
    public Persona getById(int id);
    public void update(Persona persona);
    public void delete(int id);
    public Persona login(String email, String password);
    public void addAdmin(Persona persona);
    public void updateAdmin(Persona persona);
}
