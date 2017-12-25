package cs.lwb.gui;

import cs.lwb.huluwa.Creature;
import cs.lwb.huluwa.God;
import cs.lwb.huluwa.Location;

import javax.swing.*;
import java.awt.*;

public class WarPanel extends JPanel {
    God god;

    public WarPanel(int width, int height, God god) {
        setPreferredSize(new Dimension(width, height));
        this.god = god;
        god.setJcanvas(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int gridW = getWidth() / god.groundWidth, gridH = getHeight() / god.groundHeight;
        for (int i = 0; i < god.groundWidth; i ++) {
            for (int j = 0; j < god.groundWidth; j ++) {
                Creature creature = god.getCreature(new Location(i, j));
                if (creature != null) {
                    g.fillRect(gridW * i, gridH * j, gridW, gridH);
                    g.drawImage(creature.getImage(), gridW * i, gridH * i, gridW, gridH,
                            new Color(0,0,0,0), this);
                }
            }
        }
    }
}
