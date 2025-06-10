package fr.diginamic.repotp2d.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception manager
 */
@ControllerAdvice
public class GlobalExceptionManager
{
    /**
     * Gestion des autres exceptions
     * @param e erreur
     * @return réponse serveur
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleIllegalArgumentException(Exception e)
    {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
    
    /**
     * Gestion exceptions business
     * @param e erreur
     * @return réponse serveur
     */
    @ExceptionHandler(ProblemException.class)
    public ResponseEntity<String> handleIllegalArgumentException(ProblemException e)
    {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
