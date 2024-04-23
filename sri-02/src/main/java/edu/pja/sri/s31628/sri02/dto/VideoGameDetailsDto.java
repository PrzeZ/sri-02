package edu.pja.sri.s31628.sri02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
