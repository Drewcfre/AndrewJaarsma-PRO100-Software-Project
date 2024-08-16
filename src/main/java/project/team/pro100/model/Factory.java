/**
 * @author ajaarsma
 * @createdOn 7/31/2024 at 6:41 PM
 * @projectName UntitledRhythmGame
 * @packageName model;
 */
package project.team.pro100.model;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.spawn;
import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class Factory implements EntityFactory {
    //region Entity Spawns (Click to Expand)
    @Spawns("ArrowBackground") public Entity newArrowBackground(SpawnData data) {
        return entityBuilder(data)
                .view("ArrowBackground.png")
                .build();
    }
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

    //region Graphics Methods (Click To Expand)
    public static void initGraphics() {
        getGameWorld().addEntityFactory(new Factory());

        spawn("ArrowBackground", 0, 0);

        spawn("ArrowRed",    0,   0);
        spawn("ArrowGreen",  64,  64);
        spawn("ArrowBlue",   192, 64);
        spawn("ArrowYellow", 256, 0);
    }
    //endregion
}