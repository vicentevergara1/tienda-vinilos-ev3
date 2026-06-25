package duoc.vinilos2.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    @NotBlank
    private String sku;

    @NotBlank
    private String nombre;

    @NotBlank
    private String artista;

    @NotNull
    @Min(1)
    private Integer precio;

    @NotNull
    @Min(0)
    private Integer stock;
}