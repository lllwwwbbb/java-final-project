package cs.lwb.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    private final int WIDTH = 600, HEIGHT = 400;
    public MainWindow() {
        setSize(WIDTH, HEIGHT);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - WIDTH)/2, (screenSize.height - HEIGHT)/2);
        setTitle("葫芦娃大战蛇蝎精");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new WarPanel());
    }
}
