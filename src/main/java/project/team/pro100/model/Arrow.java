/**
 * @author ajaarsma
 * @createdOn 8/15/2024 at 6:22 PM
 * @projectName AndrewJaarsma-PRO100-Software-Project
 * @packageName project.team.pro100.model;
 */

package project.team.pro100.model;

import com.almasb.fxgl.entity.Entity;

public class Arrow {
    //region Variables/Getters/Setters (Click To Expand)
    private Entity arrow;
    public Entity getArrow() {
        return arrow;
    }
    public void setArrow(Entity arrow) {
        if(arrow != null) this.arrow = arrow;
        else throw new NullPointerException("arrow is null");
    }

    private boolean isUsed;
    // The get function here is inverted, as there is no instance where the returned value isn't inverted.
    public boolean isUsedInv() {
        return !isUsed;
    }
    public void setUsed(boolean used) {
        isUsed = used;
    }
    //endregion

    //region Constructors (Click To Expand)
    public Arrow(Entity arrow) {
        setArrow(arrow);
        setUsed(false);
    }
    //endregion

    //region Methods (Click To Expand)
    public void removeArrow() {
        getArrow().removeFromWorld();
        setUsed(true);
    }
    //endregion
}