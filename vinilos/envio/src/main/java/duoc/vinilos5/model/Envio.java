package duoc.vinilos5.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "envios")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_venta", nullable = false)
    private Long idVenta; // Hace referencia a la venta del ms-ventas

    @Column(name = "direccion_destino", nullable = false)
    private String direccionDestino;

    @Column(name = "estado_despacho", nullable = false)
    private String estadoDespacho; // Ej: "PREPARANDO", "EN_RUTA", "ENTREGADO"

    @Column(name = "codigo_seguimiento", unique = true)
    private String codigoSeguimiento;
}