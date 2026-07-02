package duoc.vinilos6.controller;

import duoc.vinilos6.dto.InventarioDTO;
import duoc.vinilos6.model.Inventario;
import duoc.vinilos6.service.InventarioService;
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
@RequestMapping("/api/inventarios")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService service;

    @Operation(summary = "Obtener lista global del inventario", description = "Retorna las unidades disponibles y " +
                "ubicaciones de almacén de todos los productos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación realizada con éxito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class)))
    })
    @GetMapping
    public ResponseEntity<List<Inventario>> listarInventario() {
        log.info("Petición GET recibida para listar inventario completo");
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @Operation(summary = "Obtener stock de un vinilo por SKU")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro de stock localizado con éxito"),
            @ApiResponse(responseCode = "404", description = "El SKU consultado no cuenta con registros de inventario")
    })
    @GetMapping("/{sku}")
    public ResponseEntity<Inventario> obtenerStockPorSku(@PathVariable String sku) {
        log.info("Petición GET recibida para consultar el SKU: {}", sku);
        try {
            return ResponseEntity.ok(service.obtenerPorSku(sku));
        } catch (RuntimeException e) {
            log.error("Error al procesar la búsqueda de inventario: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Registrar o actualizar stock en bodega")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inventario actualizado/creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada corruptos o fallas en las reglas de validación")
    })
    @PostMapping
    public ResponseEntity<Inventario> registrarOActualizarStock(@Valid @RequestBody InventarioDTO dto) {
        log.info("Petición POST recibida para actualizar/registrar inventario");
        Inventario resultado = service.actualizarORegistrarStock(dto);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }
}