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

public class GameController {
    public static void userInput() {
        onKeyDown(KeyCode.W, () -> {
            System.out.println("W");
        });
        onKeyDown(KeyCode.UP, () -> {
            System.out.println("UP");
        });

        onKeyDown(KeyCode.A, () -> {
            System.out.println("A");
        });
        onKeyDown(KeyCode.LEFT, () -> {
            System.out.println("LEFT");
        });

        onKeyDown(KeyCode.S, () -> {
            System.out.println("S");
        });
        onKeyDown(KeyCode.DOWN, () -> {
            System.out.println("DOWN");
        });

        onKeyDown(KeyCode.D, () -> {
            System.out.println("D");
        });
        onKeyDown(KeyCode.RIGHT, () -> {
            System.out.println("RIGHT");
        });

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