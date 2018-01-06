package cs.lwb.huluwa;

import cs.lwb.log.Logger;
import cs.lwb.huluwa.creature.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class God {
    protected boolean running = false;
    protected JComponent jcanvas;
    public final int groundWidth = 18, groundHeight = 7;
    private Ground ground = new Ground(groundWidth, groundHeight);

    private List<Attack> attacks = new ArrayList<Attack>();

    public God(JComponent jcanvas) {
        initCreatures();
        this.jcanvas = jcanvas;
    }

    public void start() {
        for (int i = 0; i < groundWidth; i ++) {
            for (int j = 0; j < groundHeight; j ++) {
                Creature c = getCreature(new Location(i, j));
                if (c != null)
                    c.start();
            }
        }
        running = true;
    }

    public boolean isRunning() {
        return running;
    }

    protected void end() {
        List<Creature> creatures = getCreatures();
        for (Creature c : creatures)
            c.end();
        running = false;
    }

    public void repaint() {
        if (jcanvas != null)
            jcanvas.repaint();
    }

    private void initCreatures() {
        Location loc;
        loc = new Location(0, groundHeight / 2);
        loc = loc.getNext(1, -1);
        ground.setCreature(loc, new Laoyeye(this, loc));
        loc = loc.getNext(1, -1);
        ground.setCreature(loc, new DaWa(this, loc));
        loc = loc.getNext(1, 1);
        ground.setCreature(loc, new ErWa(this, loc));
        loc = loc.getNext(1, 1);
        ground.setCreature(loc, new SanWa(this, loc));
        loc = loc.getNext(-1, 1);
        ground.setCreature(loc, new SiWa(this, loc));
        loc = loc.getNext(-1, 1);
        ground.setCreature(loc, new WuWa(this, loc));
        loc = loc.getNext(-1, -1);
        ground.setCreature(loc, new LiuWa(this, loc));
        loc = loc.getNext(-1, -1);
        ground.setCreature(loc, new QiWa(this, loc));

        loc = new Location(groundWidth - 1, groundHeight / 2 - 1);
        ground.setCreature(loc, new XieJing(this, loc));
        loc = loc.getNext(-1, 0);
        ground.setCreature(loc, new Hama(this, loc));
        loc = loc.getNext(0, -1);
        ground.setCreature(loc, new Hama(this, loc));
        loc = loc.getNext(0, -1);
        ground.setCreature(loc, new Hama(this, loc));
        loc = loc.getNext(1, 0);
        ground.setCreature(loc, new Hama(this, loc));


        loc = new Location(groundWidth - 1, groundHeight / 2);
        ground.setCreature(loc, new SheJing(this, loc));
        loc = loc.getNext(-1, 0);
        ground.setCreature(loc, new Hama(this, loc));
        loc = loc.getNext(0, 1);
        ground.setCreature(loc, new Hama(this, loc));
        loc = loc.getNext(0, 1);
        ground.setCreature(loc, new Hama(this, loc));
        loc = loc.getNext(1, 0);
        ground.setCreature(loc, new Hama(this, loc));
    }

    public synchronized void checkMove(Location lastLocation, Creature creature) {
        // update ground
        ground.setCreature(lastLocation, null);
        ground.setCreature(creature.getLocation(), creature);
        Logger.writeLog(creature + " move from" + lastLocation + "To" + creature.getLocation());

        // check distance
        checkDistance(creature);
        repaint();
    }

    public synchronized void checkDistance(Creature creature) {
        Creature enemy = ground.getCreatureNearby(creature.getLocation(), 5,
                creature.getFaction().getOpposeFaction());
        if (enemy != null) {
            checkAttack(creature, enemy);
        }
    }

    protected synchronized void checkAttack(Creature creature, Creature enemy) {
        if (creature == null || !creature.isAlive())
            return;
        if (enemy == null || !enemy.isAlive())
            return;
        Logger.writeLog(creature + " attack " + enemy + " damage:" + creature.getHitPoints());
        enemy.damage(creature.getHitPoints());
        attacks.add(creature.attack(enemy));
    }

    public synchronized void checkDeath(Creature creature) {
        ground.setCreature(creature.getLocation(), null);
        Logger.writeLog(creature + " dead!");
        for (int i = 0; i < attacks.size(); i ++) {
            if (attacks.get(i).isValid())
                continue;
            attacks.remove(i);
            i --;
        }
        repaint();
        List<Creature> creatures = getCreatures();
        boolean good = false, bad = false;
        for (Creature c : creatures) {
            switch (c.getFaction()) {
                case GOOD:  good = true; break;
                case BAD:   bad = true; break;
            }
        }
        if (!good || !bad)
            end();
    }

    public Creature getCreature(Location location) {
        return ground.getCreature(location);
    }

    public void paint(Graphics g) {
        List<Creature> creatures = getCreatures();
        paint(g, creatures, attacks);
    }

    private List<Creature> getCreatures() {
        List<Creature> creatures = new ArrayList<Creature>();
        for (int i = 0; i < groundWidth; i ++) {
            for (int j = 0; j < groundHeight; j ++) {
                Creature c = getCreature(new Location(i, j));
                if (c != null) {
                    creatures.add(c);
                }
            }
        }
        return creatures;
    }

    private void paint(Graphics g, List<Creature> creatures, List<Attack> attacks) {
        int gridW = jcanvas.getWidth() / groundWidth, gridH = jcanvas.getHeight() / groundHeight;
        for (Creature c : creatures) {
            g.drawImage(c.getImage(), gridW * c.getLocation().x, gridH * c.getLocation().y, gridW, gridH, null);
        }
        for (int i = 0; i < attacks.size(); i ++) {
            Attack a = attacks.get(i);
            if (!a.isValid()) {
                attacks.remove(i);
                i --;
                continue;
            }
            int xOffset = gridW / 2, yOffset = gridH / 3;
            Location aLoc, tLoc;

            aLoc = a.attacker.getLocation();
            tLoc = a.target.getLocation();
            int xStart = aLoc.x * gridW + xOffset, yStart = aLoc.y * gridH + yOffset;
            int xEnd = tLoc.x * gridW + xOffset, yEnd = tLoc.y * gridH + gridH - yOffset;
            float xStep = (xEnd - xStart) / a.totalSteps, yStep = (yEnd - yStart) / a.totalSteps;

            g.setColor(Color.RED);
            ((Graphics2D)g).setStroke(new BasicStroke(2));
            g.drawLine(xStart + (int)(xStep * a.getTickStep()), yStart + (int)(yStep * a.getTickStep()),
                    xStart + (int)(xStep * (a.getTickStep() + 1)), yStart + (int)(yStep * (a.getTickStep() + 1)));
        }
    }
}
