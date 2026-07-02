package duoc.vinilos6.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDTO {

    @NotBlank(message = "El SKU es obligatorio y debe coincidir con el de producto-service")
    private String sku;

    @NotNull(message = "El stock disponible no puede ser nulo")
    @Min(value = 0, message = "El stock disponible no puede ser un número negativo")
    private Integer stockDisponible;

    @NotBlank(message = "La ubicación física en bodega es requerida")
    private String ubicacionBodega;
}