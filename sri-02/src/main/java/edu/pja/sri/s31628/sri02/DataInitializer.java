package edu.pja.sri.s31628.sri02;

import edu.pja.sri.s31628.sri02.model.Developer;
import edu.pja.sri.s31628.sri02.model.VideoGame;
import edu.pja.sri.s31628.sri02.repo.DeveloperRepository;
import edu.pja.sri.s31628.sri02.repo.VideoGameRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {

    private DeveloperRepository developerRepository;
    private VideoGameRepository videoGameRepository;
    public DataInitializer(DeveloperRepository developerRepository, VideoGameRepository videoGameRepository) {
        this.developerRepository = developerRepository;
        this.videoGameRepository = videoGameRepository;
    }
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
                .releaseYear(2015)
                .build();

        Developer d1 = Developer.builder()
                .name("Toby Fox")
                .description("Guy that made undertale")
                .build();

        Developer d2 = Developer.builder()
                .name("CD Projekt Red")
                .description("Polish game dev studio in Warsaw")
                .build();

        Developer d3 = Developer.builder()
                .name("Bethesda")
                .description("")
                .build();


        g1.setDeveloper(d1);
        g2.setDeveloper(d2);
        g2.setDeveloper(d3);

        developerRepository.save(d1);
        developerRepository.save(d2);
        developerRepository.save(d3);
        videoGameRepository.save(g1);
        videoGameRepository.save(g2);
        videoGameRepository.save(g3);
    }
}
