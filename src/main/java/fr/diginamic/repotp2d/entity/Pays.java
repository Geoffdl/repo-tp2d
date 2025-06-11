package fr.diginamic.repotp2d.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

/**
 * Entite JPA
 */
@Entity
public class Pays
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;
    private int nbHab;
    
    @OneToMany(mappedBy = "paysOrigine")
    @JsonIgnore
    private List<Chaussure> chaussures;
    
    public Pays()
    {
    }
    
    /**
     * Getter
     * @return id
     */
    public Long getId()
    {
        return id;
    }
    
    /**
     * Setter
     * @param id sets value
     */
    public void setId(Long id)
    {
        this.id = id;
    }
    
    /**
     * Getter
     * @return nom
     */
    public String getNom()
    {
        return nom;
    }
    
    /**
     * Setter
     * @param nom sets value
     */
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    
    /**
     * Getter
     * @return nbHab
     */
    public int getNbHab()
    {
        return nbHab;
    }
    
    /**
     * Setter
     * @param nbHab sets value
     */
    public void setNbHab(int nbHab)
    {
        this.nbHab = nbHab;
    }
    
    /**
     * Getter
     * @return chaussures
     */
    public List<Chaussure> getChaussures()
    {
        return chaussures;
    }
    
    /**
     * Setter
     * @param chaussures sets value
     */
    public void setChaussures(List<Chaussure> chaussures)
    {
        this.chaussures = chaussures;
    }
}
