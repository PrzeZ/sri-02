package edu.pja.sri.s31628.sri02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperDetailsDto extends RepresentationModel {
    private Long id;

    private String name;

    private String description;

    private Set<VideoGameDto> videoGames;

}
