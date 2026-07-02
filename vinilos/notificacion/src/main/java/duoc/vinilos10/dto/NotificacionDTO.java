package duoc.vinilos10.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionDTO {

    @NotBlank(message = "El destinatario es obligatorio")
    private String destinatario;

    @NotBlank(message = "El tipo de notificación es obligatorio (EMAIL, SMS, PUSH)")
    @Pattern(regexp = "^(EMAIL|SMS|PUSH)$", message = "El tipo debe ser EMAIL, SMS o PUSH")
    private String tipoNotificacion;

    @NotBlank(message = "El asunto no puede estar vacío")
    private String asunto;

    @NotBlank(message = "El contenido del mensaje es requerido")
    private String mensaje;
}