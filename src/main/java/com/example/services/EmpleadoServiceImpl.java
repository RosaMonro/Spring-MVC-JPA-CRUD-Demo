package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.EmpleadoDao;
import com.example.entities.Empleado;

@Service 
// @Repository proporciona datos
// @service proporciona servicios
// ambos generan beans

public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired // va a buscar un bean de tipo empleado
    private EmpleadoDao empleadoDao;


    @Override
    public List<Empleado> dameTodosLosEmpleados() {
        return empleadoDao.findAll();
    }

    @Override
    public Empleado dameUnEmpleado(int idEmpleado) {
        return empleadoDao.findById(idEmpleado).get();
    }

    @Override
    public void eliminaEmpleado(int idEmpleado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminaEmpleado'");
    }

    @Override
    public void persistirEmpleado(Empleado empleado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'persistirEmpleado'");
    }

    @Override
    public void actualizarEmpleado(Empleado empleado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarEmpleado'");
    }



}
