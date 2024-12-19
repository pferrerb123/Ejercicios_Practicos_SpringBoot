package org.example.ejercicios_practicos.controller;

import org.example.ejercicios_practicos.model.EstadoDto;
import org.example.ejercicios_practicos.model.Prestamo;
import org.example.ejercicios_practicos.model.TipoCliente;
import org.example.ejercicios_practicos.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Prestamo> crearPrestamo(@RequestBody Prestamo prestamo) {
        return prestamoService.crearPrestamo(prestamo);
    }

    @GetMapping("/activos")
    public Prestamo obtenerPrestamosActivos() {
        return prestamoService.obtenerPrestamosActivos(null);
    }

    @PutMapping("/{id}")
    public Mono<Prestamo> actualizarEstadoPrestamo(@PathVariable("id") String id
            , @RequestBody EstadoDto nuevoEstado
    ) {
        System.out.println("Invocando PUT para actualizar estado con ID: " + id);
        return prestamoService.actualizarEstadoPrestamo(id, nuevoEstado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> eliminarPrestamo(@PathVariable("id") String id) {
        return prestamoService.eliminarPrestamo(id);
    }

    // Calcular monto total a pagar con interés
    @GetMapping("/montoTotal/{id}/{tipoCliente}")
    public Mono<Double> calcularMontoTotal(@PathVariable("id") String id, @PathVariable("tipoCliente") TipoCliente tipoCliente) {
        Prestamo prestamo = prestamoService.obtenerPrestamosActivos(id);

        if (prestamo != null) {
            double montoTotal = prestamoService.calcularMontoTotal(prestamo, tipoCliente);
            return Mono.just(montoTotal);
        } else {
            return Mono.error(new IllegalArgumentException("Préstamo no encontrado"));
        }
    }
}