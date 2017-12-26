package cs.lwb.huluwa;

import javax.swing.*;
import java.awt.*;

public class SiWa extends Creature {

    public SiWa(God god, Location location) {
        super(god, Faction.GOOD, location, "四娃", 1000);
    }

    protected void onTick() {
        moveTo(getLocation().getNext(1,0));
    }

    Image image = new ImageIcon(
            getClass().getClassLoader().getResource("四娃.png")).getImage();

    public Image getImage() {
        return image;
    }
}