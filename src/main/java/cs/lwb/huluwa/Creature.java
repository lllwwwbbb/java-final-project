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
    private int healthPoints;
    private int hitPoints;
    private final Thread thread;

    protected Creature(God god, Faction faction, Location location, String name, Speed speed, Capability capability) {
        this.location = location;
        this.god = god;
        this.faction = faction;
        this.name = name;
        this.tickInterval = speed.interval;
        this.healthPoints = capability.healthPoints;
        this.hitPoints = capability.hitPoints;

        thread = new Thread(this, this.toString());
        thread.start();
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
        if (!isAlive())
            thread.interrupt();
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
                Logger.writeLog(this + " isInterrupted");
                break;
            }
        }
        god.checkDeath(this);
    }

    protected abstract void onTick();

    public enum CreatureState {
        MOVE, ATTACK
    }

    public enum Faction {
        GOOD, BAD;

        public Faction getOpposeFaction() {
            switch (this) {
                case BAD: return GOOD;
                case GOOD: return BAD;
                default: assert false;
            }
            assert false;
            return null;
        }
    }

    protected enum Speed {
        SLOW(1500), NORMAL(1000), FAST(700);

        public int interval;
        Speed(int interval) {
            this.interval = interval;
        }
    }

    protected enum  Capability {
        WEAK(70, 20), NORMAL(100, 30), POWERFUL(120, 35);

        public int healthPoints, hitPoints;
        Capability(int healthPoints, int hitPoints) {
            this.healthPoints = healthPoints;
            this.hitPoints = hitPoints;
        }
    }
}

