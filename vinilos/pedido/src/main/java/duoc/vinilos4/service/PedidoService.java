package duoc.vinilos4.service;

import duoc.vinilos4.service.ClienteClient;
import duoc.vinilos4.service.ProductoClient;
import duoc.vinilos4.dto.PedidoDTO;
import duoc.vinilos4.dto.ProductoResponse;
import duoc.vinilos4.model.Pedido;
import duoc.vinilos4.repository.PedidoRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;
    private final ClienteClient clienteClient;
    private final ProductoClient productoClient;

    public List<Pedido> obtenerTodos() {
        log.info("Obteniendo lista de todos los pedidos");
        return repository.findAll();
    }

    public Pedido obtenerPorId(Long idPedido) {
        log.info("Buscando pedido por ID: {}", idPedido);
        return repository.findById(idPedido).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    public Pedido registrarPedido(PedidoDTO dto) {
        log.info("Iniciando validacion remota para registrar pedido del cliente: {}", dto.getRutCliente());


        try {
            clienteClient.obtenerCliente(dto.getRutCliente());
            log.info("Cliente validado correctamente");
        } catch (FeignException.NotFound e) {
            log.error("Fallo la validacion: Cliente no existe");
            throw new RuntimeException("El cliente con RUT " + dto.getRutCliente() + " no existe en el sistema");
        }


        try {
            ProductoResponse producto = productoClient.obtenerProducto(dto.getSkuProducto());
            log.info("Producto validado correctamente. Stock disponible: {}", producto.getStock());
            if (producto.getStock() < dto.getCantidad()) {
                log.error("Fallo la validacion: Stock insuficiente");
                throw new RuntimeException("Stock insuficiente. Solo hay " + producto.getStock() + " unidades disponibles");
            }
        } catch (FeignException.NotFound e) {
            log.error("Fallo la validacion: Producto no existe");
            throw new RuntimeException("El producto con SKU " + dto.getSkuProducto() + " no existe en el inventario");
        }

        Pedido pedido = new Pedido();
        pedido.setRutCliente(dto.getRutCliente());
        pedido.setSkuProducto(dto.getSkuProducto());
        pedido.setCantidad(dto.getCantidad());
        pedido.setEstado("APROBADO");

        log.info("Guardando pedido en base de datos");
        return repository.save(pedido);
    }
}