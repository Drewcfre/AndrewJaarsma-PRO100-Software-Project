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
import com.almasb.fxgl.ui.FontType;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;
import project.team.pro100.RhythmApp;

public class CustomPauseMenu extends FXGLMenu {
    //region Methods (Click To Expand)
    public CustomPauseMenu(MenuType type) {
        super(type);

        Text title = FXGL.getUIFactoryService().newText("Untitled Rhythm Game", Color.WHITE, FontType.MONO, 40.0);

        Button resumeButton = new Button("Resume Game");
        resumeButton.setOnAction(e -> {
            RhythmApp.resumeGame();
            fireResume();
        });

        Button exitButton = new Button("Exit Game");
        exitButton.setOnAction(e -> fireExit());

        VBox window = getWindow(title, resumeButton, exitButton);
        getContentRoot().getChildren().add(window);
    }

    @NotNull private VBox getWindow(Text title, Button startButton, Button browseFilesButton) {
        VBox menuBox = new VBox(10, title, startButton, browseFilesButton);
        menuBox.setTranslateY(getAppHeight() / 2.0 - 50);
        menuBox.setAlignment(Pos.CENTER);

        VBox window = new VBox(10, menuBox);
        window.setMinWidth(getAppWidth());
        window.setMinHeight(getAppHeight());

        window.setStyle("-fx-background-color: rgba(0, 0, 0, 0.9);");

        return window;
    }
    //endregion
}