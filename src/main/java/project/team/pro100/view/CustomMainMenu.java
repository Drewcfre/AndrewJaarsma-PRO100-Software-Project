/**
 * @author znye
 * @createdOn 8/13/2024 at 1:17 PM
 * @projectName AndrewJaarsma-PRO100-Software-Project
 * @packageName project.team.pro100.view;
 */

package project.team.pro100.view;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.jetbrains.annotations.NotNull;
import project.team.pro100.RhythmApp;

import java.io.File;

public class CustomMainMenu extends FXGLMenu{
    //region Methods (Click To Expand)
    public CustomMainMenu(MenuType type) {
        super(type);

        Text title = FXGL.getUIFactoryService().newText("Untitled Rhythm Game", Color.BLACK, 40.0);
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Music Files", "*.mp3", "*.wav"));
        Button startButton = new Button("Start Game");
        Button browseFilesButton = new Button("Search For Songs");

        VBox window = getWindow(title, startButton, browseFilesButton);

        startButton.setOnAction(e -> fireNewGame());

        browseFilesButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(null);
            RhythmApp.setMediaLoc(file);
        });

        getContentRoot().getChildren().add(window);
    }

    @NotNull private VBox getWindow(Text title, Button startButton, Button browseFilesButton) {
        VBox menuBox = new VBox(10, title, startButton, browseFilesButton);
        menuBox.setTranslateX(getAppWidth() / 2.0 - 225);
        menuBox.setTranslateY(getAppHeight() / 2.0 - 50);

        VBox window = new VBox(10, menuBox);
        window.setMinWidth(getAppWidth());
        window.setMinHeight(getAppHeight());
        window.setBackground(
            new Background(
                new BackgroundImage(
                    new Image("file:src/main/resources/assets/textures/mainBackground.png", true),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(
                            1.0,
                            1.0,
                            true,
                            true,
                            false,
                            false
                    )
                )
            )
        );
        return window;
    }
    //endregion
}
