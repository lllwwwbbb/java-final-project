package cs.lwb.gui;

import cs.lwb.debug.Logger;
import cs.lwb.huluwa.Space;

import javax.swing.*;
import java.awt.*;

public class WarPanel extends JPanel {
    private final int width;
    private final int height;
    Space space = new Space();

    public WarPanel(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
