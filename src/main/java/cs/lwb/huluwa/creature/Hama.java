package cs.lwb.huluwa.creature;

import cs.lwb.huluwa.Creature;
import cs.lwb.huluwa.God;
import cs.lwb.huluwa.Location;

import javax.swing.*;
import java.awt.*;

public class Hama extends Creature {

    public Hama(God god, Location location) {
        super(god, Faction.BAD, location, "蛤蟆", Speed.FAST, Capability.WEAK);
    }

    protected void onTick() {
        moveTo(getLocation().getNext(-1,0));
    }

    private Image image = new ImageIcon(
            getClass().getClassLoader().getResource("蛤蟆.png")).getImage();

    public Image getImage() {
        return image;
    }
}
