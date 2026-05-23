package cl.duoc.mineria.materiales.service;

import cl.duoc.mineria.materiales.dto.MaterialRequestDTO;
import cl.duoc.mineria.materiales.dto.MaterialResponseDTO;
import cl.duoc.mineria.materiales.exceptions.MaterialInvalidoException;
import cl.duoc.mineria.materiales.mapper.MaterialMapper;
import cl.duoc.mineria.materiales.model.Materiales;
import cl.duoc.mineria.materiales.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;   
    private final MaterialMapper materialMapper;
    private final WebClient webClient;

    // 1. Crear un material (POST)
    public MaterialResponseDTO crearMaterial(MaterialRequestDTO request){
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
        Materiales material = materialRepository.findById(id)
                .orElseThrow(() -> new MaterialInvalidoException("No se encontro ningun material con el ID: " + id));
        
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

}
