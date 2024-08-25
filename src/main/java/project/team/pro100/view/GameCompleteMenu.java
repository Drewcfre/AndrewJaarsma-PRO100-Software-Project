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
    //region Methods (Click To Expand)
    public GameCompleteMenu() {
        super(MenuType.GAME_MENU);

        int playerScore = RhythmApp.getScore();
        int maxScore = RhythmApp.getTotalArrows()*100;
        int misses = RhythmApp.getMisses();

        ImageView resultImage = new ImageView();

        resultImage.setImage(FXGL.image(calculateGrade()));
        resultImage.setRotate(-20);
        resultImage.setY(100);
        resultImage.setFitWidth(100);
        resultImage.setFitHeight(100);


        String winString1 = String.format("Score: %d/%d   Grade: ", playerScore, maxScore);
        String winString2 = String.format("Misses: %d", misses);

        Text title = FXGL.getUIFactoryService().newText("Song Complete!", Color.WHITE, FontType.MONO, 40.0);
        Text winText1 = FXGL.getUIFactoryService().newText(winString1, Color.BLACK, FontType.MONO, 25.0);
        Text winText2 = FXGL.getUIFactoryService().newText(winString2, Color.RED, FontType.MONO, 25.0);
        Button backToMenu = new Button("Back to Main Menu");

        HBox resultsLine1 = new HBox(winText1, resultImage);
        HBox resultsLine2 = new HBox(winText2);
        resultsLine1.setAlignment(Pos.CENTER);
        resultsLine2.setAlignment(Pos.CENTER);

        VBox window = createWindow(title, resultsLine1, resultsLine2, backToMenu);

        backToMenu.setOnAction(e -> FXGL.getGameController().gotoMainMenu());

        getContentRoot().getChildren().add(window);

    }

    private String calculateGrade() {
        double percentage = (double) RhythmApp.getScore() / (RhythmApp.getTotalArrows() * 100) * 100;
        // TODO: Add a grading system that incorporates user score.
        if (percentage >= 70) return "ratings/Perfect.png";
        else if (percentage >= 60) return "ratings/Good.png";
        else if (percentage >= 50) return "ratings/Okay.png";
        else return "ratings/Terrible.png";
    }

    private VBox createWindow(Text title, HBox hBox1, HBox hBox2, Button backToMenu) {
        VBox menuBox = new VBox(10, title, hBox1, hBox2,  backToMenu);
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