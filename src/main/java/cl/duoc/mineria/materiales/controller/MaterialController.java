package cl.duoc.mineria.materiales.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/materiales")
@RequiredArgsConstructor
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    // POST: Crear un nuevo material en el catálogo (/materiales)
    @PostMapping
    public ResponseEntity<MaterialResponseDTO> crearMaterial(@Valid @RequestBody MaterialRequestDTO request){
        return new ResponseEntity<>(materialService.crearMaterial(request), HttpStatus.CREATED);
    }

    // GET: Listar todos los materiales registrados (/materiales/obtener-materiales)
    @GetMapping("/obtener-materiales")
    public ResponseEntity<List<MaterialResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(materialService.listarTodos());
    }

    // GET: Obtener un material específico por ID (/materiales/obtener-materiales/{id})
    @GetMapping("/obtener-materiales/{id}")
    public ResponseEntity<MaterialResponseDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(materialService.obtenerPorId(id));
    }

    // PUT: Actualizar la información completa de un material (/materiales/actualizar-material/{id})
    @PutMapping("/actualizar-material/{id}")
    public ResponseEntity<MaterialResponseDTO> actualizarMaterial(@PathVariable Long id, @Valid @RequestBody MaterialRequestDTO request){
        return ResponseEntity.ok(materialService.actualizarMaterial(id, request));
    }

    // DELETE: Eliminar un material de forma permanente (/materiales/eliminar-material/{id})
    @DeleteMapping("/eliminar-material/{id}")
    public ResponseEntity<Void> eliminarMaterial(@PathVariable Long id){
        materialService.eliminarMaterial(id);
        return ResponseEntity.noContent().build();
    }

    // PATCH: Actualizar únicamente la densidad de un material (/materiales/modificar-material/{id}/densidad)
    @PatchMapping("/modificar-material/{id}/densidad")
    public ResponseEntity<MaterialResponseDTO> actualizarDensidad(@PathVariable Long id, @RequestParam double valor) {
        return ResponseEntity.ok(materialService.actualizarDensidad(id, valor));
    }

    // GET: Buscar materiales filtrados por clasificación específica (/materiales/buscar) (/materiales/buscar?clasificacion=Metalico)
    @GetMapping("/buscar")
    public ResponseEntity<List<MaterialResponseDTO>> buscar(@RequestParam String clasificacion) {
        return ResponseEntity.ok(materialService.listarPorClasificacion(clasificacion));
    }

}
