package cl.duoc.mineria.materiales.dto;

import lombok.Data;

@Data
public class MaterialResponseDTO {
    private Long id;
    private String nombre;
    private String clasificacion;
    private double densidadPromedio;
}
