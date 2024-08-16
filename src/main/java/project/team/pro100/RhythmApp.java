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
import javafx.util.Duration;
import project.team.pro100.controller.AudioController;
import project.team.pro100.controller.GameController;
import project.team.pro100.model.Factory;

import java.util.ArrayList;
import java.util.stream.Stream;

import static com.almasb.fxgl.dsl.FXGL.*;

public class RhythmApp extends GameApplication {
    //region Constants (Click To Expand)
    private final AudioController audioControllerGenerateArrows = new AudioController();
    private final AudioController audioControllerPlayMusic = new AudioController();
    //endregion

    //region Variables/Getters/Setters (Click To Expand)
    private static final ArrayList[] arrows = Stream.generate(
            () -> new ArrayList()
    ).limit(4).toArray(ArrayList[]::new);
    public static ArrayList<Entity> getList(int type) {
        return arrows[type];
    }
    public static void removeArrow(int type, Entity entity) {
        arrows[type].remove(entity);
    }

    private static Entity message = null;
    public static Entity getMessage() {
        return message;
    }

    public static void setMessage(Entity newMessage) {
        message = newMessage;
    }
    //endregion


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
        Factory.initGraphics();

        try {
            audioControllerPlayMusic.initAudioController("src/main/resources/assets/music/RickRoll.wav", 0, true, false);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        run(() -> {
            GameController.updateArrows(10, -40);

            for (ArrayList arrow : arrows) System.out.print(arrow.size() + ", ");
            System.out.println();
        }, Duration.seconds(0.01));
    }

    //TODO (Minor Issue): Create switch statement for key inputs.
    //TODO (Minor Issue): Create comments describing what key inputs do.
    @Override protected void initInput() {
        GameController.userInput();
    }
}