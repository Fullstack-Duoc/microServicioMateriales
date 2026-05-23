package cl.duoc.mineria.materiales.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class MaterialRequestDTO {
    @NotBlank(message = "El nombre del mineral es obligatorio")
    private String nombre;

    @NotBlank(message = "La clasificacion es obligatoria")
    private String clasificacion;

    @Positive(message = "La densidad promedio debe ser mayor a 0")
    private double densidadPromedio;
}
