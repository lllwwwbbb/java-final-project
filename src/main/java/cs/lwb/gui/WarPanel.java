package cs.lwb.gui;

import cs.lwb.huluwa.God;
import cs.lwb.huluwa.GodWithInputRecord;
import cs.lwb.huluwa.GodWithOutputRecord;
import cs.lwb.huluwa.Location;
import cs.lwb.log.InputRecord;
import cs.lwb.log.OutputRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class WarPanel extends JPanel {
    private boolean init = true;
    private God god;

    boolean isRunning() {
        return !init && god.isRunning();
    }

    public WarPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.CYAN);
    }

    public void startWithOutput() {
        if (!isRunning()) {
            OutputRecord or = null;
            try {
                or = new OutputRecord();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.god = new GodWithOutputRecord(or, this);
            god.start();
            init = false;
        }
    }

    public void startWithInput(File file) {
        if (!isRunning()) {
            InputRecord ir = null;
            try {
                ir = new InputRecord(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.god = new GodWithInputRecord(ir, this);
            god.start();
            init = false;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (god != null)
            god.paint(g);
    }
}
