package com.example.services;

import java.util.List;

import com.example.entities.Departamento;

public interface DepartamentoService {

    public List<Departamento> dameDepartamento();
    public Departamento dameUnDepartamento(int idDepartamento);

}
