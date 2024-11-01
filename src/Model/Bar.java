/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Bar implements GameObject {

    public static final int SPRITE_HEIGHT = 1;
    private BufferedImage[] spriteImages = new BufferedImage[1];

    private int x;
    private int y;
    private int width;
    private boolean visible;
    private boolean horizontalOnly;

    public Bar(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.visible = true;
        this.horizontalOnly = false;

        spriteImages[0] = new BufferedImage(width, SPRITE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = spriteImages[0].createGraphics();
        g2.setColor(Color.BLACK);
        g2.setBackground(Color.BLACK);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(20));
        g2.drawLine(0, 0, getWidth(), 0);
        g2.dispose();
    }

    @Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int mX) {
		this.x = mX;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int mY) {
		if(horizontalOnly)
			return;
		this.y = mY;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	@Override
	public int getWidth(){
		return width;
	}

	@Override
	public int getHeight() {
		return SPRITE_HEIGHT;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;
	}

}
