package cs.lwb.huluwa;

public abstract class Creature implements Runnable{
    private Location location;
    private Space space;
    private Faction faction;
    public Creature(Space space, Faction faction) {
        this.space = space;
        this.faction = faction;
    }

    private void notifyObserver(Location lastLocation) {
        space.checkCreature(lastLocation, this);
    }

    Faction getFaction() {
        return faction;
    }

    protected boolean moveTo(Location nextLocation) {
        if (space.getCreature(nextLocation) != null)
            return false;
        Location lastLocation = location;
        location = nextLocation;
        notifyObserver(lastLocation);
        return true;
    }
}

