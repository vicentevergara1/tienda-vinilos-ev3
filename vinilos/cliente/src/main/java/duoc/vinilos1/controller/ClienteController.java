package duoc.vinilos1.controller;

import duoc.vinilos1.dto.ClienteDTO;
import duoc.vinilos1.model.Cliente;
import duoc.vinilos1.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @Operation(summary = "Obtener lista de clientes", description = "Retorna todos los clientes registrados en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)))
    })
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        log.info("Peticion GET recibida para listar clientes");
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @Operation(summary = "Obtener cliente por RUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{rut}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable String rut) {
        log.info("Peticion GET recibida para obtener cliente: {}", rut);
        return ResponseEntity.ok(service.obtenerPorRut(rut));
    }

    @Operation(summary = "Crear nuevo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos")
    })
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody ClienteDTO dto) {
        log.info("Peticion POST recibida para crear cliente");
        return new ResponseEntity<>(service.crearCliente(dto), HttpStatus.CREATED);
    }
}