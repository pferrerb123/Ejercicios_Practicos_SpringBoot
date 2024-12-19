package org.example.ejercicios_practicos.model;

import lombok.*;

import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Prestamo {

    private String id;
    private double monto;
    private String clienteId;
    private LocalDate fecha;
    private String estado;

}