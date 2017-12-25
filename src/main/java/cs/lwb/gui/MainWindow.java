package cs.lwb.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{

    public MainWindow() {
        setSize(600, 400);
        setLocation(300, 300);
        setTitle("葫芦娃大战蛇蝎精");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(new WarPanel());
    }
}
