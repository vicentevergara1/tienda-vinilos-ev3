package duoc.vinilos4.controller;
import duoc.vinilos4.dto.PedidoDTO;
import duoc.vinilos4.model.Pedido;
import duoc.vinilos4.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService service;

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<Pedido> obtenerPedido(@PathVariable Long idPedido) {
        return ResponseEntity.ok(service.obtenerPorId(idPedido));
    }

    @PostMapping
    public ResponseEntity<Pedido> registrarPedido(@Valid @RequestBody PedidoDTO dto) {
        return new ResponseEntity<>(service.registrarPedido(dto), HttpStatus.CREATED);
    }
}