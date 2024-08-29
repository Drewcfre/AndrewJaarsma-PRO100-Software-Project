/**
 * @author ajaarsma
 * @createdOn 7/31/2024 at 6:43 PM
 * @projectName UntitledRhythmGame
 * @packageName controller;
 */

package project.team.pro100.controller;

import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import project.team.pro100.RhythmApp;
import project.team.pro100.model.Arrow;
import project.team.pro100.model.EntityFactory;

import java.util.Iterator;

import static com.almasb.fxgl.dsl.FXGL.onKeyDown;
import static com.almasb.fxgl.dsl.FXGLForKtKt.spawn;

public class GameController {
    //region Methods (Click To Expand)
    public static void userInput() {
        onKeyDown(KeyCode.W, () -> whenKeyPressed("PlayerUp", 150, 0));
        onKeyDown(KeyCode.UP, () -> whenKeyPressed("PlayerUp", 150, 0));

        onKeyDown(KeyCode.A, () -> whenKeyPressed("PlayerLeft", 150, 1));
        onKeyDown(KeyCode.LEFT, () -> whenKeyPressed("PlayerLeft", 150, 1));

        onKeyDown(KeyCode.S, () -> whenKeyPressed("PlayerDown", 180, 2));
        onKeyDown(KeyCode.DOWN, () -> whenKeyPressed("PlayerDown", 180, 2));

        onKeyDown(KeyCode.D, () -> whenKeyPressed("PlayerRight", 150, 3));
        onKeyDown(KeyCode.RIGHT, () -> whenKeyPressed("PlayerRight", 150, 3));

        onKeyDown(KeyCode.ESCAPE, RhythmApp::pauseGame);
    }

    private static void whenKeyPressed(String entityName, int y, int arrowType){
        if(EntityFactory.getPlayer() != null) EntityFactory.getPlayer().removeFromWorld();
        EntityFactory.setPlayer(spawn(entityName, 330, y));
        arrowHit(arrowType);
    }

    private static void arrowHit(int arrowType) {
        RhythmApp.setCounter(0);

        int activeArrow;

        for (activeArrow = 0; activeArrow < RhythmApp.getList(arrowType).size(); activeArrow++) {
            if(RhythmApp.getList(arrowType).get(activeArrow).isUsedInv()) {
                int offset = switch (arrowType) {
                    case 0, 3 -> 0;
                    case 1, 2 -> 64;
                    default -> throw new IllegalStateException("Unexpected value: " + arrowType);
                };

                if(RhythmApp.getMessage() != null) RhythmApp.getMessage().removeFromWorld();

                Entity arrow = RhythmApp.getList(arrowType).get(activeArrow).getArrow();

                if(arrow.getY() < offset + 10 && arrow.getY() > offset - 10) {
                    RhythmApp.setMessage(spawn("Perfect", 350, 30));
                    RhythmApp.setScore(RhythmApp.getScore() + 100);
                }
                else if(arrow.getY() < offset + 30) {
                    RhythmApp.setMessage(spawn("Good", 350, 30));
                    RhythmApp.setScore(RhythmApp.getScore() + 50);
                }
                else if(arrow.getY() < offset + 50) {
                    RhythmApp.setMessage(spawn("Okay", 350, 30));
                    RhythmApp.setScore(RhythmApp.getScore() + 10);
                }
                else {
                    RhythmApp.setMessage(spawn("Terrible", 350, 30));
                    if(EntityFactory.getPlayer() != null) EntityFactory.getPlayer().removeFromWorld();
                    EntityFactory.setPlayer(spawn("PlayerDead", 280, 280));
                }

                RhythmApp.getList(arrowType).get(activeArrow).removeArrow();

                break;
            }
        }
    }

    public static void updateArrows(int removeHeight) {
        for (int i = 0; i < 4; i++) {
            if(RhythmApp.getList(i) != null && !RhythmApp.getList(i).isEmpty()) {
                Iterator<Arrow> iterator = RhythmApp.getList(i).iterator();

                while (iterator.hasNext()) {
                    Arrow arrow = iterator.next();

                    if (arrow.getArrow() != null && arrow.getArrow().getY() < removeHeight && arrow.isUsedInv()) {
                        RhythmApp.setMisses(RhythmApp.getMisses() + 1);
                        arrow.removeArrow();
                        iterator.remove();
                    }
                }
            }
        }

        EntityFactory.setMissCount(RhythmApp.getMisses());
        EntityFactory.setScoreCount(RhythmApp.getScore());
    }
    //endregion
}