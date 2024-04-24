package edu.pja.sri.s31628.sri02.rest;

import edu.pja.sri.s31628.sri02.dto.DeveloperDetailsDto;
import edu.pja.sri.s31628.sri02.dto.DeveloperDto;
import edu.pja.sri.s31628.sri02.dto.DeveloperDtoMapper;
import edu.pja.sri.s31628.sri02.dto.VideoGameDto;
import edu.pja.sri.s31628.sri02.model.Developer;
import edu.pja.sri.s31628.sri02.model.VideoGame;
import edu.pja.sri.s31628.sri02.repo.DeveloperRepository;
import edu.pja.sri.s31628.sri02.repo.VideoGameRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/developers")
@RequiredArgsConstructor
public class DeveloperController {

    private DeveloperDtoMapper developerDtoMapper;
    private VideoGameRepository videoGameRepository;
    private DeveloperRepository DeveloperRepository;
    private ModelMapper modelMapper;

    public ResponseEntity<Collection<DeveloperDto>> getAllDevelopers() {
        List<Developer> developers = DeveloperRepository.findAll();
        List<DeveloperDto> result = developers.stream().map(developerDtoMapper::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
