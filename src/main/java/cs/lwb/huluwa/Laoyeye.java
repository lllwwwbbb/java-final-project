package cs.lwb.huluwa;

import javax.swing.*;
import java.awt.*;

public class Laoyeye extends Creature {
    private Image image = new ImageIcon(
            getClass().getClassLoader().getResource("爷爷.png")).getImage();

    public Laoyeye(God god, Location location) {
        super(god, Faction.GOOD, location, "爷爷", 2000);
    }

    protected void onTick() {
        moveTo(getLocation().getNext(1,1));
    }

    public Image getImage() {
        return image;
    }
}
