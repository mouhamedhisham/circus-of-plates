package Controller;

import Model.Ball;
import Model.Bomb;
import Model.Heart;
import Model.ImageObject;
import java.util.Random;

/**
 *
 * @author a7med
 */
public class ShapeFactory {

    Random rand = new Random();

    public ShapeFactory() {
    }

    public ImageObject createObject(String objectName) {
        if (objectName.equalsIgnoreCase("ball")) {
            String[] colors = {"Red", "Blue", "Yellow"};
            String path = colors[rand.nextInt(colors.length)] + "_Ball.png";
            return new Ball(0, 0, path, 1);
        } else if (objectName.equalsIgnoreCase("bomb")) {
            return new Bomb(0, 0, "Bomb.png", 0);
        } else if (objectName.equalsIgnoreCase("Heart")) {
            return new Heart(0, 0, "Heart.png");
        }
        return null;
    }

}
