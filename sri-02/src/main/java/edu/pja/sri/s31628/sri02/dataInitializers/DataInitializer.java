package edu.pja.sri.s31628.sri02.dataInitializers;

import edu.pja.sri.s31628.sri02.model.Developer;
import edu.pja.sri.s31628.sri02.model.VideoGame;
import edu.pja.sri.s31628.sri02.repo.DeveloperRepository;
import edu.pja.sri.s31628.sri02.repo.VideoGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@RequiredArgsConstructor
@Component
public class DataInitializer implements ApplicationRunner {

    private final DeveloperRepository developerRepository;
    private final VideoGameRepository videoGameRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        VideoGame g1 = VideoGame.builder()
                .title("Undertale")
                .releaseYear(2015)
                .build();

        VideoGame g2 = VideoGame.builder()
                .title("Wiedźmin 3")
                .releaseYear(2015)
                .build();

        VideoGame g3 = VideoGame.builder()
                .title("TES:V Skyrim")
                .releaseYear(2011)
                .build();

        VideoGame g4 = VideoGame.builder()
                .title("DOOM Eternal")
                .releaseYear(2020)
                .build();

        VideoGame g5 = VideoGame.builder()
                .title("Cyberpunk 2077")
                .releaseYear(2020)
                .build();

        Developer d1 = Developer.builder()
                .name("Toby Fox")
                .description("Guy that made Undertale")
                .videoGames(new HashSet<>())
                .build();

        Developer d2 = Developer.builder()
                .name("CD Projekt Red")
                .description("Polish game dev studio in Warsaw")
                .videoGames(new HashSet<>())
                .build();

        Developer d3 = Developer.builder()
                .name("Bethesda game studios")
                .description("American video game developer")
                .videoGames(new HashSet<>())
                .build();


        g1.setDeveloper(d1);
        d1.getVideoGames().add(g1);

        g2.setDeveloper(d2);
        d2.getVideoGames().add(g2);

        g3.setDeveloper(d3);
        d3.getVideoGames().add(g3);

        g4.setDeveloper(d3);
        d3.getVideoGames().add(g4);

        g5.setDeveloper(d2);
        d2.getVideoGames().add(g5);

        developerRepository.saveAll(Arrays.asList(d1,d2,d3));
        videoGameRepository.saveAll(Arrays.asList(g1,g2,g3,g4,g5));
    }
}
