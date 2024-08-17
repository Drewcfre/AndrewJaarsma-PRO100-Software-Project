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

import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGL.onKeyDown;
import static com.almasb.fxgl.dsl.FXGLForKtKt.spawn;

public class GameController {
    //region Methods (Click To Expand)
    public static void userInput() {
        onKeyDown(KeyCode.W, () -> {
            EntityFactory.setPlayer("PlayerUp.png");
            arrowHit(0);
        });
        onKeyDown(KeyCode.UP, () -> {
            EntityFactory.setPlayer("PlayerUp.png");
            arrowHit(0);
        });

        onKeyDown(KeyCode.A, () -> {
            EntityFactory.setPlayer("PlayerLeft.png");
            arrowHit(1);
        });
        onKeyDown(KeyCode.LEFT, () -> {
            EntityFactory.setPlayer("PlayerLeft.png");
            arrowHit(1);
        });

        onKeyDown(KeyCode.S, () -> {
            EntityFactory.setPlayer("PlayerDown.png");
            arrowHit(2);
        });
        onKeyDown(KeyCode.DOWN, () -> {
            EntityFactory.setPlayer("PlayerDown.png");
            arrowHit(2);
        });

        onKeyDown(KeyCode.D, () -> {
            EntityFactory.setPlayer("PlayerRight.png");
            arrowHit(3);
        });
        onKeyDown(KeyCode.RIGHT, () -> {
            EntityFactory.setPlayer("PlayerRight.png");
            arrowHit(3);
        });

        // Opens the pause menu.
        onKeyDown(KeyCode.BACK_SPACE, () -> System.out.println("BACK_SPACE"));
        onKeyDown(KeyCode.ESCAPE, () -> System.out.println("ESCAPE"));
    }

    private static void arrowHit(int arrowType) {
        int activeArrow;

        for (activeArrow = 0; activeArrow < RhythmApp.getList(arrowType).size(); activeArrow++) {
            if(RhythmApp.getList(arrowType).get(activeArrow).isUsedInv()) {
                int offset = switch (arrowType) {
                    case 0, 3 -> 0;
                    case 1, 2 -> 64;
                    default -> throw new IllegalStateException("Unexpected value: " + arrowType);
                };

                if(RhythmApp.getMessage() != null) RhythmApp.getMessage().removeFromWorld();

                Entity arrow = RhythmApp.getList(arrowType).getFirst().getArrow();

                //TODO: Scoring seems to be off and message spawns are still buggy.
                if(arrow.getY() < offset + 10 && arrow.getY() > offset - 10) {
                    RhythmApp.setMessage(spawn("Perfect", 300, 300));
                    RhythmApp.setScore(RhythmApp.getScore() + 100);
                }
                else if(arrow.getY() < offset + 30) {
                    RhythmApp.setMessage(spawn("Good", 300, 300));
                    RhythmApp.setScore(RhythmApp.getScore() + 50);
                }
                else if(arrow.getY() < offset + 50) {
                    RhythmApp.setMessage(spawn("Okay", 300, 300));
                    RhythmApp.setScore(RhythmApp.getScore() + 10);
                }
                else {
                    RhythmApp.setMessage(spawn("Terrible", 300, 300));
                }

                RhythmApp.getList(arrowType).get(activeArrow).removeArrow();

                break;
            }
        }
    }

    public static void updateArrows(int removeHeight) {
        for (int i = 0; i < 4; i++) {
            ArrayList<Arrow> oldArrows = new ArrayList<>();

            if(RhythmApp.getList(i) != null && !RhythmApp.getList(i).isEmpty()) {
                for (Arrow arrow : RhythmApp.getList(i)) {
                    if (arrow.getArrow() != null && arrow.getArrow().getY() < removeHeight && arrow.isUsedInv()) {
                        RhythmApp.setMisses(RhythmApp.getMisses() + 1);
                        arrow.removeArrow();
                        oldArrows.add(arrow);
                    }
                }

                for (int j = 0; j < oldArrows.size() - 1; j++) {
                    RhythmApp.getList(i).remove(oldArrows.get(j));
                }
            }
        }

        EntityFactory.setMissCount(RhythmApp.getMisses());
        EntityFactory.setScoreCount(RhythmApp.getScore());
    }
    //endregion
}