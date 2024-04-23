package edu.pja.sri.s31628.sri02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoGameDto {
    private Long id;
    private String title;

    private int releaseYear;
}
