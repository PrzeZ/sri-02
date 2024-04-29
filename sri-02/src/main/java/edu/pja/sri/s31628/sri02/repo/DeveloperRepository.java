package edu.pja.sri.s31628.sri02.repo;

import edu.pja.sri.s31628.sri02.model.Developer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeveloperRepository extends CrudRepository<Developer, Long> {
    List<Developer> findAll();
    @Query("from Developer as dev left join fetch dev.videoGames where dev.id=:developerId")
    Optional<Developer> getDevelopereDetailsById(@Param("developerId") Long developerId);
}
