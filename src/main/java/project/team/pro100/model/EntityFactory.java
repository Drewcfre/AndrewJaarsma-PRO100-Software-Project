/**
 * @author ajaarsma
 * @createdOn 7/31/2024 at 6:41 PM
 * @projectName UntitledRhythmGame
 * @packageName model;
 */

package project.team.pro100.model;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.spawn;
import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class EntityFactory implements com.almasb.fxgl.entity.EntityFactory {
    //region Entity Spawns (Click to Expand)
    // IntelliJ wrongly flags these methods as unused.
    // Suppress the unused inspection in this file to get rid of the warnings.
    @Spawns("ArrowRed") public Entity newArrowRed(SpawnData data) {
        return entityBuilder(data)
                .view("ArrowRed.png")
                .build();
    }
    @Spawns("ArrowGreen") public Entity newArrowGreen(SpawnData data) {
        return entityBuilder(data)
                .view("ArrowGreen.png")
                .rotate(270)
                .build();
    }
    @Spawns("ArrowBlue") public Entity newArrowBlue(SpawnData data) {
        return entityBuilder(data)
                .view("ArrowBlue.png")
                .rotate(180)
                .build();
    }
    @Spawns("ArrowYellow") public Entity newArrowYellow(SpawnData data) {
        return entityBuilder(data)
                .view("ArrowYellow.png")
                .rotate(90)
                .build();
    }
    @Spawns("ArrowPreload") public Entity newArrowPreload(SpawnData data){
        return entityBuilder(data)
                .view("ArrowRed.png")
                .build();
    }

    @Spawns("Terrible") public Entity newTerrible(SpawnData data) {
        return entityBuilder(data)
                .view("Terrible.png")
                .build();
    }
    @Spawns("Okay") public Entity newOkay(SpawnData data) {
        return entityBuilder(data)
                .view("Okay.png")
                .build();
    }
    @Spawns("Good") public Entity newGood(SpawnData data) {
        return entityBuilder(data)
                .view("Good.png")
                .build();
    }
    @Spawns("Perfect") public Entity newPerfect(SpawnData data) {
        return entityBuilder(data)
                .view("Perfect.png")
                .build();
    }
    //endregion

    //region Variables/Getters/Setters (Click To Expand)
    private static Text score;
    public static void setScoreCount(int text) {
        score.setText("Score: " + text);
    }

    private static Text misses;
    public static void setMissCount(int num) {
        misses.setText("Misses: " + num);
    }

    private static Texture player;
    public static void setPlayer(String newTexture) {
        if(newTexture == null) throw new NullPointerException("newTexture is null");
        else if(newTexture.isEmpty()) throw new NullPointerException("newTexture is empty");
        else player = FXGL.getAssetLoader().loadTexture(newTexture);
    }
    //endregion

    //region Graphics Methods (Click To Expand)
    public static void initGraphics() {
        getGameWorld().addEntityFactory(new EntityFactory());

        FXGL.getGameWorld().addEntity(FXGL.entityBuilder()
                .at(0, 0)
                .view("ArrowBackground.png")
                .buildAndAttach());

        FXGL.getGameWorld().addEntity(FXGL.entityBuilder()
                .at(256, 0)
                .view("StageBackground.png")
                .buildAndAttach());

        spawn("ArrowRed",    0,   0);
        spawn("ArrowGreen",  64,  64);
        spawn("ArrowBlue",   192, 64);
        spawn("ArrowYellow", 256, 0);

        score = new Text();
        score.setText("Score: 0");
        score.setTranslateX(260);
        score.setTranslateY(10);
        score.setStyle("-fx-fill: white;");
        FXGL.getGameScene().addUINode(score);

        misses = new Text();
        misses.setText("Misses: 0");
        misses.setTranslateX(260);
        misses.setTranslateY(30);
        misses.setStyle("-fx-fill: white;");
        FXGL.getGameScene().addUINode(misses);

        player = FXGL.getAssetLoader().loadTexture("PlayerNeutral.png");
        player.setTranslateX(320);
        player.setTranslateY(210);
        FXGL.getGameScene().addUINode(player);
    }
    //endregion
}