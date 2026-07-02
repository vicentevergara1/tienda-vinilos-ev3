package duoc.vinilos6.service;

import duoc.vinilos6.dto.InventarioDTO;
import duoc.vinilos6.model.Inventario;
import duoc.vinilos6.repository.InventarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository repository;

    @Transactional(readOnly = true)
    public List<Inventario> obtenerTodos() {
        log.info("Buscando listado completo de existencias en el inventario");
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Inventario obtenerPorSku(String sku) {
        log.info("Consultando stock disponible para el SKU: {}", sku);
        return repository.findBySku(sku)
                .orElseThrow(() -> new RuntimeException("No se encontró registro de inventario para el SKU: " + sku));
    }

    @Transactional
    public Inventario actualizarORegistrarStock(InventarioDTO dto) {
        log.info("Procesando actualización de stock en inventario para SKU: {}", dto.getSku());

        return repository.findBySku(dto.getSku())
                .map(inventarioExistente -> {
                    log.info("SKU localizado. Modificando stock de {} a {}", inventarioExistente.getStockDisponible(), dto.getStockDisponible());
                    inventarioExistente.setStockDisponible(dto.getStockDisponible());
                    inventarioExistente.setUbicacionBodega(dto.getUbicacionBodega());
                    return repository.save(inventarioExistente);
                })
                .orElseGet(() -> {
                    log.info("SKU nuevo detectado. Creando nueva entrada física en bodega");
                    Inventario nuevoInventario = new Inventario();
                    nuevoInventario.setSku(dto.getSku());
                    nuevoInventario.setStockDisponible(dto.getStockDisponible());
                    nuevoInventario.setUbicacionBodega(dto.getUbicacionBodega());
                    return repository.save(nuevoInventario);
                });
    }
}