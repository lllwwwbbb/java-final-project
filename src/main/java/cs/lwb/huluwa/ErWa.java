package cs.lwb.huluwa;

import javax.swing.*;
import java.awt.*;

public class ErWa extends Creature {

    public ErWa(God god, Location location) {
        super(god, Faction.GOOD, location, "二娃", Speed.NORMAL, Capability.NORMAL);
    }

    protected void onTick() {
        moveTo(getLocation().getNext(1,0));
    }

    Image image = new ImageIcon(
            getClass().getClassLoader().getResource("二娃.png")).getImage();

    public Image getImage() {
        return image;
    }
}