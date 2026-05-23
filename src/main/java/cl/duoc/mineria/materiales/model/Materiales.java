package cl.duoc.mineria.materiales.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "materiales")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Materiales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El Nombre del mineral es obligatorio")
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    
    @NotBlank(message = "La clasificacion es obligatoria")
    @Column(name = "clasificacion", nullable = false)
    private String clasificacion;

    @Positive(message = "La densidad promedio debe ser mayor a 0")
    @Column(name = "densidad_promedio", nullable = false)
    private double densidadPromedio;

}
