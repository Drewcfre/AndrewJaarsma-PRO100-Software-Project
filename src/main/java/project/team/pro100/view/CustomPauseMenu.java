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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.jetbrains.annotations.NotNull;
import project.team.pro100.RhythmApp;

import java.io.File;
import java.io.FileInputStream;

public class CustomPauseMenu extends FXGLMenu{
    //region Methods (Click To Expand)
    public CustomPauseMenu(MenuType type) {
        super(type);
        FileChooser fileChooser = new FileChooser();
        Image image = null;


        try {
            FileInputStream input = new FileInputStream("src/main/resources/assets/textures/mainBackground.png");
            image = new Image(input);
        }catch (Exception e){
            e.printStackTrace();
        }

        Pane rootPane = getRootPane(image);

        Text title = FXGL.getUIFactoryService().newText("Untitled Rhythm Game", Color.BLACK, 40.0);
        Button resumeButton = new Button("Resume Game");
        Button exitButton = new Button("Exit Game");

        VBox buttonBox = new VBox(10, resumeButton, exitButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox menuBox = new VBox(10, title, buttonBox);
        menuBox.setTranslateX(getAppWidth() / 2.0 - 225);
        menuBox.setTranslateY(getAppHeight() / 2.0 - 50);

        resumeButton.setOnAction(e -> {
                RhythmApp.resumeGame();
                fireResume();
    });

        exitButton.setOnAction(e -> {
            fireExit();
        });

        rootPane.getChildren().addAll(menuBox);

        getContentRoot().getChildren().add(rootPane);
    }

    private @NotNull Pane getRootPane(Image image) {
        BackgroundSize backgroundSize = new BackgroundSize(100,
                100,
                true,
                true,
                true,
                false
        );

        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);

        Background background = new Background(backgroundImage);

        Pane rootPane = new Pane();
        rootPane.setPrefSize(getAppWidth(),getAppHeight());
        rootPane.setBackground(background);
        rootPane.setOpacity(0.9);
        return rootPane;
    }
    //endregion
}
