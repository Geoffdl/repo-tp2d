package fr.diginamic.repotp2d.repository;

import fr.diginamic.repotp2d.entity.Chaussure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChaussureRepository extends JpaRepository<Chaussure, Long>
{
}
