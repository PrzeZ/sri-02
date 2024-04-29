package edu.pja.sri.s31628.sri02.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoGameDto {
    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String title;

    private int releaseYear;
}
