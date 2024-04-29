package edu.pja.sri.s31628.sri02.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    private String description;

    @OneToMany(mappedBy = "developer")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<VideoGame> videoGames = new HashSet<>();


}
