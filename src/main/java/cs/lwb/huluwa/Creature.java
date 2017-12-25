package cs.lwb.huluwa;

public abstract class Creature implements Runnable{
    protected Position position;

    public Position getPosition() {
        return position;
    }

    void setPosition(Position position) {
        this.position = position;
    }
}
