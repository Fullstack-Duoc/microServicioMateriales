package cl.duoc.mineria.materiales.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetalle> manejarValidaciones(MethodArgumentNotValidException ex){
        String errores = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        
        ErrorDetalle errorDetalle = ErrorDetalle.builder()
                    .timestamp(LocalDateTime.now())
                    .mensaje("Error de validacion en los datos")
                    .detalles(errores)
                    .build();
        return new ResponseEntity<>(errorDetalle, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MaterialInvalidoException.class)
    public ResponseEntity<ErrorDetalle> manejarMaterialInvalido(MaterialInvalidoException ex){
        ErrorDetalle errorDetalle = ErrorDetalle.builder()
                .timestamp(LocalDateTime.now())
                .mensaje("Error en logica de negocio")
                .detalles(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorDetalle, HttpStatus.BAD_REQUEST);
    }

}
