package duoc.vinilos4.dto;

import lombok.Data;

@Data
public class ProductoResponse {
    private String sku;
    private String nombre;
    private Integer stock;
    private Integer precio;
}