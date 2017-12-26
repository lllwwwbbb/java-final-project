package cs.lwb.huluwa;

import javax.swing.*;
import java.awt.*;

public class QiWa extends Creature {

    public QiWa(God god, Location location) {
        super(god, Faction.GOOD, location, "七娃", 1000);
    }

    protected void onTick() {
        moveTo(getLocation().getNext(1,0));
    }

    Image image = new ImageIcon(
            getClass().getClassLoader().getResource("七娃.png")).getImage();

    public Image getImage() {
        return image;
    }
}