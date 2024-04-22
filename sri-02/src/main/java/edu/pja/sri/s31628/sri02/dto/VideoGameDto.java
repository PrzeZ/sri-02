package edu.pja.sri.s31628.sri02.dto;

import edu.pja.sri.s31628.sri02.model.Developer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoGameDto {
    private Long id;
    private String title;
    private List<Developer> developers = new ArrayList<>();
    private int releaseYear;
}
