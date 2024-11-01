package Controller;

import Model.ControllableBall;
import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.LinkedList;
import java.util.Stack;

public class StackIterator implements Iterator {

    Stack<ControllableBall> stack;
    int counter;

    public StackIterator() {
        stack = new Stack();
        counter = 1;
    }

    @Override
    public void addObject(Object ball) {
        if (!stack.empty()) {
            if (((ControllableBall) ball).getColor().equalsIgnoreCase(stack.peek().getColor())) {
                counter++;
            } else {
                counter = 1;
            }
            stack.push((ControllableBall) ball);
        } else {
            stack.push((ControllableBall) ball);
        }
    }

    @Override
    public Object removeObject(Object ball) {
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.empty();
    }

    @Override
    public Object getPeekValue() {
        return stack.peek();
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
        return stack.size();
    }

    @Override
    public LinkedList<GameObject> getIteratorTool() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public GameObject getValue(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
