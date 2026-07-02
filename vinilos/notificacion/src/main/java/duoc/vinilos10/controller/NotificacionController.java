package duoc.vinilos10.controller;

import duoc.vinilos10.dto.NotificacionDTO;
import duoc.vinilos10.model.Notificacion;
import duoc.vinilos10.service.NotificacionService;
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
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService service;

    @Operation(summary = "Listar todas las notificaciones", description = "Retorna el historial de auditoría global de envíos de la plataforma.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Notificacion.class)))
    })
    @GetMapping
    public ResponseEntity<List<Notificacion>> listarNotificaciones() {
        log.info("Petición GET recibida para listar todas las notificaciones");
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @Operation(summary = "Obtener una notificación por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificación localizada de manera exitosa"),
            @ApiResponse(responseCode = "404", description = "El ID especificado no corresponde a ningún registro")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtenerNotificacionPorId(@PathVariable Long id) {
        log.info("Petición GET recibida para consultar la notificación ID: {}", id);
        try {
            return ResponseEntity.ok(service.obtenerPorId(id));
        } catch (RuntimeException e) {
            log.error("Error al buscar notificación: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Listar las alertas asociadas a un correo o contacto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de correspondencia retornado correctamente")
    })
    @GetMapping("/destinatario")
    public ResponseEntity<List<Notificacion>> listarPorDestinatario(@RequestParam String correo) {
        log.info("Petición GET recibida para listar mensajes enviados a: {}", correo);
        return ResponseEntity.ok(service.obtenerPorDestinatario(correo));
    }

    @Operation(summary = "Crear y despachar una nueva alerta", description = "Recibe los datos, los procesa a través del motor simulado y guarda el estado del envío.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Notificación registrada y enviada con éxito"),
            @ApiResponse(responseCode = "400", description = "Esquema o datos del DTO inválidos")
    })
    @PostMapping
    public ResponseEntity<Notificacion> enviarNotificacion(@Valid @RequestBody NotificacionDTO dto) {
        log.info("Petición POST recibida para procesar nueva alerta de cliente");
        Notificacion resultado = service.registrarYEnviar(dto);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }
}
