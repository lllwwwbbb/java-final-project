package cs.lwb.huluwa;

import cs.lwb.log.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Attack implements Runnable{
    private transient final God god;
    final Creature attacker;
    final Creature target;
    private int tickInterval = 1000;
    final int totalSteps = 10;

    public int getTickStep() {
        return tickStep;
    }

    private int tickStep = 0;

    public Attack(God god, Creature attacker, Creature target) {
        this.god = god;
        this.attacker = attacker;
        this.target = target;
        new Thread(this, attacker + " attack " + target).start();
    }

    public void run() {
        for (int i = 0; i < totalSteps; i ++) {
            try {
                Thread.sleep(tickInterval / totalSteps);
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
            if (!attacker.isAlive() || !target.isAlive())
                break;
            tickStep = i;
            god.repaint();
        }
        tickStep = totalSteps;
        if (attacker.isAlive())
            attacker.resumeMove();
        god.repaint();
    }

    public boolean isValid() {
        return   tickStep < totalSteps;
    }
}
