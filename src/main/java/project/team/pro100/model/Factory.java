/**
 * @author ajaarsma
 * @createdOn 7/31/2024 at 6:41 PM
 * @projectName UntitledRhythmGame
 * @packageName model;
 */
package project.team.pro100.model;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point2D;

public class Factory {
    @Spawns("Object") public Entity newObject(SpawnData data) {
        return FXGL.entityBuilder(data)
                .view("arrow.png")
                .with(new ProjectileComponent(new Point2D(1,0), 50))
                .build();
    }
    // Objects such as arrows, cover art, or fonts will have blueprints here.
}