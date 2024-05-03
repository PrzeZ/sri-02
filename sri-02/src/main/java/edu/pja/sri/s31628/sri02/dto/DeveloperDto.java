package edu.pja.sri.s31628.sri02.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperDto extends RepresentationModel {
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    private String description;
}
