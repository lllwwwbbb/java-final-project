package cs.lwb.huluwa.creature;

import cs.lwb.huluwa.Creature;
import cs.lwb.huluwa.God;
import cs.lwb.huluwa.Location;

import javax.swing.*;
import java.awt.*;

public class XieJing extends Creature {

    public XieJing(God god, Location location) {
        super(god, Faction.BAD, location, "蝎精", Speed.SLOW, Capability.POWERFUL);
    }

    protected void onTick() {
        moveTo(getLocation().getNext(-1, 0));
    }

    private Image image = new ImageIcon(
            getClass().getClassLoader().getResource("蝎精.png")).getImage();

    public Image getImage() {
        return image;
    }
}
