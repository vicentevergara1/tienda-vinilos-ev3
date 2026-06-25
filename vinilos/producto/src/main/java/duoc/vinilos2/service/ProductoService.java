package duoc.vinilos2.service;

import duoc.vinilos2.dto.ProductoDTO;
import duoc.vinilos2.model.Producto;
import duoc.vinilos2.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository repository;

    public List<Producto> obtenerTodos() {
        log.info("Obteniendo lista de todos los productos del inventario");
        return repository.findAll();
    }

    public Producto obtenerPorSku(String sku) {
        log.info("Buscando producto por SKU: {}", sku);
        return repository.findById(sku).orElseThrow(() -> {
            log.error("Producto no encontrado con SKU: {}", sku);
            return new RuntimeException("Producto no encontrado");
        });
    }

    public Producto registrarProducto(ProductoDTO dto) {
        log.info("Registrando nuevo producto con SKU: {}", dto.getSku());
        Producto producto = new Producto(dto.getSku(), dto.getNombre(), dto.getArtista(), dto.getPrecio(), dto.getStock());
        return repository.save(producto);
    }
}