package edu.pja.sri.s31628.sri02.repo;

import edu.pja.sri.s31628.sri02.model.VideoGame;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoGameRepository extends CrudRepository<VideoGame, Long> {
    List<VideoGame> findAll();

//    @Query("from VideoGame as game left join fetch game.developers where game.id=:videoGameId")
//    Optional<VideoGame> getVideoGameDetailsById(@Param("videoGameId") Long videoGameId);
}
