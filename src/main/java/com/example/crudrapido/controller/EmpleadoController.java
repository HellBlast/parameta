package com.example.crudrapido.controller;

import com.example.crudrapido.entity.Empleado;
import com.example.crudrapido.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

@RestController
@RequestMapping(path = "api/v1/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping
    public String save(@RequestBody Empleado empleado){
        LocalDate fechaActual = LocalDate.now();
        LocalDate fecha_na = empleado.getNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fecha_vi = empleado.getVinculacion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
        Period diferencia = Period.between(fecha_na, fechaActual);
        Period tiempo = Period.between(fecha_vi, fechaActual);
        int edad= diferencia.getYears();
        int year= tiempo.getYears();
        int months= tiempo.getMonths();
        if (edad<18) {
            return "El empleado debe ser mayor de edad.";
        } else {
            empleadoService.save(empleado);
            String mensaje="Id:"+empleado.getId()+"\n" +
                    "Nombres:"+empleado.getNombre()+"\n" +
                    "Apellidos:"+empleado.getApellido()+"\n" +
                    "Tipo de Documento:"+empleado.getTipo()+"\n" +
                    "Numero de Documento:"+empleado.getNumero()+"\n" +
                    "Fecha de Nacimiento:"+empleado.getNacimiento()+"\n" +
                    "fecha de Vinculacion:"+empleado.getVinculacion()+"\n" +
                    "Cargo:"+empleado.getCargo()+"\n" +
                    "Salario:"+empleado.getSalario()+"\n";
            mensaje+="Timpo de vinculacion a la compañia (años:"+year+", meses:"+months+").\n";
            mensaje+="Edad actual del empleado(años: "+diferencia.getYears()+", meses: "+diferencia.getMonths()+", dìas: "+diferencia.getDays();
            return mensaje;
        }
    }
}
