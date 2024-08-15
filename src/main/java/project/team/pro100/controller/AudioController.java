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
                float[] soundValues =  magnitudes;
                for (int i = 0; i < magnitudes.length; i+=50) {
                    if ((int) soundValues[i] == -57) {
                        RhythmApp.getList(0).add(spawn("ArrowRed", 0, 512));
                    }
                    else if ((int)soundValues[i] >= -50 && (int)soundValues[i] <= -47) {
                        RhythmApp.getList(1).add(spawn("ArrowGreen", 64, 576));
                    }
                    else if ((int)soundValues[i] >= -31 && (int)soundValues[i] <= -32) {
                        RhythmApp.getList(2).add(spawn("ArrowBlue",    192, 576));
                    }
                    else if ((int) soundValues[i] == -26) {
                        RhythmApp.getList(3).add(spawn("ArrowYellow", 256, 512));
                    }
//                    if ((int) soundValues[i] >= -30 && (int)soundValues[i] < -20) {
//                        System.out.println((int)soundValues[i]);
//                    }
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