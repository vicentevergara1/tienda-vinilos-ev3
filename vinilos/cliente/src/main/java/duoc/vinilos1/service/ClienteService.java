package duoc.vinilos1.service;

import duoc.vinilos1.dto.ClienteDTO;
import duoc.vinilos1.model.Cliente;
import duoc.vinilos1.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public List<Cliente> obtenerTodos() {
        log.info("Obteniendo lista de todos los clientes");
        return repository.findAll();
    }

    public Cliente obtenerPorRut(String rut) {
        log.info("Buscando cliente por rut: {}", rut);
        return repository.findById(rut).orElseThrow(() -> {
            log.error("Cliente no encontrado con rut: {}", rut);
            return new RuntimeException("Cliente no encontrado");
        });
    }

    public Cliente crearCliente(ClienteDTO dto) {
        log.info("Registrando nuevo cliente con rut: {}", dto.getRut());
        Cliente cliente = new Cliente(dto.getRut(), dto.getNombre(), dto.getCorreo());
        return repository.save(cliente);
    }
}