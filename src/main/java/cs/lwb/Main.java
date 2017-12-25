package cs.lwb;

import cs.lwb.debug.Logger;
import cs.lwb.gui.MainWindow;
import cs.lwb.huluwa.Ground;

public class Main {
    public static void main(String[] args) {
        new MainWindow();
        Logger logger = Logger.getLogger();
        logger.writeLog("GAME START!");
    }
}
