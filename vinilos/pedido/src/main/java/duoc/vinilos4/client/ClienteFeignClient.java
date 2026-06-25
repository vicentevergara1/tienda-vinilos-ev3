package duoc.vinilos4.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente-service", url = "http://localhost:8081/api/clientes")
public interface ClienteFeignClient {

    @GetMapping("/{rut}")
    Object obtenerClientePorRut(@PathVariable("rut") String rut);
}