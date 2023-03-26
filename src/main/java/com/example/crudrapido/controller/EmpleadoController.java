package com.example.crudrapido.controller;

import com.example.crudrapido.entity.Empleado;
import com.example.crudrapido.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> save(@RequestBody Empleado empleado){
        LocalDate fechaActual = LocalDate.now();
        LocalDate fecha_na = empleado.getNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fecha_vi = empleado.getVinculacion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
        Period diferencia = Period.between(fecha_na, fechaActual);
        Period tiempo = Period.between(fecha_vi, fechaActual);
        int edad= diferencia.getYears();
        int year= tiempo.getYears();
        int months= tiempo.getMonths();
        if (edad<18) {
            String mensaje="El empleado debe ser mayor de edad.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
        } else {
            empleadoService.save(empleado);
            String mensaje="{" +
                    "\"id\": "+empleado.getId()+"," +
                    "\"nombres\": \""+empleado.getNombre()+"\"," +
                    "\"apellidos\": \""+empleado.getApellido()+"\"," +
                    "\"tipo_documento\": \""+empleado.getTipo()+"\"," +
                    "\"numero_documento\": "+empleado.getNumero()+"," +
                    "\"fecha_nacimiento\": \""+empleado.getNacimiento()+"\"," +
                    "\"fecha_vinculacion\": \""+empleado.getVinculacion()+"\"," +
                    "\"cargo\": \""+empleado.getCargo()+"\"," +
                    "\"salario\": "+empleado.getSalario()+"," +
                    "\"tiempo_vinculacion\": {" +
                    "\"anios\": "+year+"," +
                    "\"meses\": "+months+"" +
                    "}," +
                    "\"edad\": {" +
                    "\"anios\": "+diferencia.getYears()+"," +
                    "\"meses\": "+diferencia.getMonths()+"," +
                    "\"dias\": "+diferencia.getDays()+"" +
                    "}" +
                    "}";
            return ResponseEntity.ok(mensaje);
        }
    }
}
