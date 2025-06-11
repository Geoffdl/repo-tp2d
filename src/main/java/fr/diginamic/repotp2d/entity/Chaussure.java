package fr.diginamic.repotp2d.entity;

import jakarta.persistence.*;

/**
 * Entite JPA
 */
@Entity
public class Chaussure
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String reference;
    private String marque;
    private String modele;
    private String couleur;
    private String taille;
    
    @ManyToOne
    @JoinColumn(name = "id_pays_origine")
    private Pays paysOrigine;
    
    public Chaussure()
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
     * @return reference
     */
    public String getReference()
    {
        return reference;
    }
    
    /**
     * Setter
     * @param reference sets value
     */
    public void setReference(String reference)
    {
        this.reference = reference;
    }
    
    /**
     * Getter
     * @return marque
     */
    public String getMarque()
    {
        return marque;
    }
    
    /**
     * Setter
     * @param marque sets value
     */
    public void setMarque(String marque)
    {
        this.marque = marque;
    }
    
    /**
     * Getter
     * @return modele
     */
    public String getModele()
    {
        return modele;
    }
    
    /**
     * Setter
     * @param modele sets value
     */
    public void setModele(String modele)
    {
        this.modele = modele;
    }
    
    /**
     * Getter
     * @return couleur
     */
    public String getCouleur()
    {
        return couleur;
    }
    
    /**
     * Setter
     * @param couleur sets value
     */
    public void setCouleur(String couleur)
    {
        this.couleur = couleur;
    }
    
    /**
     * Getter
     * @return taille
     */
    public String getTaille()
    {
        return taille;
    }
    
    /**
     * Setter
     * @param taille sets value
     */
    public void setTaille(String taille)
    {
        this.taille = taille;
    }
    
    /**
     * Getter
     * @return paysOrigine
     */
    public Pays getPaysOrigine()
    {
        return paysOrigine;
    }
    
    /**
     * Setter
     * @param paysOrigine sets value
     */
    public void setPaysOrigine(Pays paysOrigine)
    {
        this.paysOrigine = paysOrigine;
    }
}
