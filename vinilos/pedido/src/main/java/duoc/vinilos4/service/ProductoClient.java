package duoc.vinilos4.service;

import duoc.vinilos4.dto.ProductoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producto-service", url = "http://localhost:8082")
public interface ProductoClient {

    @GetMapping("/{sku}")
    ProductoResponse obtenerProducto(@PathVariable("sku") String sku);
}
