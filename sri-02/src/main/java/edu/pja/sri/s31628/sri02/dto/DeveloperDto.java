package edu.pja.sri.s31628.sri02.dto;

import edu.pja.sri.s31628.sri02.model.VideoGame;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
    private String description;
}
