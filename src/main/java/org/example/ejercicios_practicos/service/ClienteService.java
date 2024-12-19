package org.example.ejercicios_practicos.service;

import org.example.ejercicios_practicos.model.Cliente;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ClienteService {

    private List<Cliente> clientes = new ArrayList<>();

    public Mono<Cliente> crearCliente(Cliente cliente) {
        clientes.add(cliente);
        return Mono.just(cliente);
    }

    public Mono<List<Cliente>> obtenerClientes() {
        return Mono.just(clientes);
    }


    public Mono<Cliente> actualizarCliente(String id, Cliente clienteActualizado) {
        // Buscar el cliente por ID
        Cliente clienteExistente = clientes.stream()
                .filter(c -> c.id().equals(id))
                .findFirst()
                .orElse(null);

        if (clienteExistente == null) {
            return Mono.error(new IllegalArgumentException("Cliente no encontrado"));
        }

        Cliente clienteModificado = new Cliente(clienteExistente.id(),
                clienteActualizado.nombres(),
                clienteActualizado.email(),
                clienteActualizado.edad(),
                clienteActualizado.tipoCliente());
        clientes.remove(clienteExistente);
        clientes.add(clienteModificado);

        return Mono.just(clienteModificado);
    }

    public Mono<Void> eliminarCliente(String id) {
        // Buscar el cliente por ID
        Cliente cliente = clientes.stream()
                .filter(c -> c.id().equals(id))
                .findFirst()
                .orElse(null);

        if (cliente == null) {
            return Mono.error(new IllegalArgumentException("Cliente no encontrado"));
        }

        clientes.remove(cliente);
        return Mono.empty();
    }

    public Mono<String> aplicarDescuento(String id) {
        Optional<Cliente> clienteOpt = clientes.stream()
                .filter(c -> c.id().equals(id))
                .findFirst();
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            return switch (cliente.tipoCliente()) {
                case VIP -> Mono.just("Descuento del 20% aplicado a cliente VIP");
                case REGULAR -> Mono.just("Descuento del 5% aplicado a cliente REGULAR");
            };
        } else {
            return Mono.error(new RuntimeException("Cliente no encontrado")); // Devuelve un error si no se encuentra el cliente
        }
    }


}