package project.team.pro100.controller;

import javafx.animation.PauseTransition;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import project.team.pro100.RhythmApp;
import project.team.pro100.model.Arrow;

import java.io.File;
import java.net.MalformedURLException;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class AudioController {
    //region Variables/Getters/Setters (Click To Expand)
    private MediaPlayer playAudio;
    //endregion

    //region Methods (Click To Expand)
    public void initAudioController(File mediaLocation, int delayInMilliseconds, boolean hasListener) throws InterruptedException {
        Media media = new Media(mediaLocation.toURI().toString());
        MediaPlayer soundValueOutput = new MediaPlayer(media);
        soundValueOutput.setMute(true);
        playAudio = new MediaPlayer(media);

        if(hasListener) {
            soundValueOutput.setAudioSpectrumListener(((timestamp, duration, magnitudes, phases) -> {
                for (int i = 0; i < magnitudes.length; i+=50) {
                    if ((int) magnitudes[i] == -57) {
                        RhythmApp.getList(0).add(new Arrow(spawn("ArrowRed", 0, 512)));
                    }
                    else if ((int) magnitudes[i] >= -50 && (int) magnitudes[i] <= -47) {
                        RhythmApp.getList(1).add(new Arrow(spawn("ArrowGreen", 64, 576)));
                    }
                    else if ((int) magnitudes[i] >= -31 && (int) magnitudes[i] <= -32) {
                        RhythmApp.getList(2).add(new Arrow(spawn("ArrowBlue", 192, 576)));
                    }
                    else if ((int) magnitudes[i] == -26) {
                        RhythmApp.getList(3).add(new Arrow(spawn("ArrowYellow", 256, 512)));
                    }
                }
            }));
        }

        soundValueOutput.play();

        PauseTransition pause = new PauseTransition(Duration.millis(delayInMilliseconds));
        pause.setOnFinished(e -> playAudio.play());
        pause.play();
    }
    //endregion
}