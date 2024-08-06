/**
 * @author ajaarsma
 * @createdOn 8/6/2024 at 4:05 PM
 * @projectName AndrewJaarsma-PRO100-Software-Project
 * @packageName project.team.pro100.view;
 */
package project.team.pro100.view;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;

public class Graphics {
    public static void drawMainMenu() {
        Texture texture = FXGL.getAssetLoader().loadTexture("ArrowBackground.png");
        texture.setTranslateX(0);
        texture.setTranslateY(0);

        FXGL.getGameScene().addUINode(texture);
    }
}