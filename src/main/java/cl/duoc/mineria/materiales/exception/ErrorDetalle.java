package cl.duoc.mineria.materiales.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetalle {

    private LocalDateTime timestamp;
    private String mensaje;
    private String detalles;

}
