package duoc.vinilos9.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResenaDTO {

    @NotBlank(message = "El ID de resena es obligatorio")
    private Long id;

    @NotNull(message = "Debe ingresar puntuacion")
    @Min(1)
    @Max(5)
    private Integer puntuacion;

    @NotBlank(message = "Debe ingresar comentario")
    private String comentario;

    //@NotNull(message = "Debe ingresar puntuacion")
    //private LocalDateTime fechaResena;

}