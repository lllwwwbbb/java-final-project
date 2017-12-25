package cs.lwb.huluwa;

public class Space {
    private Ground ground = new Ground(20, 20);

    public Space() {
        initCreatures();
    }

    private void initCreatures() {

    }

    public void checkCreature(Location lastLocation, Creature creature) {

    }

    public Creature getCreature(Location location) {
        return ground.getCreature(location);
    }
}
