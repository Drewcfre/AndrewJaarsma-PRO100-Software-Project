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
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.jetbrains.annotations.NotNull;
import project.team.pro100.RhythmApp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class CustomMainMenu extends FXGLMenu {
    private File selectedFile;
    //region Methods (Click To Expand)
    public CustomMainMenu(MenuType type) {
        super(type);

        Text title = FXGL.getUIFactoryService().newText("Untitled Rhythm Game", Color.BLACK, FontType.MONO, 40.0);
        Text error = FXGL.getUIFactoryService().newText("Please Choose A Song Before Playing", Color.RED, FontType.MONO, 20.0);
        error.setVisible(false);

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Music Files", "*.mp3", "*.wav"));

        Button startButton = new Button("Start Game");
        Button browseFilesButton = new Button("Search For Songs");
        Button exitButton = new Button("Exit");

        VBox window = getWindow(title, startButton, browseFilesButton, exitButton, error);

        startButton.setOnAction(e -> {
            if (selectedFile != null) fireNewGame();
            else error.setVisible(true);
        });

        browseFilesButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                saveFileToGameFolder(file);
                selectedFile = new File("SONGS_FOLDER/" + file.getName());
                RhythmApp.setMediaLocation(new File("SONGS_FOLDER/" + file.getName()));
                error.setVisible(false);
            }
        });

        exitButton.setOnAction(e -> fireExit());

        getContentRoot().getChildren().add(window);
    }

    private void saveFileToGameFolder(File file) {
        try {
            File dir = new File("SONGS_FOLDER");
            if (!dir.mkdirs()) {
                System.out.println("Could not create folder " + dir.getAbsolutePath());
            }

            Path destination = Path.of("SONGS_FOLDER", file.getName());
            Files.copy(file.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @NotNull
    private VBox getWindow(Text title, Button startButton, Button browseFilesButton, Button exitButton, Text error) {
        VBox menuBox = new VBox(10, title, startButton, browseFilesButton, exitButton, error);
        menuBox.setAlignment(Pos.CENTER);
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