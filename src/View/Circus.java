package View;

import Controller.Iterator;
import Controller.LinkedListIterator;
import Controller.StackIterator;
import Controller.Strategy;
import Controller.ShapeFactory;
import Controller.GameState;
import Controller.GameOver;
import Controller.NewGame;
import Model.*;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.util.List;

/**
 *
 * @author elsam
 */
public class Circus implements World {

    private int width;
    private int height;
    private int score = 0;
    private Bar leftBar;
    private Bar rightBar;
    private Clown clown;
    private Strategy strategy;
    private int numOfHearts = 3;
    private int ballsToClear = 3;
    private int counter = 4;
    private GameState state = new NewGame(this);
    private boolean gameStart = true;
    
    private ControllableBall newBall;
    Iterator sLeft = new StackIterator();
    Iterator sRight = new StackIterator();

    Iterator constant = new LinkedListIterator();
    Iterator moving = new LinkedListIterator();
    Iterator control = new LinkedListIterator();

    ShapeFactory shapeFactory = new ShapeFactory();

    public Circus(int width, int height, Strategy strategy) {
        this.width = width;
        this.height = height;
        this.clown = new Clown(width / 2, height - 160, "Clown.png");
        this.leftBar = new Bar(width / 2 - 10, clown.getY() + 20, 20);
        this.rightBar = new Bar(width / 2 + 10, clown.getY() + 20, 20);
        this.strategy = strategy;

        constant.addObject(new Background(0, 0, "Background.png"));

        for (int i = 0; i < numOfHearts; i++) {
            Heart heart = (Heart) shapeFactory.createObject("Heart");
            heart.setX(50 + i * 30);
            heart.setY(20);

            constant.addObject(heart);

        }

        for (int i = 0; i < 3; i++) {
            int posX = (int) ((int) (Math.random() * width / 2));
            int posY = (int) (-1 * (int) (Math.random() * height * i));
            Bomb bomb = (Bomb) shapeFactory.createObject("bomb");
            bomb.setX(posX);
            bomb.setY(posY);
            moving.addObject(bomb);

        }

        String[] colors = {"Red", "Blue", "Yellow"};
        for (int i = 0; i < 100; i++) {
            int posX = (int) ((int) (Math.random() * width / 2));
            int posY = (int) (-1 * (int) (Math.random() * height * i));
            Ball ball = (Ball) shapeFactory.createObject("ball");
            ball.setX(posX);
            ball.setY(posY);
            moving.addObject(ball);
        }

        control.addObject(clown);
        constant.addObject(leftBar);
        constant.addObject(rightBar);

        
        
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constant.getIteratorTool();
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return moving.getIteratorTool();
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return control.getIteratorTool();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    private boolean intersect(GameObject o1, GameObject o2) {
        return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth()) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
    }
    
  
    

    @Override
    public boolean refresh() {
        if(gameStart)
        {
            state.gameAction();
            gameStart = false;
        }
        
        leftBar.setX(clown.getX() - 2);
        rightBar.setX(clown.getX() + 107);
        
        

        for (var m : moving.getIteratorTool().toArray(new Shape[moving.getSize()])) {

            if (m.getType() == 0) {
                m.setY((m.getY() + 1));
                if (m.getY() == getHeight()) {
                    // reuse the alien in another position
                    m.setY(-1 * (int) (Math.random() * getHeight()));
                    m.setX((int) (Math.random() * getWidth()));
                }
            } else if (m.getType() == 1) {
                m.setY((m.getY() + 1));
                if (m.getY() == getHeight()) {
                    m.setY((int) (-1 * (int) (Math.random() * height)) - 30);
                }
            }

            if (m.getType() == 0 && (intersect(leftBar, m) || intersect(rightBar, m))) {
                moving.removeObject(m);

                constant.getValue(counter-1).setX(-500);
                numOfHearts = numOfHearts - 1;
                counter--;
                if (score > 0) {
                    score--;
                }
                if(numOfHearts <= 0){
                    state = new GameOver(this);
                    state.gameAction();
                    return false;
                }
            }

            if (intersect(leftBar, m)) {
                if (m instanceof Ball) {
                    newBall = new ControllableBall(m.getX(), m.getY(), m.getPath(), m.getType(), clown);
                    newBall.setY(leftBar.getY() - 5);
                    newBall.setX(leftBar.getX());
                    control.addObject(newBall);
                    moving.removeObject(m);

                    newBall.setLefthand(1);

                    //System.out.println(i);
                    leftBar.setY(leftBar.getY() - 10);
                    sLeft.addObject(newBall);
                    if (sLeft.getCounter() == 3) {
                        while (ballsToClear > 0) {
                            newBall = (ControllableBall) sLeft.removeObject(null);
                            control.removeObject(newBall);
                            leftBar.setY(leftBar.getY() + 10);
                            ballsToClear--;
                        }
                        score++;
                        ballsToClear = 3;
                        
                        if (!sLeft.isEmpty()) {
                            newBall = (ControllableBall) sLeft.removeObject(null);
                            if (sLeft.isEmpty()) {
                                sLeft.addObject(newBall);
                                sLeft.setCounter(1);
                            } else if (newBall.getColor().equalsIgnoreCase(((ControllableBall) sLeft.getPeekValue()).getColor())) {
                                sLeft.addObject(newBall);
                                sLeft.setCounter(2);
                            } else {
                                sLeft.setCounter(1);
                            }
                        } else {
                            sLeft.setCounter(1);
                        }
                    }
                } 
            }

            if (intersect(rightBar, m)) {
                if (m instanceof Ball) {
                    newBall = new ControllableBall(m.getX(), m.getY(), m.getPath(), m.getType(), clown);
                    newBall.setY(rightBar.getY() - 5);
                    newBall.setX(rightBar.getX());
                    control.addObject(newBall);
                    moving.removeObject(m);

                    newBall.setLefthand(2);

                    rightBar.setY(rightBar.getY() - 10);
                    sRight.addObject(newBall);
                    
                    if (sRight.getCounter()== 3) {
                        while (ballsToClear > 0) {
                            newBall = (ControllableBall) sRight.removeObject(null);
                            control.removeObject(newBall);
                            rightBar.setY(rightBar.getY() + 10);
                            //moving.add(newBall);
                            ballsToClear--;
                        }
                        score++;
                        ballsToClear = 3;
                        if (!sRight.isEmpty()) {
                            newBall = (ControllableBall) sRight.removeObject(null);
                            if (sRight.isEmpty()) {
                                sRight.addObject(newBall);
                                sRight.setCounter(1);
                            } else if (newBall.getColor().equalsIgnoreCase(((ControllableBall) sRight.getPeekValue()).getColor())) {
                                sRight.addObject(newBall);
                                sRight.setCounter(2);
                            } else {
                                sRight.setCounter(1);
                            }
                        } else {
                            sRight.setCounter(1);
                        }
                    }
                } 
            }
        }

        return true;
    }

    @Override
    public String getStatus() {
        return "Please Use Arrows To Move   |   Score=" + score;	// update status
    }

    @Override
    public int getSpeed() {
        return strategy.getSpeed();

    }

    @Override
    public int getControlSpeed() {
        return 10;
    }

}
