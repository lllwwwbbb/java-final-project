package cs.lwb.huluwa;

import javax.swing.*;
import java.awt.*;

public class LiuWa extends Creature {

    public LiuWa(God god, Location location) {
        super(god, Faction.GOOD, location, "六娃", 1000);
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