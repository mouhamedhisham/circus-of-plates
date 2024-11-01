/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author elsam
 */
public class ImageObject implements GameObject {

    private int x, y;
    private boolean visible;
    private BufferedImage[] spriteImages;
    private boolean horizontalOnly;
    private String path;
    private String color;

    public String getColor() {
        return (path.split("_"))[0];
    }

    public ImageObject(int posX, int posY, String path, boolean horizontalOnly) {
        this.x = posX;
        this.y = posY;
        this.spriteImages = new BufferedImage[1];
        this.horizontalOnly = horizontalOnly;
        this.visible = true;
        this.path = path;
        // create a bunch of buffered images and place into an array, to be displayed sequentially
        try {
            spriteImages[0] = ImageIO.read(getClass().getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPath() {
        return path;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int mX) {
        System.out.println("inside set x");
        this.x = mX;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int mY) {
        if (horizontalOnly) {
            return;
        }
        this.y = mY;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getWidth() {
        return spriteImages[0].getWidth();
    }

    @Override
    public int getHeight() {
        return spriteImages[0].getHeight();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
