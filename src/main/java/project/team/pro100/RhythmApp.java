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
import static com.almasb.fxgl.dsl.FXGL.*;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

import project.team.pro100.controller.*;
import project.team.pro100.model.*;
import project.team.pro100.view.SceneFactory;

import java.io.File;
import java.util.ArrayList;

public class RhythmApp extends GameApplication {
    //region Variables/Getters/Setters (Click To Expand)
    public static void pauseGame() {
        AudioController.stopStartSound(true);
        isPaused = true;
    }

    public static void resumeGame() {
        AudioController.stopStartSound(false);
        isPaused = false;
    }

    private static File mediaLocation;

    public static File getMediaLocation() {
        return mediaLocation;
    }

    public static void setMediaLocation(File newMediaLocation) {
        if (newMediaLocation != null) mediaLocation = newMediaLocation;
        else throw new IllegalArgumentException("newMediaLoc is null");
    }

    private final AudioController audioController = new AudioController();

    // A list of 4 array lists that hold the arrow objects.
    private static final ArrayList<ArrayList<Arrow>> arrows = new ArrayList<>();

    public static ArrayList<Arrow> getList(int type) {
        return arrows.get(type);
    }

    // Displays a graphic on the side of the screen that rates the accuracy of your last input.
    private static Entity message = null;

    public static Entity getMessage() {
        return message;
    }

    public static void setMessage(Entity newMessage) {
        if (newMessage != null) message = newMessage;
        else throw new NullPointerException("newMessage is null");
    }

    private static int misses;

    public static int getMisses() {
        return misses;
    }

    public static void setMisses(int miss) {
        misses = miss;
    }

    private static int score;

    public static int getScore() {
        return score;
    }

    public static void setScore(int num) {
        score = num;
    }

    private static int totalArrows;
    public static int getTotalArrows() {
        return totalArrows;
    }
    public static void setTotalArrows(int totalArrows) {
        RhythmApp.totalArrows = totalArrows;
    }

    private boolean endOfFile = false;
    //endregion

    //region Methods (Click To Expand)
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    private static boolean isPaused;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(512);
        gameSettings.setHeight(512);
        gameSettings.setTitle("Untitled Rhythm Game");
        gameSettings.setVersion("v0.1");

        gameSettings.setSceneFactory(new SceneFactory());
        gameSettings.setMainMenuEnabled(true);
    }

    @Override
    protected void initGame() {
        for (int i = 0; i < 4; i++) arrows.add(new ArrayList<>());

        EntityFactory.initGraphics();

        run(() -> {
            GameController.updateArrows(-40);

            if(arrows.getFirst().isEmpty() && arrows.get(1).isEmpty() &&
            arrows.get(2).isEmpty() && arrows.get(3).isEmpty() && endOfFile) {
                //TODO: Add endgame logic here.
                int perfectScore = getTotalArrows() * 100;
                System.out.println(perfectScore);
            }
        }, Duration.seconds(0.01));

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(x -> {
            try {
                audioController.initAudioController(getMediaLocation(), 1450, true);
                audioController.getPlayAudio().setOnEndOfMedia(() -> endOfFile = true);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        pause.play();
    }

    @Override
    protected void initInput() {
        GameController.userInput();
    }

    @Override
    protected void onUpdate(double tpf) {
        if (!isPaused)  {
            for (int i = 0; i < 4; i++) {
                if (getList(i) != null && !getList(i).isEmpty()) {
                    for (Arrow arrow : getList(i)) {
                        arrow.getArrow().translateY(-2);
                    }
                }
            }
        }
        //endregion
    }
}