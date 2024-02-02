package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.DepartamentoDao;
import com.example.entities.Departamento;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartamentoServiceImpl implements DepartamentoService {

    // Hacemos inyección de dependencia por constructor (es más eficiente) en vez de @autowired
    private final DepartamentoDao departamentoDao;

    @Override
    public List<Departamento> dameDepartamentos() {
        return departamentoDao.findAll();
    }

    @Override
    public Departamento dameUnDepartamento(int idDepartamento) {
        return departamentoDao.findById(idDepartamento).get();
    }

    @Override
    public void persistirDpto(Departamento departamento) {
        departamentoDao.save(departamento);
    }

}
