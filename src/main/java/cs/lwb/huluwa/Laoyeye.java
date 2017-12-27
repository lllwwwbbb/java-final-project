package cs.lwb.huluwa;

import javax.swing.*;
import java.awt.*;

public class Laoyeye extends Creature {
    public Laoyeye(God god, Location location) {
        super(god, Faction.GOOD, location, "爷爷", Speed.SLOW, Capability.WEAK);
    }

    protected void onTick() {
        moveTo(getLocation().getNext(1,0));
    }

    private Image image = new ImageIcon(
            getClass().getClassLoader().getResource("爷爷.png")).getImage();

    public Image getImage() {
        return image;
    }
}
