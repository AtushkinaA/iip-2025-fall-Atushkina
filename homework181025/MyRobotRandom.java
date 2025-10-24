import codewars.api.Robot;
import codewars.impl.cmd.RobotWithWorldCreation;

import java.util.Random;

import static codewars.api.Rotation.LEFT;
import static codewars.api.Rotation.RIGHT;

public class MyRobotRandom {
    public static void main(String[] args) {
        Robot robot = new RobotWithWorldCreation("/Users/arinaatuskina/Desktop/map1.rmc");
        robot.setSpeed(20);

        Random random = new Random();

        while (robot.collectedItemsCount() < robot.totalItemsCount()) {
            int action = random.nextInt(10);

            if (action <= 6) {
                if (!robot.hasObstacle()) robot.move(1);
            } else if (action == 7) {
                robot.rotate(RIGHT);
                if (!robot.hasObstacle()) robot.move(1);
            } else {
                robot.rotate(LEFT);
                if (!robot.hasObstacle()) robot.move(1);
            }
        }

        robot.finish();
    }
}