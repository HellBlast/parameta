package com.example.crudrapido.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private Date nacimiento;

    @Column(nullable = false)
    private Date vinculacion;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    private Double salario;
}
