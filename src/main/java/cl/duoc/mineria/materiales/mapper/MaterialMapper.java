package cl.duoc.mineria.materiales.mapper;

import org.springframework.stereotype.Component;

import cl.duoc.mineria.materiales.dto.MaterialRequestDTO;
import cl.duoc.mineria.materiales.dto.MaterialResponseDTO;
import cl.duoc.mineria.materiales.model.Materiales;

@Component
public class MaterialMapper {

    public Materiales toEntity(MaterialRequestDTO dto){
        return Materiales.builder()
                .nombre(dto.getNombre())
                .clasificacion(dto.getClasificacion())
                .densidadPromedio(dto.getDensidadPromedio())
                .build();
    }

    public MaterialResponseDTO toResponseDTO(Materiales entity){
        MaterialResponseDTO dto = new MaterialResponseDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setClasificacion(entity.getClasificacion());
        dto.setDensidadPromedio(entity.getDensidadPromedio());
        return dto;
    }

}
