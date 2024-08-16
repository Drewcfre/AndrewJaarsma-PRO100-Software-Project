/**
 * @author ajaarsma
 * @createdOn 7/31/2024 at 6:43 PM
 * @projectName UntitledRhythmGame
 * @packageName controller;
 */
package project.team.pro100.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.io.File;

public class FileController {
    private MediaPlayer mediaPlayer;

    public MediaPlayer playAudioFromPath(String filePath) {
        if (!isValidAudioFile(filePath)) {
            System.out.println("Invalid audio file: " + filePath);
            return null;
        }

        Media media = new Media(new File(filePath).toURI().toString());

        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        return mediaPlayer;
    }

    private boolean isValidAudioFile(String filePath) {
        File file = new File(filePath);
        return file.exists() && (filePath.endsWith(".wav") || filePath.endsWith(".mp3") || filePath.endsWith(".aac"));
    }


    public void changeAudio(String newFilePath) {
        playAudioFromPath(newFilePath);
    }
}



