package duoc.vinilos5.service;

import duoc.vinilos5.dto.EnvioDTO;
import duoc.vinilos5.model.Envio;
import duoc.vinilos5.repository.EnvioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnvioService {

    private final EnvioRepository repository;

    public List<Envio> obtenerTodos() {
        log.info("Obteniendo lista de todos los envios");
        return repository.findAll();
    }

    public Envio obtenerPorId(Long idEnvio) {
        log.info("Buscando envio por ID: {}", idEnvio);
        return repository.findById(idEnvio).orElseThrow(() -> {
            log.error("Envio no encontrado con ID: {}", idEnvio);
            return new RuntimeException("Envio no encontrado");
        });
    }

    public Envio registrarEnvio(EnvioDTO dto) {
        log.info("Registrando nuevo envio para el pedido ID: {}", dto.getIdPedido());
        Envio envio = new Envio();
        envio.setIdPedido(dto.getIdPedido());
        envio.setEmpresaTransporte(dto.getEmpresaTransporte());
        envio.setNumeroSeguimiento(dto.getNumeroSeguimiento());
        envio.setEstadoActual(dto.getEstadoActual());
        return repository.save(envio);
    }
}