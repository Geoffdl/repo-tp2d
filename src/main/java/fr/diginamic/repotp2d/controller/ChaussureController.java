package fr.diginamic.repotp2d.controller;

import fr.diginamic.repotp2d.entity.Chaussure;
import fr.diginamic.repotp2d.service.ChaussureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Routes pour l'ajout et la lecture de chaussures
 * Accès restreint aux utilisateurs connectés (via cookie JWT)
 */
@RestController
@RequestMapping({"/chaussure"})
public class ChaussureController
{
    /**
     * Chaussure Service
     */
    @Autowired
    ChaussureService service;
    
    /**
     * Ajoute une chaussure en base
     * @param chaussure mapping jpa chaussure
     * @return chaussure insérée
     */
    @PostMapping({"/add"})
    public ResponseEntity<Chaussure> insert(@RequestBody Chaussure chaussure)
    {
        Chaussure saved = service.insert(chaussure);
        return ResponseEntity.ok(saved);
    }
    
    /**
     * Recupere toutes les chaussures
     * @return liste de chaussures
     */
    @GetMapping("/all")
    public ResponseEntity<List<Chaussure>> getAll()
    {
        List<Chaussure> chaussures = service.getAll();
        return ResponseEntity.ok(chaussures);
    }
}
