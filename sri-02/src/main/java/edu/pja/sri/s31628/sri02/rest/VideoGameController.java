package edu.pja.sri.s31628.sri02.rest;


import edu.pja.sri.s31628.sri02.dto.VideoGameDto;
import edu.pja.sri.s31628.sri02.dto.VideoGameDtoMapper;
import edu.pja.sri.s31628.sri02.model.VideoGame;
import edu.pja.sri.s31628.sri02.repo.VideoGameRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/games")
public class VideoGameController {
    private final VideoGameRepository videoGameRepository;
    private final VideoGameDtoMapper videoGameDtoMapper;

    @GetMapping
    public ResponseEntity<List<VideoGameDto>> getVideoGames() {
        List<VideoGame> videoGames = videoGameRepository.findAll();
        List<VideoGameDto> result = videoGames.stream().map(videoGameDtoMapper::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("{gameId}")
    public ResponseEntity<VideoGameDto> getVideoGameById(@PathVariable long gameId) {
        Optional<VideoGame> game = videoGameRepository.findById(gameId);

        if (game.isPresent()) {
            VideoGameDto videoGameDto = videoGameDtoMapper.convertToDto(game.get());
            return new ResponseEntity<>(videoGameDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity saveNewVideoGame(@Valid @RequestBody VideoGameDto videoGameDto) {
        VideoGame entity = videoGameDtoMapper.convertToEntity(videoGameDto);
        videoGameRepository.save(entity);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        responseHeaders.add("Location", location.toString());
        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping("{gameId}")
    public ResponseEntity updateVideoGame(@PathVariable Long gameId, @Valid @RequestBody VideoGameDto videoGameDto){
        Optional<VideoGame> game = videoGameRepository.findById(gameId);

        if (game.isPresent()) {
            videoGameDto.setId(gameId);
            VideoGame videoGame = videoGameDtoMapper.convertToEntity(videoGameDto);
            videoGameRepository.save(videoGame);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{gameId}")
    public ResponseEntity deleteVideoGame(@PathVariable Long gameId) {
        boolean found = videoGameRepository.existsById(gameId);

        if (found) {
            videoGameRepository.deleteById(gameId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
