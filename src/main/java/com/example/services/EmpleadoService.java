package com.example.services;

import java.util.List;

import com.example.entities.Empleado;

public interface EmpleadoService {

    public List<Empleado> dameTodosLosEmpleados();
    public Empleado dameUnEmpleado(int idEmpleado);
    public void eliminarEmpleado(int idEmpleado);
    public void persistirEmpleado(Empleado empleado); //le pasamos el empleado que vamos a guardar
    public void actualizarEmpleado(Empleado empleado);

}
