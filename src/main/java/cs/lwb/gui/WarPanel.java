package cs.lwb.gui;

import com.sun.istack.internal.NotNull;
import cs.lwb.debug.Logger;
import cs.lwb.huluwa.Attack;
import cs.lwb.huluwa.Creature;
import cs.lwb.huluwa.God;
import cs.lwb.huluwa.Location;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WarPanel extends JPanel {
    private God god;

    public WarPanel(int width, int height, @NotNull God god) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.CYAN);
        this.god = god;
        god.setJcanvas(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int gridW = getWidth() / god.groundWidth, gridH = getHeight() / god.groundHeight;
        for (int i = 0; i < god.groundWidth; i ++) {
            for (int j = 0; j < god.groundWidth; j ++) {
                Drawable creature = god.getCreature(new Location(i, j));
                if (creature != null) {
                    g.drawImage(creature.getImage(), gridW * i, gridH * j, gridW, gridH,null);
                }
            }
        }
        List<? extends Drawable> attackList = god.getAttacks();
        for (int i = 0; i < attackList.size(); i ++) {
            g.drawImage(attackList.get(i).getImage(), 0, 0, getWidth(), getHeight(), null);
        }
    }
}
