package edu.pja.sri.s31628.sri02.repo;

import edu.pja.sri.s31628.sri02.model.VideoGame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoGameRepository extends CrudRepository<VideoGame, Long> {
    List<VideoGame> findAll();


}
