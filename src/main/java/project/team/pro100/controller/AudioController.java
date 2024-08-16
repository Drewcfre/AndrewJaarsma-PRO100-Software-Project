package project.team.pro100.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import project.team.pro100.RhythmApp;

import java.io.File;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class AudioController {
    private MediaPlayer mediaPlayer;
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void initAudioController(String mediaLocation, int delayInMilliseconds, boolean hasListener, boolean isMuted)
            throws InterruptedException {
        Media media = new Media(new File(mediaLocation).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

//        try {
//            wait(delayInMilliseconds);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        if(hasListener) {
            mediaPlayer.setAudioSpectrumListener(((timestamp, duration, magnitudes, phases) -> {
                for (int i = 0; i < magnitudes.length; i+=70) {
                    if (magnitudes[i] > -59 && magnitudes[i] < -50) {
                        RhythmApp.getList(0).add(spawn("ArrowRed", 0, 512));
                    }
                    else if (magnitudes[i] >= -50 && magnitudes[i] < -40) {
                        RhythmApp.getList(1).add(spawn("ArrowGreen", 64, 576));
                    }
                    else if (magnitudes[i] >= -40 && magnitudes[i] < -30) {
                        RhythmApp.getList(2).add(spawn("ArrowBlue",    192, 576));
                    }
                    else if (magnitudes[i] >= -30 && magnitudes[i] < -20) {
                        RhythmApp.getList(3).add(spawn("ArrowYellow", 256, 512));
                    }
                }
            }));
        }

        mediaPlayer.play();
        mediaPlayer.setMute(isMuted);
    }
}