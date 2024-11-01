package Controller;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.LinkedList;

public interface Iterator {

    void addObject(Object ball);

    Object removeObject(Object ball);

    boolean isEmpty();

    Object getPeekValue();

    int getCounter();

    void setCounter(int counter);

    int getSize();
    
    GameObject getValue(int index);

    LinkedList<GameObject> getIteratorTool();
}
