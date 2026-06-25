package duoc.vinilos3.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {

    @NotBlank(message = "El RUT del cliente es obligatorio")
    private String rutCliente;

    @NotNull(message = "El monto no puede ser nulo")
    @Positive(message = "El monto debe ser mayor a cero")
    private Integer monto;

    @NotBlank(message = "El método de pago es obligatorio")
    private String metodoPago;
}