package edu.pja.sri.s31628.sri02;

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
    }
}
