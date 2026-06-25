package duoc.vinilos5.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvioDTO {

    @NotNull
    @Min(1)
    private Long idPedido;

    @NotBlank
    private String empresaTransporte;

    @NotBlank
    private String numeroSeguimiento;

    @NotBlank
    private String estadoActual;
}