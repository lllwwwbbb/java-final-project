package cs.lwb.huluwa;

import javax.swing.*;
import java.awt.*;

public class SheJing extends Creature {

    public SheJing(God god, Location location) {
        super(god, Faction.BAD, location, "蛇精", 800);
    }

    protected void onTick() {
        moveTo(getLocation().getNext(-1,0));
    }

    private Image image = new ImageIcon(
            getClass().getClassLoader().getResource("蛇精.png")).getImage();

    public Image getImage() {
        return image;
    }
}
