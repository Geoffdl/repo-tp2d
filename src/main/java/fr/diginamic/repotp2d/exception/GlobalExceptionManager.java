package fr.diginamic.repotp2d.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionManager
{
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleIllegalArgumentException(Exception e)
    {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
    
    @ExceptionHandler(ProblemException.class)
    public ResponseEntity<String> handleIllegalArgumentException(ProblemException e)
    {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
