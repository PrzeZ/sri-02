package edu.pja.sri.s31628.sri02.dto;

import edu.pja.sri.s31628.sri02.model.Developer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeveloperDtoMapper {
    @Autowired
    private ModelMapper modelMapper;

    public DeveloperDetailsDto convertToDetailsDto(Developer dev) {
        return modelMapper.map(dev, DeveloperDetailsDto.class);
    }

    public DeveloperDto convertToDto(Developer dev) {
        return modelMapper.map(dev, DeveloperDto.class);
    }

    public Developer convertToEntity(DeveloperDto dto) {
        return modelMapper.map(dto, Developer.class);
    }
}
