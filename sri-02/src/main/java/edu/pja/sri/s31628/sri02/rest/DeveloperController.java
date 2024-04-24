package edu.pja.sri.s31628.sri02.rest;

import edu.pja.sri.s31628.sri02.dto.DeveloperDtoMapper;
import edu.pja.sri.s31628.sri02.model.Developer;
import edu.pja.sri.s31628.sri02.repo.DeveloperRepository;
import edu.pja.sri.s31628.sri02.repo.VideoGameRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/developers")
@RequiredArgsConstructor
public class DeveloperController {

    private DeveloperDtoMapper developerDtoMaper;
    private VideoGameRepository videoGameRepository;
    private DeveloperRepository DeveloperRepository;
    private ModelMapper modelMapper;
}
