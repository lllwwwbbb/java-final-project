package cs.lwb.huluwa.creature;

import cs.lwb.huluwa.Creature;
import cs.lwb.huluwa.God;
import cs.lwb.huluwa.Location;

import javax.swing.*;
import java.awt.*;

public class WuWa extends Creature {

    public WuWa(God god, Location location) {
        super(god, Faction.GOOD, location, "五娃", Speed.NORMAL, Capability.NORMAL);
    }

    protected void onTick() {
        moveTo(getLocation().getNext(1,0));
    }

    Image image = new ImageIcon(
            getClass().getClassLoader().getResource("五娃.png")).getImage();

    public Image getImage() {
        return image;
    }
}