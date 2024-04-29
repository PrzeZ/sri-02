package edu.pja.sri.s31628.sri02.dto;

import edu.pja.sri.s31628.sri02.model.VideoGame;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperDto {
    private Long id;

    private String name;
    private String description;
}
