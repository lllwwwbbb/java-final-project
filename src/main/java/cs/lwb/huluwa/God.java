package cs.lwb.huluwa;

import cs.lwb.debug.Logger;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class God {
    JComponent jcanvas;
    public final int groundWidth = 10, groundHeight = 10;
    private Ground ground = new Ground(groundWidth, groundHeight);

    public List<Attack> getAttacks() {
        return attacks;
    }

    private List<Attack> attacks = new ArrayList<Attack>();

    public God() {
        initCreatures();
    }

    public void setJcanvas(JComponent jcanvas) {
        this.jcanvas = jcanvas;
    }

    public void repaint() {
        if (jcanvas != null)
            jcanvas.repaint();
    }

    private void initCreatures() {
        Location loc = new Location(0,0);
        ground.setCreature(loc, new Laoyeye(this, loc));
        loc = loc.getNext(0, 1);
        ground.setCreature(loc, new DaWa(this, loc));
        loc = loc.getNext(0, 1);
        ground.setCreature(loc, new ErWa(this, loc));
        loc = loc.getNext(0, 1);
        ground.setCreature(loc, new SanWa(this, loc));
        loc = loc.getNext(0, 1);
        ground.setCreature(loc, new SiWa(this, loc));
        loc = loc.getNext(0, 1);
        ground.setCreature(loc, new WuWa(this, loc));
        loc = loc.getNext(0, 1);
        ground.setCreature(loc, new LiuWa(this, loc));
        loc = loc.getNext(0, 1);
        ground.setCreature(loc, new QiWa(this, loc));

        loc = new Location(9,0);
        ground.setCreature(loc, new XieJing(this, loc));
        loc = loc.getNext(0, 1);
        ground.setCreature(loc, new SheJing(this, loc));
        loc = loc.getNext(0, 1);
        ground.setCreature(loc, new Hama(this, loc));
        loc = loc.getNext(0, 1);
        ground.setCreature(loc, new Hama(this, loc));
        loc = loc.getNext(0, 1);
        ground.setCreature(loc, new Hama(this, loc));
        loc = loc.getNext(0, 1);
        ground.setCreature(loc, new Hama(this, loc));
        loc = loc.getNext(0, 1);
        ground.setCreature(loc, new Hama(this, loc));
        loc = loc.getNext(0, 1);
        ground.setCreature(loc, new Hama(this, loc));
    }

    public void checkMove(Location lastLocation, Creature creature) {
        // update ground
        ground.setCreature(lastLocation, null);
        ground.setCreature(creature.getLocation(), creature);
        Logger.writeLog(creature + " move from" + lastLocation + "To" + creature.getLocation());

        // check distance
        Creature enemy = ground.getCreatureNearby(creature.getLocation(), 5,
                creature.getFaction().getOpposeFaction());
        if (enemy != null)
            attacks.add(creature.attack(enemy));
        repaint();
    }

    public void checkDeath(Creature creature) {
        ground.setCreature(creature.getLocation(), null);
        Logger.writeLog(creature + "dead!");
        for (int i = 0; i < attacks.size(); i ++) {
            if (attacks.get(i).isValid())
                continue;
            attacks.remove(i);
            i --;
        }
        repaint();
    }

    public Creature getCreature(Location location) {
        return ground.getCreature(location);
    }
}
