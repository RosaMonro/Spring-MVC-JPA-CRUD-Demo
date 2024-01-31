package com.example.services;

import java.util.List;

import com.example.entities.Telefono;

public interface TelefonoService {

    public List<Telefono> telefonos(int idEmpleado);
    public void eliminarTelefonos(int idEmpleado); // aunque esto luego se puede hacer con el cascadeo
    public void persistirTelefono(int idEmpleado, Telefono telefono); //le pasamos el teléfono que vamos a guardar

}
