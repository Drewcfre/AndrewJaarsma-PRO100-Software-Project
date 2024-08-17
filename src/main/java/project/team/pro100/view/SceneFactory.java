/**
 * @author znye
 * @createdOn 8/13/2024 at 1:27 PM
 * @projectName AndrewJaarsma-PRO100-Software-Project
 * @packageName project.team.pro100.view;
 */

package project.team.pro100.view;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import org.jetbrains.annotations.NotNull;

public class SceneFactory extends com.almasb.fxgl.app.scene.SceneFactory{
    //region Methods (Click To Expand)
    @NotNull @Override public FXGLMenu newMainMenu() {
        return new CustomMainMenu(MenuType.MAIN_MENU);
    }
    //endregion
}