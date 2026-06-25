package duoc.vinilos2.controller;

import duoc.vinilos2.dto.ProductoDTO;
import duoc.vinilos2.model.Producto;
import duoc.vinilos2.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService service;

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        log.info("Peticion GET recibida para listar inventario");
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{sku}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable String sku) {
        log.info("Peticion GET recibida para obtener producto: {}", sku);
        return ResponseEntity.ok(service.obtenerPorSku(sku));
    }

    @PostMapping
    public ResponseEntity<Producto> registrarProducto(@Valid @RequestBody ProductoDTO dto) {
        log.info("Peticion POST recibida para registrar producto en inventario");
        return new ResponseEntity<>(service.registrarProducto(dto), HttpStatus.CREATED);
    }
}