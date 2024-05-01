package edu.pja.sri.s31628.sri02.rest;

import edu.pja.sri.s31628.sri02.dto.DeveloperDetailsDto;
import edu.pja.sri.s31628.sri02.dto.DeveloperDto;
import edu.pja.sri.s31628.sri02.dto.DeveloperDtoMapper;
import edu.pja.sri.s31628.sri02.model.Developer;
import edu.pja.sri.s31628.sri02.repo.DeveloperRepository;
import edu.pja.sri.s31628.sri02.repo.VideoGameRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("{developerId}")
    public ResponseEntity<DeveloperDetailsDto> getDeveloperById(@PathVariable long developerId) {

        Optional<Developer> dev = developerRepository.findById(developerId);
        if (dev.isPresent()) {
            DeveloperDetailsDto developerDetailsDto = developerDtoMapper.convertToDetailsDto(dev.get());

            Link linkSelf = linkTo(methodOn(DeveloperController.class).getDeveloperById(developerId)).withSelfRel();
            developerDetailsDto.add(linkSelf);
            return new ResponseEntity<>(developerDetailsDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping
//    public ResponseEntity saveNewDeveloper(@RequestBody DeveloperDto developerDto) {
//        Developer entity = developerDtoMapper.convertToEntity(developerDto);
//        DeveloperRepository.save(entity);
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
//        responseHeaders.add("Location", location.toString());
//        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
//    }
//
//    @PutMapping("{developerId}")
//    public ResponseEntity updateDeveloper(@PathVariable Long developerId, @RequestBody DeveloperDto developerDto){
//        Optional<Developer> dev = developerRepository.findById(developerId);
//        if (dev.isPresent()) {
//            developerDto.setId(developerId);
//            Developer developer = developerDtoMapper.convertToEntity(developerDto);
//           DeveloperRepository.save(developer);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("{developerId}")
//    public ResponseEntity deleteDeveloper(@PathVariable Long developerId) {
//        boolean found = developerRepository.existsById(developerId);
//        if (found) {
//            developerRepository.deleteById(developerId);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

}
