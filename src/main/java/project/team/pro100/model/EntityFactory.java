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
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.spawn;
import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class EntityFactory implements com.almasb.fxgl.entity.EntityFactory {
    //region Entity Spawns (Click to Expand)
    @Spawns("ArrowRed") public Entity newArrowRed(SpawnData data) {
        return entityBuilder(data)
                .view("arrows/ArrowRed.png")
                .build();
    }
    @Spawns("ArrowGreen") public Entity newArrowGreen(SpawnData data) {
        return entityBuilder(data)
                .view("arrows/ArrowGreen.png")
                .rotate(270)
                .build();
    }
    @Spawns("ArrowBlue") public Entity newArrowBlue(SpawnData data) {
        return entityBuilder(data)
                .view("arrows/ArrowBlue.png")
                .rotate(180)
                .build();
    }
    @Spawns("ArrowYellow") public Entity newArrowYellow(SpawnData data) {
        return entityBuilder(data)
                .view("arrows/ArrowYellow.png")
                .rotate(90)
                .build();
    }
    @Spawns("ArrowPreload") public Entity newArrowPreload(SpawnData data) {
        return entityBuilder(data)
                .view("arrows/ArrowRed.png")
                .build();
    }

    @Spawns("PlayerNeutral") public Entity newPlayerNeutral(SpawnData data) {
        return entityBuilder(data)
                .view("player/PlayerNeutral.png")
                .build();
    }
    @Spawns("PlayerNeutral2") public Entity newPlayerNeutral2(SpawnData data) {
        return entityBuilder(data)
                .view("player/PlayerNeutral2.png")
                .build();
    }
    @Spawns("PlayerUp") public Entity newPlayerUp(SpawnData data) {
        return entityBuilder(data)
                .view("player/PlayerUp.png")
                .build();
    }
    @Spawns("PlayerLeft") public Entity newPlayerLeft(SpawnData data) {
        return entityBuilder(data)
                .view("player/PlayerLeft.png")
                .build();
    }
    @Spawns("PlayerDown") public Entity newPlayerDown(SpawnData data) {
        return entityBuilder(data)
                .view("player/PlayerDown.png")
                .build();
    }
    @Spawns("PlayerRight") public Entity newPlayerRight(SpawnData data) {
        return entityBuilder(data)
                .view("player/PlayerRight.png")
                .build();
    }
    @Spawns("PlayerDead") public Entity newPlayerDead(SpawnData data) {
        return entityBuilder(data)
                .view("player/PlayerDead.png")
                .build();
    }

    @Spawns("Terrible") public Entity newTerrible(SpawnData data) {
        return entityBuilder(data)
                .view("ratings/Terrible.png")
                .build();
    }
    @Spawns("Okay") public Entity newOkay(SpawnData data) {
        return entityBuilder(data)
                .view("ratings/Okay.png")
                .build();
    }
    @Spawns("Good") public Entity newGood(SpawnData data) {
        return entityBuilder(data)
                .view("ratings/Good.png")
                .build();
    }
    @Spawns("Perfect") public Entity newPerfect(SpawnData data) {
        return entityBuilder(data)
                .view("ratings/Perfect.png")
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

    private static Entity player;
    public static Entity getPlayer() {
        return player;
    }
    public static void setPlayer(Entity newEntity) {
        if(newEntity != null) {
            player = newEntity;
        }
        else {
            throw new NullPointerException("newEntity is null");
        }
    }
    //endregion

    //region Graphics Methods (Click To Expand)
    public static void initGraphics() {
        getGameWorld().addEntityFactory(new EntityFactory());

        FXGL.getGameWorld().addEntity(FXGL.entityBuilder()
                .at(0, 0)
                .view("backgrounds/ArrowBackground.png")
                .buildAndAttach());

        FXGL.getGameWorld().addEntity(FXGL.entityBuilder()
                .at(256, 0)
                .view("backgrounds/StageBackground.png")
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

        player = spawn("PlayerNeutral", 330, 100);
        FXGL.getGameWorld().addEntity(player);
    }
    //endregion
}