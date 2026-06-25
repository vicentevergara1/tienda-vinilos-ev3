package duoc.vinilos3.controller;

import duoc.vinilos3.dto.PagoDTO;
import duoc.vinilos3.model.Pago;
import duoc.vinilos3.service.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService service;

    @GetMapping
    public ResponseEntity<List<Pago>> listarPagos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/cliente/{rut}")
    public ResponseEntity<List<Pago>> listarPorCliente(@PathVariable String rut) {
        return ResponseEntity.ok(service.obtenerPorCliente(rut));
    }

    @PostMapping
    public ResponseEntity<Pago> registrarPago(@Valid @RequestBody PagoDTO dto) {
        log.info("Peticion POST recibida para registrar pago");
        return new ResponseEntity<>(service.procesarPago(dto), HttpStatus.CREATED);
    }
}