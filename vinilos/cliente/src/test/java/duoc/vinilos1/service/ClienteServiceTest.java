package duoc.vinilos1.service;

import duoc.vinilos1.dto.ClienteDTO;
import duoc.vinilos1.model.Cliente;
import duoc.vinilos1.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    @Test
    void obtenerTodos_DebeRetornarListaDeClientes() {
        Cliente c1 = new Cliente("11111111-1", "Juan Perez", "juan@test.com");
        Cliente c2 = new Cliente("22222222-2", "Maria Gomez", "maria@test.com");
        when(repository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<Cliente> resultado = service.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void obtenerPorRut_CuandoExiste_DebeRetornarCliente() {
        String rutBuscado = "11111111-1";
        Cliente clienteSimulado = new Cliente(rutBuscado, "Juan Perez", "juan@test.com");
        when(repository.findById(rutBuscado)).thenReturn(Optional.of(clienteSimulado));

        Cliente resultado = service.obtenerPorRut(rutBuscado);

        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getNombre());
        verify(repository, times(1)).findById(rutBuscado);
    }

    @Test
    void obtenerPorRut_CuandoNoExiste_DebeLanzarExcepcion() {
        String rutBuscado = "99999999-9";
        when(repository.findById(rutBuscado)).thenReturn(Optional.empty());

        RuntimeException excepcion = assertThrows(RuntimeException.class, () -> {
            service.obtenerPorRut(rutBuscado);
        });

        assertEquals("Cliente no encontrado", excepcion.getMessage());
        verify(repository, times(1)).findById(rutBuscado);
    }

    @Test
    void crearCliente_DebeGuardarYRetornarCliente() {
        ClienteDTO dto = new ClienteDTO();
        dto.setRut("33333333-3");
        dto.setNombre("Pedro Pascal");
        dto.setCorreo("pedro@test.com");

        Cliente clienteGuardado = new Cliente("33333333-3", "Pedro Pascal", "pedro@test.com");

        when(repository.save(any(Cliente.class))).thenReturn(clienteGuardado);

        Cliente resultado = service.crearCliente(dto);

        assertNotNull(resultado);
        assertEquals("33333333-3", resultado.getRut());
        assertEquals("Pedro Pascal", resultado.getNombre());
        verify(repository, times(1)).save(any(Cliente.class));
    }
}