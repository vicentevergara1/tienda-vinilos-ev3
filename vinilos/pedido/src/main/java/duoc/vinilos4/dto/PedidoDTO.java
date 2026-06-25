package duoc.vinilos4.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    @NotBlank
    private String rutCliente;

    @NotBlank
    private String skuProducto;

    @NotNull
    @Min(1)
    private Integer cantidad;
}