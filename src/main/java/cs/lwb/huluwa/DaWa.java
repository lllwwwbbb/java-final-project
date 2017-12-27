package cs.lwb.huluwa;

import javax.swing.*;
import java.awt.*;

public class DaWa extends Creature {

    public DaWa(God god, Location location) {
        super(god, Faction.GOOD, location, "大娃", Speed.NORMAL, Capability.NORMAL);
    }

    protected void onTick() {
        moveTo(getLocation().getNext(1,0));
    }

    Image image = new ImageIcon(
            getClass().getClassLoader().getResource("大娃.png")).getImage();

    public Image getImage() {
        return image;
    }
}
