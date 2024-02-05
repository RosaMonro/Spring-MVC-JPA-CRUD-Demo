package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Correo;
import com.example.entities.Empleado;

@Repository

public interface CorreoDao extends JpaRepository <Correo, Integer> {
    List<Correo> findByEmpleado(Empleado empleado); // generamos este método porq este no lo genera el DAO.
    void deleteByEmpleado(Empleado empleado);

}
