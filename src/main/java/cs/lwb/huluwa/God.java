package cs.lwb.huluwa;

import javax.swing.*;

public class God {
    JComponent jcanvas;
    public final int groundWidth = 20, groundHeight = 20;
    private Ground ground = new Ground(20, 20);

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
    }

    public void checkCreature(Location lastLocation, Creature creature) {
        // update ground
        ground.setCreature(lastLocation, null);
        ground.setCreature(creature.getLocation(), creature);

        // check distance

        repaint();
    }

    public Creature getCreature(Location location) {
        return ground.getCreature(location);
    }
}
