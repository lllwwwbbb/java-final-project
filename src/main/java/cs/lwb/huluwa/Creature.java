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
    private int healthPoints = 100;
    private int hitPoints = 25;

    protected Creature(God god, Faction faction, Location location, String name, int tickInterval) {
        this.location = location;
        this.god = god;
        this.faction = faction;
        this.name = name;
        this.tickInterval = tickInterval;

        new Thread(this, this.toString()).start();
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

    public boolean isAlive() {
        return healthPoints > 0;
    }

    public void damage(int hitPoints) {
        healthPoints -= hitPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public Attack attack(Creature target) {
        state = CreatureState.ATTACK;
        return new Attack(god,this, target);
    }

    public void resumeMove() {
        state = CreatureState.MOVE;
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
        god.checkMove(lastLocation, this);
        return true;
    }

    public void run() {
        while (isAlive()) {
            try {
                Thread.sleep(tickInterval);
                onTick();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        god.checkDeath(this);
    }

    protected abstract void onTick();
}

