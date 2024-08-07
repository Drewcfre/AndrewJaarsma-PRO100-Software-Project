/**
    @author ajaarsma
    @createdOn 8/6/2024 at 6:54 PM
    @projectName AndrewJaarsma-PRO100-Software-Project
 @packageName project.team.pro100.view;*/
package project.team.pro100.view;

import project.team.pro100.model.Factory;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.spawn;

public class Graphics {
    public static void initGraphics() {
        getGameWorld().addEntityFactory(new Factory());

        spawn("ArrowBackground", 0, 0);

        spawn("ArrowRed",    0,   0);
        spawn("ArrowGreen",  64,  64);
        spawn("ArrowBlue",   192, 64);
        spawn("ArrowYellow", 256, 0);
    }
}