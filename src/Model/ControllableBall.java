package Model;

public class ControllableBall extends Shape {

    int y;
    private int whichHand = 0;
    private Clown clown;

    public ControllableBall(int posX, int posY, String path, int type, Clown clown) {
        super(posX, posY, path, type);
        this.clown = clown;
    }

    public int isLefthand() {
        return whichHand;
    }

    public void setLefthand(int lefthand) {
        this.whichHand = lefthand;
    }

    @Override
    public void setY(int mY) {
    }

    @Override
    public void setX(int mX) {
        if (whichHand == 1) {
            super.setX(clown.getX() - 2);
        } else if (whichHand == 2) {
            super.setX(clown.getX() + 107);
        }
    }
}
