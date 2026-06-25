package duoc.vinilos5.controller;

import duoc.vinilos5.dto.EnvioDTO;
import duoc.vinilos5.model.Envio;
import duoc.vinilos5.service.EnvioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/envios")
@RequiredArgsConstructor
public class EnvioController {

    private final EnvioService service;

    @GetMapping
    public ResponseEntity<List<Envio>> listarEnvios() {
        log.info("Peticion GET recibida para listar envios");
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{idEnvio}")
    public ResponseEntity<Envio> obtenerEnvio(@PathVariable Long idEnvio) {
        log.info("Peticion GET recibida para obtener envio: {}", idEnvio);
        return ResponseEntity.ok(service.obtenerPorId(idEnvio));
    }

    @PostMapping
    public ResponseEntity<Envio> registrarEnvio(@Valid @RequestBody EnvioDTO dto) {
        log.info("Peticion POST recibida para registrar envio");
        return new ResponseEntity<>(service.registrarEnvio(dto), HttpStatus.CREATED);
    }
}