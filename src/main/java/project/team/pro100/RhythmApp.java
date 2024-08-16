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
import project.team.pro100.model.Factory;
import project.team.pro100.view.SceneFactory;

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
    //endregion


    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override protected void initSettings(GameSettings gameSettings) {
        gameSettings.setMainMenuEnabled(true);
        gameSettings.setWidth(512);
        gameSettings.setHeight(512);
        gameSettings.setTitle("Untitled Rhythm Game");
        gameSettings.setVersion("v0.1");
        gameSettings.setSceneFactory(new SceneFactory());
    }

    @Override protected void initGame() {
        Factory.initGraphics();
        PauseTransition pause = new PauseTransition(Duration.seconds(1));

        run(() -> {

            GameController.updateArrows(5, -40);

            for (ArrayList arrow : arrows) System.out.print(arrow.size() + ", ");
            System.out.println();
        }, Duration.seconds(0.01));

        pause.setOnFinished(x -> {
        try {
            audioControllerPlayMusic.initAudioController("src/main/resources/assets/music/RickRoll.wav", 1450, true);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        });
        pause.play();
    }


    //TODO (Minor Issue): Create switch statement for key inputs.
    //TODO (Minor Issue): Create comments describing what key inputs do.
    @Override protected void initInput() {
        GameController.userInput();
    }

    @Override protected void onUpdate(double tpf) {
        for (int i = 0; i < 4; i++) {
            if (getList(i) != null && !getList(i).isEmpty()) {
                for (Entity arrow : getList(i)) {
                    arrow.translateY(-2);
                }
            }
        }

//        Entity preloadArrow = spawn("ArrowPreload", 0, 512);
//        preloadArrow.translateY(-5*tpf);
    }
}