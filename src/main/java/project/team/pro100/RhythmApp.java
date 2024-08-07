/**
 * @author ajaarsma
 * @createdOn 7/31/2024 at 5:38 PM
 * @projectName UntitledRhythmGame
 * @packageName PACKAGE_NAME;
 */
package project.team.pro100;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.chart.XYChart;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import project.team.pro100.model.Factory;

import java.io.File;
import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGL.*;

public class RhythmApp extends GameApplication {
    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(512);
        gameSettings.setHeight(512);
        gameSettings.setTitle("Untitled Rhythm Game");
        gameSettings.setVersion("v0.1");
    }

    @Override protected void initGame() {
        getGameWorld().addEntityFactory(new Factory());
        spawn("ArrowBackground", 0, 0);

        spawn("ArrowRed",    0,   0);
        spawn("ArrowGreen",  64,  64);
        spawn("ArrowBlue",   192, 64);
        spawn("ArrowYellow", 256, 0);

        ArrayList<Entity> red    = new ArrayList<>();
        ArrayList<Entity> green  = new ArrayList<>();
        ArrayList<Entity> blue   = new ArrayList<>();
        ArrayList<Entity> yellow = new ArrayList<>();

        String mediaLocation = "src/main/resources/assets/music/RickRoll.wav";
        Media media = new Media(new File(mediaLocation).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setAudioSpectrumListener(((timestamp, duration, magnitudes, phases) -> {
            for (int i = 0; i < magnitudes.length; i++) {
                if (magnitudes[i] > -60 && magnitudes[i] < -50) {
                    red.add(spawn("ArrowRed", 0, 512));
                }
                else if (magnitudes[i] >= -50 && magnitudes[i] < -40) {
                    green.add(spawn("ArrowGreen", 64, 576));
                }
                else if (magnitudes[i] >= -40 && magnitudes[i] < -30) {
                    blue.add(spawn("ArrowBlue",    192, 576));
                }
                else if (magnitudes[i] >= -30 && magnitudes[i] < -20) {
                    yellow.add(spawn("ArrowYellow", 256, 512));
                }
            }
        }));

        mediaPlayer.play();

        run(() -> {
            for (Entity e : red) {
                e.translateY(-20);
            }

            for (Entity e : green) {
                e.translateY(-20);
            }

            for (Entity e : blue) {
                e.translateY(-20);
            }

            for (Entity e : yellow) {
                e.translateY(-20);
            }
        }, Duration.seconds(0.05));
    }

    //TODO (Minor Issue): Create switch statement for key inputs.
    //TODO (Minor Issue): Create more descriptive comments describing what key inputs do.
    @Override protected void initInput() {
        onKeyDown(KeyCode.W, () -> {
            System.out.println("W");
        });
        onKeyDown(KeyCode.UP, () -> {
            System.out.println("UP");
        });

        onKeyDown(KeyCode.A, () -> {
            System.out.println("A");
        });
        onKeyDown(KeyCode.LEFT, () -> {
            System.out.println("LEFT");
        });

        onKeyDown(KeyCode.S, () -> {
            System.out.println("S");
        });
        onKeyDown(KeyCode.DOWN, () -> {
            System.out.println("DOWN");
        });

        onKeyDown(KeyCode.D, () -> {
            System.out.println("D");
        });
        onKeyDown(KeyCode.RIGHT, () -> {
            System.out.println("RIGHT");
        });

        // Select.
        onKeyDown(KeyCode.SPACE, () -> {
            System.out.println("SPACE");
        });
        onKeyDown(KeyCode.ENTER, () -> {
            System.out.println("ENTER");
        });

        // Go back; open the pause menu.
        onKeyDown(KeyCode.BACK_SPACE, () -> {
            System.out.println("BACK_SPACE");
        });
        onKeyDown(KeyCode.ESCAPE, () -> {
            System.out.println("ESCAPE");
        });
    }
}