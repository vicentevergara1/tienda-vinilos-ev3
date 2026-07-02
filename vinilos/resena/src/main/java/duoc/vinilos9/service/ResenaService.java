package duoc.vinilos9.service;

import duoc.vinilos9.dto.ResenaDTO;
import duoc.vinilos9.model.Resena;
import duoc.vinilos9.repository.ResenaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResenaService {

    private final ResenaRepository repository;

    public List<Resena> obtenerTodos() {
        log.info("Obteniendo lista de todas las resenas");
        return repository.findAll();
    }

    public Resena obtenerPorId(Long id) {
        log.info("Buscando resena con ID: {}", id);
        return repository.findById(id).orElseThrow(() -> {
            log.error("Resena no encontrada con ID: {}", id);
            return new RuntimeException("Resena no encontrada");
        });
    }

    public Resena registrarResena(ResenaDTO dto) {
        log.info("Registrando nueva resena con ID: {}", dto.getId());
        Resena resena = new Resena(dto.getId(), dto.getPuntuacion(), dto.getComentario(), LocalDateTime.now());
        return repository.save(resena);
    }
}