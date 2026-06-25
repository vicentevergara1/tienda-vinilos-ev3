package duoc.vinilos4.service;

import duoc.vinilos4.dto.ClienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente-service", url = "http://localhost:8081")
public interface ClienteClient {

    @GetMapping("/{rut}")
    ClienteResponse obtenerCliente(@PathVariable("rut") String rut);
}