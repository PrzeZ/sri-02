package edu.pja.sri.s31628.sri02.repo;

import edu.pja.sri.s31628.sri02.model.Developer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeveloperRepository extends CrudRepository<Developer, Long> {
    List<Developer> findAll();
}
