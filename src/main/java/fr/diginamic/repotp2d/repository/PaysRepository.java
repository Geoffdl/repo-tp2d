package fr.diginamic.repotp2d.repository;

import fr.diginamic.repotp2d.entity.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Long>
{
    Optional<Pays> findByNom(String nom);
}
