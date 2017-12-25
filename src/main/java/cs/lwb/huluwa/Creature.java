package cs.lwb.huluwa;

public abstract class Creature implements Runnable{
    protected Location location = new Location();
    protected Space ground;
    public Creature(Space ground) {
        this.ground = ground;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
