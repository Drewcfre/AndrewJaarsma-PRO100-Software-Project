/**
 * @author ajaarsma
 * @createdOn 7/31/2024 at 5:38 PM
 * @projectName UntitledRhythmGame
 * @packageName PACKAGE_NAME;
 */
package project.team.pro100;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import project.team.pro100.model.Factory;

import static com.almasb.fxgl.dsl.FXGL.*;

public class RhythmApp extends GameApplication {
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

        var player = spawn("ArrowGreen", 230, 300);

        run(() -> {
            player.translateY(-20);
        }, Duration.seconds(1));
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