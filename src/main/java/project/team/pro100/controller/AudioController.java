package project.team.pro100.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
        playAudio = new MediaPlayer(media);

        //wait(delayInMilliseconds);

        if(hasListener) {
            soundValueOutput.setAudioSpectrumListener(((timestamp, duration, magnitudes, phases) -> {
                for (int i = 0; i < magnitudes.length; i+=70) {
                    if (magnitudes[i] > -59.5 && magnitudes[i] < -54) {
                        RhythmApp.getList(0).add(spawn("ArrowRed", 0, 512));
                    }
                    else if (magnitudes[i] >= -52 && magnitudes[i] < -48) {
                        RhythmApp.getList(1).add(spawn("ArrowGreen", 64, 576));
                    }
                    else if (magnitudes[i] >= -46 && magnitudes[i] < -43) {
                        RhythmApp.getList(2).add(spawn("ArrowBlue",    192, 576));
                    }
                    else if (magnitudes[i] >= -39 && magnitudes[i] < -38) {
                        RhythmApp.getList(3).add(spawn("ArrowYellow", 256, 512));
                    }
                }
            }));
        }

        soundValueOutput.setMute(true);
        soundValueOutput.play();
        playAudio.play();
    }
}