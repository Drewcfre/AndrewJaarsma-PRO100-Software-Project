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

import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGL.onKeyDown;
import static com.almasb.fxgl.dsl.FXGLForKtKt.spawn;

public class GameController {
    public static void userInput() {
        onKeyDown(KeyCode.W, () -> { arrowHit(0); });
        onKeyDown(KeyCode.UP, () -> { arrowHit(0); });

        onKeyDown(KeyCode.A, () -> { arrowHit(1); });
        onKeyDown(KeyCode.LEFT, () -> { arrowHit(1); });

        onKeyDown(KeyCode.S, () -> { arrowHit(2); });
        onKeyDown(KeyCode.DOWN, () -> { arrowHit(2); });

        onKeyDown(KeyCode.D, () -> { arrowHit(3); });
        onKeyDown(KeyCode.RIGHT, () -> { arrowHit(3); });

        // Select.
        onKeyDown(KeyCode.SPACE, () -> {
            System.out.println("SPACE");
        });
        onKeyDown(KeyCode.ENTER, () -> {
            System.out.println("ENTER");
        });

        // Go back; open the pause menu.
        onKeyDown(KeyCode.BACK_SPACE, () -> {
            System.out.println("BACK_SPACE");
        });
        onKeyDown(KeyCode.ESCAPE, () -> {
            System.out.println("ESCAPE");
        });
    }

    private static void arrowHit(int arrowType) {
        if(RhythmApp.getMessage() != null) {
            RhythmApp.getMessage().removeFromWorld();
        }

        int offset = switch (arrowType) {
            case 0, 3 -> 0;
            case 1, 2 -> 64;
            default -> throw new IllegalStateException("Unexpected value: " + arrowType);
        };

        Entity arrow = RhythmApp.getList(arrowType).getFirst();

        if(arrow.getY() < offset + 10 && arrow.getY() > offset - 10) {
            RhythmApp.setMessage(spawn("Perfect", 300, 300));
        }
        else if(arrow.getY() < offset + 30) {
            RhythmApp.setMessage(spawn("Good", 300, 300));
        }
        else if(arrow.getY() < offset + 50) {
            RhythmApp.setMessage(spawn("Okay", 300, 300));
        }
        else {
            RhythmApp.setMessage(spawn("Terrible", 300, 300));
        }
    }

    public static void updateArrows(int speed, int removeHeight) {
        for (int i = 0; i < 4; i++) {
            ArrayList<Entity> oldArrows = new ArrayList<>();

            if(RhythmApp.getList(i) != null && !RhythmApp.getList(i).isEmpty()) {
                for (Entity arrow : RhythmApp.getList(i)) {
//                    arrow.translateY(-speed);
                    if (arrow.getY() < removeHeight) {
                        oldArrows.add(arrow);
                    }
                }

                for (int j = 0; j < oldArrows.size() - 1; j++) {
                    RhythmApp.removeArrow(j, oldArrows.getFirst());
                    oldArrows.getFirst().removeFromWorld();
                    oldArrows.remove(oldArrows.getFirst());
                }
            }
        }
    }
}