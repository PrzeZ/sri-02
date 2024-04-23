package edu.pja.sri.s31628.sri02.dto;

import edu.pja.sri.s31628.sri02.model.Developer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoGameDetailsDto {
    private Long id;
    private String title;
    private Set<DeveloperDto> developers;
    private int releaseYear;
}
