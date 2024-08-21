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
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import project.team.pro100.controller.AudioController;
import project.team.pro100.controller.GameController;
import project.team.pro100.model.Arrow;
import project.team.pro100.model.EntityFactory;
import project.team.pro100.view.SceneFactory;

import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGL.*;

public class RhythmApp extends GameApplication {

    public static void pauseGame() {
        AudioController.stopStartSound(true);
        isPaused = true;
    }

    public static void resumeGame() {
        AudioController.stopStartSound(false);
        isPaused = false;
    }
    //region Variables/Getters/Setters (Click To Expand)
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
    //endregion

    //region Methods (Click To Expand)
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    private static boolean isPaused;
    private static final int delayInMilliseconds = 1450;

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

            //TODO: Arrows in the array lists are slowly increasing when they should be getting deleted.
            for (ArrayList<Arrow> arrow : arrows) System.out.print(arrow.size() + ", ");
            System.out.println();


        }, Duration.seconds(0.01));
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(x -> {
            try {
                audioController.initAudioController("src/main/resources/assets/music/sugar.mp3", delayInMilliseconds, true);
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
        if (isPaused) return;
        else {
            for (int i = 0; i < 4; i++) {
                if (getList(i) != null && !getList(i).isEmpty()) {
                    for (Arrow arrow : getList(i)) {
                        arrow.getArrow().translateY(-2);
                    }
                }
            }
        }
    }
    //endregion
}