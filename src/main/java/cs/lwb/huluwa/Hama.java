package cs.lwb.huluwa;

import javax.swing.*;
import java.awt.*;

public class Hama extends Creature {

    public Hama(God god, Location location) {
        super(god, Faction.BAD, location, "蛤蟆", 700);
    }

    protected void onTick() {
        moveTo(getLocation().getNext(-1,0));
    }

    private Image image = new ImageIcon(
            getClass().getClassLoader().getResource("蛤蟆.png")).getImage();

    public Image getImage() {
        return image;
    }
}
