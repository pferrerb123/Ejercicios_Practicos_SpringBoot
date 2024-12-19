package org.example.ejercicios_practicos.service;

import org.example.ejercicios_practicos.model.EstadoDto;
import org.example.ejercicios_practicos.model.Prestamo;
import org.example.ejercicios_practicos.model.TipoCliente;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestamoService {

    private final List<Prestamo> prestamos = new ArrayList<>();

    public Mono<Prestamo> crearPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
        return Mono.just(prestamo);
    }

    public Prestamo obtenerPrestamosActivos(String id) {
        return prestamos.stream().filter(p -> p.getEstado().equals("PENDIENTE")
                        && (id != null || p.getId().equals(id))).
                findFirst().orElse(null);
    }

    public Mono<Prestamo> actualizarEstadoPrestamo(String id, EstadoDto nuevoEstado) {
        Prestamo prestamo = prestamos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (prestamo == null) {
            return Mono.error(new IllegalArgumentException("Préstamo no encontrado"));
        }

        prestamo.setEstado(nuevoEstado.getEstado());

        return Mono.just(prestamo);
    }

    public Mono<Void> eliminarPrestamo(String id) {
        Prestamo prestamo = prestamos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Préstamo no encontrado"));

        prestamos.remove(prestamo);
        return Mono.empty();
    }

    // Calcular el monto total a pagar (monto + interés)
    public double calcularMontoTotal(Prestamo prestamo, TipoCliente tipoCliente) {
        double tasaInteres = tipoCliente == TipoCliente.VIP ? 0.05 : 0.10;
        return prestamo.getMonto() * (1 + tasaInteres);
    }

}
