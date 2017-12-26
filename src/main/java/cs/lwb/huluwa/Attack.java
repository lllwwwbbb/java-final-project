package cs.lwb.huluwa;

import cs.lwb.debug.Logger;
import cs.lwb.gui.Drawable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Attack implements Drawable, Runnable {
    private final God god;
    private final Creature attacker;
    private final Creature target;
    private int tickInterval = 1000;
    private final int totalSteps = 10;
    private int tickStep = 0;
    private Image images[];
    private boolean valid = true;
    private Location aLoc;
    private Location tLoc;

    public Attack(God god, Creature attacker, Creature target) {
        this.god = god;
        this.attacker = attacker;
        this.target = target;
        initImages();
        new Thread(this, attacker + " attack " + target).start();
    }

    private void initImages() {
        int factor = 100;
        int xOffset = factor / 2, yOffset = factor / 3;

        aLoc = attacker.getLocation();
        tLoc = target.getLocation();
        int xStart = aLoc.x * factor + xOffset, yStart = aLoc.y * factor + yOffset;
        int xEnd = tLoc.x * factor + xOffset, yEnd = tLoc.y * factor + factor - yOffset;
        float xStep = (xEnd - xStart) / totalSteps, yStep = (yEnd - yStart) / totalSteps;
        images = new BufferedImage[totalSteps];
        for (int i = 0; i < totalSteps; i ++) {
            images[i] = new BufferedImage(god.groundWidth * factor, god.groundHeight * factor,
                    BufferedImage.TYPE_4BYTE_ABGR);
            Graphics g = ((BufferedImage)images[i]).createGraphics();
            g.setColor(Color.RED);
            ((Graphics2D)g).setStroke(new BasicStroke(2));
            g.drawLine(xStart, yStart, xStart + (int)(xStep * (i + 1)), yStart + (int)(yStep * (i + 1)));
            g.dispose();
        }
    }

    public Image getImage() {
        if (attacker.getLocation().compareTo(aLoc) != 0 || target.getLocation().compareTo(tLoc) != 0)
            initImages();
        return images[tickStep];
    }

    public void run() {
        while (isValid()) {
            for (int i = 0; i < totalSteps; i ++) {
                try {
                    Thread.sleep(tickInterval / totalSteps);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tickStep = i;
                god.repaint();
            }
            if (!isValid())
                break;
            Logger.writeLog(attacker + " attack " + target + " damage:" + attacker.getHitPoints());
            target.damage(attacker.getHitPoints());
        }
        valid = false;
        attacker.resumeMove();
        god.repaint();
    }

    public boolean isValid() {
        // setting of 'valid' may be delay because of 'sleep'
        return valid && (attacker.isAlive() && target.isAlive());
    }
}
