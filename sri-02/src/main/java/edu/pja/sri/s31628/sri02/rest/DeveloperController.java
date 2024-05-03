package edu.pja.sri.s31628.sri02.rest;

import edu.pja.sri.s31628.sri02.dto.DeveloperDetailsDto;
import edu.pja.sri.s31628.sri02.dto.DeveloperDto;
import edu.pja.sri.s31628.sri02.dto.DeveloperDtoMapper;
import edu.pja.sri.s31628.sri02.model.Developer;
import edu.pja.sri.s31628.sri02.model.VideoGame;
import edu.pja.sri.s31628.sri02.repo.DeveloperRepository;
import edu.pja.sri.s31628.sri02.repo.VideoGameRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/developers")
public class DeveloperController {

    private final DeveloperRepository developerRepository;
    private final DeveloperDtoMapper developerDtoMapper;
    private final VideoGameRepository videoGameRepository;
    private final ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<Collection<DeveloperDto>> getAllDevelopers() {
        List<Developer> developers = developerRepository.findAll();
        List<DeveloperDto> result = developers.stream().map(developerDtoMapper::convertToDto).collect(Collectors.toList());
        for (DeveloperDto developerDto : result) {
            developerDto.add(CreateDeveloperLinkSelf(developerDto.getId()));
            developerDto.add(CreateDeveloperGamesLink(developerDto.getId()));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("{developerId}")
    public ResponseEntity<DeveloperDetailsDto> getDeveloperDetailsById(@PathVariable long developerId) {
        Optional<Developer> dev = developerRepository.findById(developerId);
        
        if (dev.isPresent()) {
            DeveloperDetailsDto developerDetailsDto = developerDtoMapper.convertToDetailsDto(dev.get());
            Link linkSelf = CreateDeveloperLinkSelf(developerId);
            developerDetailsDto.add(linkSelf);
            return new ResponseEntity<>(developerDetailsDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{developerId}/games")
    public ResponseEntity<List<VideoGame>> getDeveloperGamesById(@PathVariable Long developerId) {
        Optional<Developer> developerOptional = developerRepository.findById(developerId);

        if (!developerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 if the developer does not exist
        }

        Developer developer = developerOptional.get();
        List<VideoGame> games = new ArrayList<>(developer.getVideoGames()); // Assuming getVideoGames() returns a collection of VideoGame

        if (games.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns 204 if no games are associated with the developer
        }

        return new ResponseEntity<>(games, HttpStatus.OK); // Returns 200 with the games
    }

    @PostMapping
    public ResponseEntity saveNewDeveloper(@RequestBody DeveloperDto developerDto) {
        Developer entity = developerDtoMapper.convertToEntity(developerDto);
        developerRepository.save(entity);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        responseHeaders.add("Location", location.toString());
        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping("{developerId}")
    public ResponseEntity updateDeveloper(@PathVariable Long developerId, @RequestBody DeveloperDto developerDto){
        Optional<Developer> dev = developerRepository.findById(developerId);

        if (dev.isPresent()) {
            developerDto.setId(developerId);
            Developer developer = developerDtoMapper.convertToEntity(developerDto);
           developerRepository.save(developer);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{developerId}/games/{gameId}")
    public ResponseEntity<?> addGameToDeveloper(@PathVariable Long developerId, @PathVariable Long gameId) {
        Optional<Developer> developerOptional = developerRepository.findById(developerId);
        Optional<VideoGame> gameOptional = videoGameRepository.findById(gameId);

        if (!developerOptional.isPresent()) {
            return new ResponseEntity<>("Developer not found", HttpStatus.NOT_FOUND);
        }
        if (!gameOptional.isPresent()) {
            return new ResponseEntity<>("Game not found", HttpStatus.NOT_FOUND);
        }

        Developer developer = developerOptional.get();
        VideoGame game = gameOptional.get();

        developer.getVideoGames().add(game);
        developerRepository.save(developer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{developerId}")
    public ResponseEntity deleteDeveloper(@PathVariable Long developerId) {
        boolean found = developerRepository.existsById(developerId);

        if (found) {
            developerRepository.deleteById(developerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{developerId}/games/{gameId}")
    public ResponseEntity<?> removeGameFromDeveloper(@PathVariable Long developerId, @PathVariable Long gameId) {
        Optional<Developer> developerOptional = developerRepository.findById(developerId);
        Optional<VideoGame> gameOptional = videoGameRepository.findById(gameId);

        if (!developerOptional.isPresent()) {
            return new ResponseEntity<>("Developer not found!", HttpStatus.NOT_FOUND);
        }
        if (!gameOptional.isPresent()) {
            return new ResponseEntity<>("Game not found!", HttpStatus.NOT_FOUND);
        }

        Developer developer = developerOptional.get();
        VideoGame game = gameOptional.get();

        if (!developer.getVideoGames().contains(game)) {
            return new ResponseEntity<>("Game not found in developer!", HttpStatus.BAD_REQUEST);
        }

        developer.getVideoGames().remove(game);
        developerRepository.save(developer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Link CreateDeveloperLinkSelf(Long developerId) {
        return linkTo(methodOn(DeveloperController.class).getDeveloperDetailsById(developerId)).withSelfRel();
    }

    private Link CreateDeveloperGamesLink(Long developerId) {
        return linkTo(methodOn(DeveloperController.class).getDeveloperGamesById(developerId)).withSelfRel();
    }
}
