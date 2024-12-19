package org.example.ejercicios_practicos.controller;

import org.example.ejercicios_practicos.model.Cliente;
import org.example.ejercicios_practicos.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {


    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public Mono<ResponseEntity<Cliente>> crearCliente(@RequestBody Cliente cliente) {
        return clienteService.crearCliente(cliente)
                .map(c -> ResponseEntity.status(HttpStatus.CREATED).body(c));
    }

    @GetMapping
    public Mono<List<Cliente>> obtenerClientes() {
        return clienteService.obtenerClientes();
    }

    @PutMapping("/{id}")
    public Mono<Cliente> actualizarCliente(@PathVariable("id") String id, @RequestBody Cliente cliente) {
        return clienteService.actualizarCliente(id, cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> eliminarCliente(@PathVariable("id") String id) {
        return clienteService.eliminarCliente(id);
    }

    @GetMapping("/descuento/{id}")
    public Mono<String> aplicarDescuento(@PathVariable("id") String id) {
        return clienteService.aplicarDescuento(id);
    }
}
