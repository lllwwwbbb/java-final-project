package cs.lwb.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    public MainWindow() {
        int width = 600, height = 400;
        add(new WarPanel(width, height), BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setTitle("葫芦娃大战蛇蝎精");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
