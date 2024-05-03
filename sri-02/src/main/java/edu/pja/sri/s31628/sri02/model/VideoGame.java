package edu.pja.sri.s31628.sri02.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoGame {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String title;

    @ManyToOne
    @JoinColumn(name="developer_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Developer developer;

    private int releaseYear;

}
