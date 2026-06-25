package duoc.vinilos4.service;

import duoc.vinilos4.dto.ClienteResponse;
import duoc.vinilos4.dto.PedidoDTO;
import duoc.vinilos4.dto.ProductoResponse;
import duoc.vinilos4.model.Pedido;
import duoc.vinilos4.repository.PedidoRepository;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository repository;

    @Mock
    private ClienteClient clienteClient;

    @Mock
    private ProductoClient productoClient;

    @InjectMocks
    private PedidoService service;

    @Test
    void registrarPedido_FlujoExitoso_DebeGuardarPedido() {
        PedidoDTO dto = new PedidoDTO();
        dto.setRutCliente("11111111-1");
        dto.setSkuProducto("VIN-001");
        dto.setCantidad(2);

        ProductoResponse productoMock = new ProductoResponse();
        productoMock.setStock(10);

        Pedido pedidoGuardado = new Pedido();
        pedidoGuardado.setIdPedido(1L);
        pedidoGuardado.setRutCliente("11111111-1");
        pedidoGuardado.setSkuProducto("VIN-001");
        pedidoGuardado.setCantidad(2);
        pedidoGuardado.setEstado("APROBADO");

        when(clienteClient.obtenerCliente(anyString())).thenReturn(new ClienteResponse());
        when(productoClient.obtenerProducto(anyString())).thenReturn(productoMock);
        when(repository.save(any(Pedido.class))).thenReturn(pedidoGuardado);

        Pedido resultado = service.registrarPedido(dto);

        assertNotNull(resultado);
        assertEquals("APROBADO", resultado.getEstado());
        verify(clienteClient, times(1)).obtenerCliente("11111111-1");
        verify(productoClient, times(1)).obtenerProducto("VIN-001");
        verify(repository, times(1)).save(any(Pedido.class));
    }

    @Test
    void registrarPedido_ClienteNoExiste_LanzaExcepcion() {
        PedidoDTO dto = new PedidoDTO();
        dto.setRutCliente("99999999-9");

        FeignException.NotFound notFoundMock = mock(FeignException.NotFound.class);
        doThrow(notFoundMock).when(clienteClient).obtenerCliente(anyString());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.registrarPedido(dto);
        });

        assertTrue(exception.getMessage().contains("no existe en el sistema"));
        verify(productoClient, never()).obtenerProducto(anyString());
        verify(repository, never()).save(any(Pedido.class));
    }

    @Test
    void registrarPedido_StockInsuficiente_LanzaExcepcion() {
        PedidoDTO dto = new PedidoDTO();
        dto.setRutCliente("11111111-1");
        dto.setSkuProducto("VIN-001");
        dto.setCantidad(5);

        ProductoResponse productoMock = new ProductoResponse();
        productoMock.setStock(2);

        when(clienteClient.obtenerCliente(anyString())).thenReturn(new ClienteResponse());
        when(productoClient.obtenerProducto(anyString())).thenReturn(productoMock);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.registrarPedido(dto);
        });

        assertTrue(exception.getMessage().contains("Stock insuficiente"));
        verify(repository, never()).save(any(Pedido.class));
    }
}