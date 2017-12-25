package cs.lwb.huluwa;

import cs.lwb.debug.Logger;
import cs.lwb.gui.Drawable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Attack implements Drawable {
    private God god;
    private final Creature attacker;
    private final Creature target;

    public Attack(God god, Creature attacker, Creature target) {
        this.god = god;
        this.attacker = attacker;
        this.target = target;
    }

    public Image getImage() {
        int factor = 100;
        int offset = factor / 2;
        BufferedImage bImg = new BufferedImage(god.groundWidth * factor, god.groundHeight * factor,
                BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = bImg.createGraphics();
        g.setColor(Color.RED);
        Location aLoc = attacker.getLocation(), tLoc = target.getLocation();
        g.drawLine(aLoc.x * factor + offset, aLoc.y * factor + offset,
                tLoc.x * factor + offset, tLoc.y * factor + offset);
        g.dispose();
        return bImg;
    }
}
