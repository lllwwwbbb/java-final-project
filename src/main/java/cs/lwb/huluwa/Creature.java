package cs.lwb.huluwa;

import cs.lwb.debug.Logger;
import cs.lwb.gui.Drawable;

public abstract class Creature implements Runnable, Drawable{
    private Location location;
    private God god;
    private Faction faction;
    private String name;
    private int tickInterval;
    private CreatureState state = CreatureState.MOVE;

    protected Creature(God god, Faction faction, Location location, String name, int tickInterval) {
        this.location = location;
        this.god = god;
        this.faction = faction;
        this.name = name;
        this.tickInterval = tickInterval;
    }

    Faction getFaction() {
        return faction;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return name;
    }

    public Attack attack(Creature target) {
        Logger.writeLog(this + " attack " + target);
        state = CreatureState.ATTACK;
        return new Attack(god,this, target);
    }

    private void notifyGod(Location lastLocation) {
        god.checkCreature(lastLocation, this);
    }

    protected boolean moveTo(Location nextLocation) {
        if (state != CreatureState.MOVE)
            return false;
        if (nextLocation.x < 0 || nextLocation.x >= god.groundWidth
                || nextLocation.y < 0 || nextLocation.y >= god.groundHeight)
            return false;
        if (god.getCreature(nextLocation) != null)
            return false;
        Location lastLocation = location;
        location = nextLocation;
        notifyGod(lastLocation);
        Logger.writeLog(this + " move from" + lastLocation + "To" + location);
        return true;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(tickInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            onTick();
        }
    }

    protected abstract void onTick();
}

