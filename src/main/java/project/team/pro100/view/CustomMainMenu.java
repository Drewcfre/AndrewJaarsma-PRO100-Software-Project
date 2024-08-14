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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;

public class CustomMainMenu extends FXGLMenu{
    public CustomMainMenu(MenuType type) {
        super(type);

        Text title = FXGL.getUIFactoryService().newText("Untitled Rhythm Game", Color.BLACK, 40.0);
        FileChooser fileChooser = new FileChooser();
        Button startButton = new Button("Start Game");
        Button browseFilesButton = new Button("Search For Songs");

        VBox menuBox = new VBox(10, title, startButton, browseFilesButton);
        menuBox.setTranslateX(getAppWidth() / 2.0 - 225);
        menuBox.setTranslateY(getAppHeight() / 2.0 - 50);

        startButton.setOnAction(e -> fireNewGame());

        browseFilesButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(null);
        });

        getContentRoot().getChildren().add(menuBox);
    }
}
