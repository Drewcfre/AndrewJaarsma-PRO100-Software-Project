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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CustomMainMenu extends FXGLMenu{
    //region Methods (Click To Expand)
    public CustomMainMenu(MenuType type) {
        super(type);

        Background background = getBackground();
        Text title = FXGL.getUIFactoryService().newText("Untitled Rhythm Game", Color.BLACK, 40.0);
        FileChooser fileChooser = new FileChooser();
        Button startButton = new Button("Start Game");
        Button browseFilesButton = new Button("Search For Songs");

        VBox menuBox = new VBox(10, title, startButton, browseFilesButton);
        menuBox.setTranslateX(getAppWidth() / 2.0 - 225);
        menuBox.setTranslateY(getAppHeight() / 2.0 - 50);
        menuBox.setBackground(background);

        startButton.setOnAction(e -> fireNewGame());

        browseFilesButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(null);
        });

        getContentRoot().getChildren().add(menuBox);
    }

    @NotNull private static Background getBackground() {
        Image image;

        try {
            FileInputStream input = new FileInputStream("src/main/resources/assets/textures/wasp.gif");
            image = new Image(input);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT);
        return new Background(backgroundImage);
    }
    //endregion
}
