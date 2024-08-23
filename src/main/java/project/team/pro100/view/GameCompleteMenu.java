package project.team.pro100.view;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.FontType;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import project.team.pro100.RhythmApp;

public class GameCompleteMenu extends FXGLMenu {

    public GameCompleteMenu() {
        super(MenuType.GAME_MENU);


        int score = RhythmApp.getScore();
        int misses = RhythmApp.getMisses();

        String grade = calculateGrade(misses);

        String winString = String.format("Score: %d   Grade: %s", score, grade);

        Text title = FXGL.getUIFactoryService().newText("Song Complete!", Color.WHITE, FontType.MONO, 40.0);
        Text winText = FXGL.getUIFactoryService().newText(winString, Color.BLACK, FontType.MONO, 30.0);
        Button backToMenu = new Button("Back to Main Menu");

        VBox window = createWindow(title, winText, backToMenu);

        backToMenu.setOnAction(e -> FXGL.getGameController().gotoMainMenu());

        getContentRoot().getChildren().add(window);
    }

    private String calculateGrade(int misses) {
        if (misses == 0) return "A";
        else if (misses <= 5) return "B";
        else if (misses <= 10) return "C";
        else return "D";
    }

    private VBox createWindow(Text title, Text winText, Button backToMenu) {
        VBox menuBox = new VBox(10, title, winText, backToMenu);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setTranslateY(FXGL.getAppHeight() / 2.0 - 50);

        VBox window = new VBox(10, menuBox);
        window.setMinWidth(FXGL.getAppWidth());
        window.setMinHeight(FXGL.getAppHeight());

        window.setStyle("-fx-background-color: rgba(255, 192, 203, .85);");

        return window;
    }
}
