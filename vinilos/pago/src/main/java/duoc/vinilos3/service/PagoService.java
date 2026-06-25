package duoc.vinilos3.service;

import duoc.vinilos3.dto.PagoDTO;
import duoc.vinilos3.model.Pago;
import duoc.vinilos3.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository repository;

    public List<Pago> obtenerTodos() {
        log.info("Obteniendo historial de todos los pagos");
        return repository.findAll();
    }

    public List<Pago> obtenerPorCliente(String rut) {
        log.info("Buscando pagos para el cliente: {}", rut);
        return repository.findByRutCliente(rut);
    }

    public Pago procesarPago(PagoDTO dto) {
        log.info("Procesando nuevo pago para RUT: {}", dto.getRutCliente());
        Pago pago = new Pago();
        pago.setRutCliente(dto.getRutCliente());
        pago.setMonto(dto.getMonto());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setFechaPago(LocalDateTime.now());

        return repository.save(pago);
    }
}