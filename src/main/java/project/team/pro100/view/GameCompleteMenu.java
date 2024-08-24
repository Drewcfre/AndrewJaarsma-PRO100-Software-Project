package project.team.pro100.view;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.FontType;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import project.team.pro100.RhythmApp;

public class GameCompleteMenu extends FXGLMenu {
    private static final int misses = RhythmApp.getMisses();
    //region Methods (Click To Expand)
    public GameCompleteMenu() {
        super(MenuType.GAME_MENU);

        int score = RhythmApp.getScore();
//        int misses = RhythmApp.getMisses();

        ImageView resultImage = new ImageView();

        resultImage.setImage(FXGL.image(calculateGrade()));
        resultImage.setRotate(-10);
        resultImage.setY(100);


        String winString = String.format("Score: %d   Grade: ", score);

        Text title = FXGL.getUIFactoryService().newText("Song Complete!", Color.WHITE, FontType.MONO, 40.0);
        Text winText = FXGL.getUIFactoryService().newText(winString, Color.BLACK, FontType.MONO, 25.0);
        Button backToMenu = new Button("Back to Main Menu");

        HBox winLine = new HBox(winText, resultImage);
        winLine.setAlignment(Pos.CENTER);

        VBox window = createWindow(title, winLine, backToMenu);

        backToMenu.setOnAction(e -> FXGL.getGameController().gotoMainMenu());

        getContentRoot().getChildren().add(window);

    }

    private String calculateGrade() {
        // TODO: Add a grading system that incorporates user score.
        if (GameCompleteMenu.misses == 0) return "ratings/Perfect.png";
        else if (GameCompleteMenu.misses <= 5) return "ratings/Good.png";
        else if (GameCompleteMenu.misses <= 10) return "ratings/Okay.png";
        else return "ratings/Terrible.png";
    }

    private VBox createWindow(Text title, HBox hbox, Button backToMenu) {
        VBox menuBox = new VBox(10, title, hbox, backToMenu);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setTranslateY(FXGL.getAppHeight() / 2.0 - 50);

        VBox window = new VBox(10, menuBox);
        window.setMinWidth(FXGL.getAppWidth());
        window.setMinHeight(FXGL.getAppHeight());


        window.setStyle("-fx-background-color: rgba(255, 192, 203, .85);");

        return window;
    }
//endregion
}