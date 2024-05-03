package edu.pja.sri.s31628.sri02.dto;

import edu.pja.sri.s31628.sri02.model.VideoGame;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideoGameDtoMapper {
    @Autowired
    private ModelMapper modelMapper;
    public VideoGameDto convertToDto(VideoGame v) {
        return modelMapper.map(v, VideoGameDto.class);
    }

    public VideoGame convertToEntity(VideoGameDto dto) {
        return modelMapper.map(dto, VideoGame.class);
    }
}
