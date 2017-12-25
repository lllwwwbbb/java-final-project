package cs.lwb.huluwa;

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

    private void repaint() {
        if (jcanvas != null)
            jcanvas.repaint();
    }

    private void initCreatures() {
        Location loc = new Location(0,0);
        Creature c = new Laoyeye(this, loc);
        ground.setCreature(loc, c);
        new Thread(c).start();

        loc = new Location(9, 9);
        c = new Shejing(this, loc);
        ground.setCreature(loc, c);
        new Thread(c).start();
    }

    public void checkCreature(Location lastLocation, Creature creature) {
        // update ground
        ground.setCreature(lastLocation, null);
        ground.setCreature(creature.getLocation(), creature);

        // check distance
        Creature enemy = ground.getCreatureNearby(creature.getLocation(), 5,
                creature.getFaction().getOpposeFaction());
        if (enemy != null)
            attacks.add(creature.attack(enemy));
        repaint();
    }

    public Creature getCreature(Location location) {
        return ground.getCreature(location);
    }
}
