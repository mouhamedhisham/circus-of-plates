package Controller;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.LinkedList;

public class LinkedListIterator implements Iterator {

    LinkedList<GameObject> linkedList;
    int counter;

    public LinkedListIterator() {
        linkedList = new LinkedList<GameObject>();
        counter = 0;
    }

    @Override
    public void addObject(Object ball) {
        linkedList.add((GameObject) ball);
    }

    @Override
    public Object removeObject(Object ball) {
        return linkedList.remove(ball);
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public Object getPeekValue() {
        return linkedList.peek();
    }

    @Override
    public int getCounter() {
        return counter;
    }

    @Override
    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public int getSize() {
        return linkedList.size();
    }

    @Override
    public LinkedList<GameObject> getIteratorTool() {
        return linkedList;
    }
    
    @Override
    public GameObject getValue(int index) {
        return  linkedList.get(index);
    }
}
