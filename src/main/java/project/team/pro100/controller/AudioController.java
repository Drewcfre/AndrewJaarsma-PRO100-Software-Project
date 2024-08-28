package project.team.pro100.controller;

import javafx.animation.PauseTransition;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import project.team.pro100.RhythmApp;
import project.team.pro100.model.Arrow;

import java.io.File;

import static com.almasb.fxgl.dsl.FXGL.spawn;
import static project.team.pro100.RhythmApp.setEndOfFile;

public class AudioController {
    //region Variables/Getters/Setters (Click To Expand)
    private static MediaPlayer playAudio;
    private static MediaPlayer soundValueOutput;
    //endregion

    //region Methods (Click To Expand)
    public void initAudioController(File mediaLocation, int delayInMilliseconds, boolean hasListener) throws InterruptedException {
        Media media = new Media(mediaLocation.toURI().toString());
        soundValueOutput = new MediaPlayer(media);
        soundValueOutput.setMute(true);
        playAudio = new MediaPlayer(media);
        playAudio.setVolume(0.5);

        playAudio.setOnEndOfMedia(() -> {
            setEndOfFile(true);
            playAudio.dispose();
        });

        if(hasListener) {
            soundValueOutput.setAudioSpectrumListener(((timestamp, duration, magnitudes, phases) -> {
                for (int i = 0; i < magnitudes.length; i+=50) {
                    if ((int) magnitudes[i] == -57) {
                        RhythmApp.getList(0).add(new Arrow(spawn("ArrowRed", 0, 512)));
                        RhythmApp.setTotalArrows(RhythmApp.getTotalArrows() + 1);
                    }
                    else if ((int) magnitudes[i] >= -50 && (int) magnitudes[i] <= -47) {
                        RhythmApp.getList(1).add(new Arrow(spawn("ArrowGreen", 64, 576)));
                        RhythmApp.setTotalArrows(RhythmApp.getTotalArrows() + 1);
                    }
                    else if ((int) magnitudes[i] >= -32 && (int) magnitudes[i] <= -31) {
                        RhythmApp.getList(2).add(new Arrow(spawn("ArrowBlue", 192, 576)));
                        RhythmApp.setTotalArrows(RhythmApp.getTotalArrows() + 1);
                    }
                    else if ((int) magnitudes[i] >= -27 && (int) magnitudes[i] <= -26) {
                        RhythmApp.getList(3).add(new Arrow(spawn("ArrowYellow", 256, 512)));
                        RhythmApp.setTotalArrows(RhythmApp.getTotalArrows() + 1);
                    }
                    if ((int) magnitudes[i] >= -26 && (int) magnitudes[i] <= -30||(int) magnitudes[i] >= -31 && (int) magnitudes[i] <= -40){
                        System.out.println(magnitudes[i]);
                    }
                }
            }));
        }

        soundValueOutput.play();

        PauseTransition pause = new PauseTransition(Duration.millis(delayInMilliseconds));
        pause.setOnFinished(e -> playAudio.play());
        pause.play();

    }

    public static void stopStartSound(boolean paused) {
        if(paused) {
            playAudio.pause();
            soundValueOutput.pause();
        }
        else {
            playAudio.play();
            soundValueOutput.play();
        }
    }

    public static void setVolume(double volume) {
        playAudio.setVolume(volume);
    }
    //endregion
}