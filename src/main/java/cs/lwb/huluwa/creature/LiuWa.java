package cs.lwb.huluwa.creature;

import cs.lwb.huluwa.Creature;
import cs.lwb.huluwa.God;
import cs.lwb.huluwa.Location;

import javax.swing.*;
import java.awt.*;

public class LiuWa extends Creature {

    public LiuWa(God god, Location location) {
        super(god, Faction.GOOD, location, "六娃", Speed.NORMAL, Capability.NORMAL);
    }

    protected void onTick() {
        moveTo(getLocation().getNext(1,0));
    }

    Image image = new ImageIcon(
            getClass().getClassLoader().getResource("六娃.png")).getImage();

    public Image getImage() {
        return image;
    }
}