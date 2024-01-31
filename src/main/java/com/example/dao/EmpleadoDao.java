package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Empleado;
import java.util.List;


@Repository // Para crear beans de datos
//para que se sepa que aquí hay beans

public interface EmpleadoDao extends JpaRepository<Empleado, Integer> {
    List<Empleado> findByNombre(String nombre);


}
