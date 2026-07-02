package duoc.vinilos9.controller;

import duoc.vinilos9.dto.ResenaDTO;
import duoc.vinilos9.model.Resena;
import duoc.vinilos9.service.ResenaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/resena")
@RequiredArgsConstructor
public class ResenaController {

    private final ResenaService service;

    @GetMapping
    public ResponseEntity<List<Resena>> listarResenas() {
        log.info("Peticion GET recibida para listar las resenas");
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resena> obtenerResena(@PathVariable Long id) {
        log.info("Peticion GET recibida para obtener resena numero: {}", id);
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Resena> registrarResena(@Valid @RequestBody ResenaDTO dto) {
        log.info("Peticion POST recibida para subir la resena");
        return new ResponseEntity<>(service.registrarResena(dto), HttpStatus.CREATED);
    }
}