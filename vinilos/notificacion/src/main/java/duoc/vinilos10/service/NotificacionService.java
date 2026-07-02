package duoc.vinilos10.service;

import duoc.vinilos10.dto.NotificacionDTO;
import duoc.vinilos10.model.Notificacion;
import duoc.vinilos10.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificacionService {

    private final NotificacionRepository repository;

    @Transactional(readOnly = true)
    public List<Notificacion> obtenerTodas() {
        log.info("Buscando historial completo de notificaciones enviadas y pendientes");
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Notificacion obtenerPorId(Long id) {
        log.info("Buscando registro de notificación con ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la notificación con el ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<Notificacion> obtenerPorDestinatario(String destinatario) {
        log.info("Filtrando historial de alertas para el destinatario: {}", destinatario);
        return repository.findByDestinatario(destinatario);
    }

    @Transactional
    public Notificacion registrarYEnviar(NotificacionDTO dto) {
        log.info("Registrando nueva solicitud de notificación tipo {} para {}", dto.getTipoNotificacion(), dto.getDestinatario());

        Notificacion notificacion = new Notificacion();
        notificacion.setDestinatario(dto.getDestinatario());
        notificacion.setTipoNotificacion(dto.getTipoNotificacion());
        notificacion.setAsunto(dto.getAsunto());
        notificacion.setMensaje(dto.getMensaje());

        try {
            // Simulación del motor externo de envío (SMTP, pasarela SMS, etc.)
            log.info("Conectando con el servidor de mensajería externa...");

            notificacion.setEstado("ENVIADO");
            notificacion.setFechaEnvio(LocalDateTime.now());
            log.info("Mensaje despachado con éxito de forma síncrona");
        } catch (Exception e) {
            log.error("Fallo crítico en el proveedor de envío de mensajería: {}", e.getMessage());
            notificacion.setEstado("FALLIDO");
        }

        return repository.save(notificacion);
    }
}
