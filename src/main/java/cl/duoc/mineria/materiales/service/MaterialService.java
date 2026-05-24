package cl.duoc.mineria.materiales.service;

import cl.duoc.mineria.materiales.dto.MaterialRequestDTO;
import cl.duoc.mineria.materiales.dto.MaterialResponseDTO;
import cl.duoc.mineria.materiales.exception.MaterialInvalidoException;
import cl.duoc.mineria.materiales.mapper.MaterialMapper;
import cl.duoc.mineria.materiales.model.Materiales;
import cl.duoc.mineria.materiales.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;   
    private final MaterialMapper materialMapper;

    // 1. Crear un material (POST)
    public MaterialResponseDTO crearMaterial(MaterialRequestDTO request){
        validarDensidad(request.getDensidadPromedio());

        if(materialRepository.findByNombreIgnoreCase(request.getNombre()).isPresent()){
            throw new MaterialInvalidoException("Ya existe un material registrado con el nombre: " + request.getNombre());
        }

        Materiales nuevoMaterial = materialMapper.toEntity(request);
        Materiales guardado = materialRepository.save(nuevoMaterial);

        return materialMapper.toResponseDTO(guardado);
    }

    // 2. Obtener todos (GET)
    public List<MaterialResponseDTO> listarTodos(){
        return materialRepository.findAll().stream()
                .map(materialMapper::toResponseDTO)
                .toList();
    }

    // 3. Obtener por ID (GET)
    public MaterialResponseDTO obtenerPorId(Long id){
        Materiales material = materialRepository.findById(id)
                .orElseThrow(() -> new MaterialInvalidoException("No se encontró ningun material con el ID: " + id));
        return materialMapper.toResponseDTO(material);
    }

    // 4. Actualizar material completo (PUT)
    public MaterialResponseDTO actualizarMaterial(Long id, MaterialRequestDTO request){
        validarDensidad(request.getDensidadPromedio());

        Materiales material = materialRepository.findById(id)
                .orElseThrow(() -> new MaterialInvalidoException("No se encontro ningun material con el ID: " + id));
        
        // Verificar que el nuevo nombre no esté tomado por OTRO material
        materialRepository.findByNombreIgnoreCase(request.getNombre())
                .ifPresent(m -> {
                    if (!m.getId().equals(id)) {
                        throw new MaterialInvalidoException("El nombre '" + request.getNombre() + "' ya existe en otro registro");
                    }
                });

        material.setNombre(request.getNombre());
        material.setClasificacion(request.getClasificacion());
        material.setDensidadPromedio(request.getDensidadPromedio());
        Materiales actualizado = materialRepository.save(material);

        return materialMapper.toResponseDTO(actualizado);
    }

    // 5. Eliminar (DELETE)
    public void eliminarMaterial(Long id){
        if (!materialRepository.existsById(id)) {
            throw new MaterialInvalidoException("No se encontro ningun material con el ID: " + id);
        }
        materialRepository.deleteById(id);
    }

    // 6. Actualización parcial de densidad (PATCH)
    public MaterialResponseDTO actualizarDensidad(Long id, double nuevaDensidad) {
        validarDensidad(nuevaDensidad);
        Materiales material = materialRepository.findById(id)
                .orElseThrow(() -> new MaterialInvalidoException("No se encontró el material con ID: " + id));
        
        material.setDensidadPromedio(nuevaDensidad);
        return materialMapper.toResponseDTO(materialRepository.save(material));
    }

    // 7. Filtrar por clasificación (GET)
    public List<MaterialResponseDTO> listarPorClasificacion(String clasificacion) {
        if (clasificacion == null || clasificacion.isBlank()) {
            throw new MaterialInvalidoException("El criterio de clasificación no puede estar vacío");
        }
        
        return materialRepository.findByClasificacionIgnoreCase(clasificacion).stream()
                .map(materialMapper::toResponseDTO)
                .toList();
    }

    // Método auxiliar para centralizar la validación de negocio
    private void validarDensidad(double densidad) {
        if (densidad <= 0) {
            throw new MaterialInvalidoException("La densidad debe ser mayor a 0");
        }
    }
}
