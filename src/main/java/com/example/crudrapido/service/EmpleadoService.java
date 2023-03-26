package com.example.crudrapido.service;

import com.example.crudrapido.entity.Empleado;
import com.example.crudrapido.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    public void save(Empleado empleado){
        empleadoRepository.save(empleado);
    }

}
