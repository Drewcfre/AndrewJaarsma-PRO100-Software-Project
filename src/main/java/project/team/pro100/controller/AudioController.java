package project.team.pro100.controller;

import com.almasb.fxgl.dsl.FXGL;
import javafx.animation.PauseTransition;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import project.team.pro100.RhythmApp;

import java.io.File;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class AudioController {
    private MediaPlayer soundValueOutput;
    private MediaPlayer playAudio;

    public void initAudioController(String mediaLocation, int delayInMilliseconds, boolean hasListener)
            throws InterruptedException {
        Media media = new Media(new File(mediaLocation).toURI().toString());
        soundValueOutput = new MediaPlayer(media);
        soundValueOutput.setMute(true);
        playAudio = new MediaPlayer(media);

        //wait(delayInMilliseconds);
        if(hasListener) {
            soundValueOutput.setAudioSpectrumListener(((timestamp, duration, magnitudes, phases) -> {
                for (int i = 0; i < magnitudes.length; i+=50) {
                    if (magnitudes[i] > -58 && magnitudes[i] < -57) {
                        RhythmApp.getList(0).add(spawn("ArrowRed", 0, 512));
                    }
                    else if (magnitudes[i] >= -48 && magnitudes[i] < -44) {
                        RhythmApp.getList(1).add(spawn("ArrowGreen", 64, 576));
                    }
                    else if (magnitudes[i] >= -38 && magnitudes[i] < -36) {
                        RhythmApp.getList(2).add(spawn("ArrowBlue",    192, 576));
                    }
                    else if (magnitudes[i] >= -28 && magnitudes[i] < -24) {
                        RhythmApp.getList(3).add(spawn("ArrowYellow", 256, 512));
                    }
                }
            }));
        }

        soundValueOutput.play();

        PauseTransition pause = new PauseTransition(Duration.millis(delayInMilliseconds));
        pause.setOnFinished(e -> {
            playAudio.play();
        });
        pause.play();

    }
}