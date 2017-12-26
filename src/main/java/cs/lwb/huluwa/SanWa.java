package cs.lwb.huluwa;

import javax.swing.*;
import java.awt.*;

public class SanWa extends Creature {

    public SanWa(God god, Location location) {
        super(god, Faction.GOOD, location, "三娃", 1000);
    }

    protected void onTick() {
        moveTo(getLocation().getNext(1,0));
    }

    Image image = new ImageIcon(
            getClass().getClassLoader().getResource("三娃.png")).getImage();

    public Image getImage() {
        return image;
    }
}