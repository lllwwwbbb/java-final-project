package cs.lwb.gui;

import cs.lwb.huluwa.God;
import cs.lwb.log.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class MainFrame extends JFrame {
    private boolean started = false;
    private final WarPanel wp;

    public MainFrame() {
        int width = 900, height = 600;
        wp = new WarPanel(width, height);
        add(wp, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setTitle("葫芦娃大战蛇蝎精");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (wp.isRunning())
                    return;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE: wp.startWithOutput(); break;
                    case KeyEvent.VK_L: {
                        JFileChooser jfc = new JFileChooser();
                        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        File workingDirectory = new File(System.getProperty("user.dir"));
                        jfc.setCurrentDirectory(workingDirectory);
                        jfc.showDialog(new JLabel(), "选择");
                        File file = jfc.getSelectedFile();
                        wp.startWithInput(file);
                    } break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
}
