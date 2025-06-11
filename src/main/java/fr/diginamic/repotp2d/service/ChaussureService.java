package fr.diginamic.repotp2d.service;

import fr.diginamic.repotp2d.entity.Chaussure;
import fr.diginamic.repotp2d.entity.Pays;
import fr.diginamic.repotp2d.repository.ChaussureRepository;
import fr.diginamic.repotp2d.repository.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Chaussure service
 */
@Service
public class ChaussureService
{
    /**
     * Chaussure repository
     */
    @Autowired
    ChaussureRepository chaussureRepository;
    /**
     * Pays repository
     */
    @Autowired
    PaysRepository paysRepository;
    
    /**
     * Ajoute une chaussure en base, et vérifie si le pays d'origine existe en base, si non crée un nouveau pays
     * @param chaussure entité JPa chaussure
     * @return entité créée
     */
    @Transactional
    public Chaussure insert(Chaussure chaussure)
    {
        Pays pays = chaussure.getPaysOrigine();
        if (pays != null)
        {
            Optional<Pays> paysEnBase = paysRepository.findByNom(pays.getNom());
            if (paysEnBase.isPresent())
            {
                chaussure.setPaysOrigine(paysEnBase.get());
            }
            else
            {
                Pays nouveauPays = paysRepository.save(pays);
                chaussure.setPaysOrigine(nouveauPays);
            }
        }
        return chaussureRepository.save(chaussure);
    }
    
    /**
     * Recupere json de toutes les chaussures
     * @return list de toutes les chaussures
     */
    public List<Chaussure> getAll()
    {
        return chaussureRepository.findAll();
    }
}
