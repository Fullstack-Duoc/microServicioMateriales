package cl.duoc.mineria.materiales.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.mineria.materiales.dto.MaterialRequestDTO;
import cl.duoc.mineria.materiales.dto.MaterialResponseDTO;
import cl.duoc.mineria.materiales.service.MaterialService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/materiales")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    // Crear nuevo material (POST)
    @PostMapping
    public ResponseEntity<MaterialResponseDTO> crearMaterial(@Valid @RequestBody MaterialRequestDTO request){
        return new ResponseEntity<>(materialService.crearMaterial(request), HttpStatus.CREATED);
    }

    // Listar todos (GET)
    @GetMapping
    public ResponseEntity<List<MaterialResponseDTO>> listarTodos() {
        return ResponseEntity.ok(materialService.listarTodos());
    }

    // Obtener uno por ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<MaterialResponseDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(materialService.obtenerPorId(id));
    }

    // Actualizar un material existente
    @PutMapping("/{id}")
    public ResponseEntity<MaterialResponseDTO> actualizarMaterial(@PathVariable Long id, @Valid @RequestBody MaterialRequestDTO request){
        return ResponseEntity.ok(materialService.actualizarMaterial(id, request));
    }

    // Eliminar un material
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMaterial(@PathVariable Long id){
        materialService.eliminarMaterial(id);
        return ResponseEntity.noContent().build();
    }

    // Actualización parcial de la densidad (PATCH)
    @PatchMapping("/{id}/densidad")
    public ResponseEntity<MaterialResponseDTO> actualizarDensidad(@PathVariable Long id, @RequestParam double valor) {
        return ResponseEntity.ok(materialService.actualizarDensidad(id, valor));
    }

    // Buscar por clasificación (GET)
    @GetMapping("/buscar")
    public ResponseEntity<List<MaterialResponseDTO>> buscarPorClasificacion(@RequestParam String clasificacion) {
        return ResponseEntity.ok(materialService.listarPorClasificacion(clasificacion));
    }

}
