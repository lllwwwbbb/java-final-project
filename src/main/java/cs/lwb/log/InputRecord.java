package cs.lwb.log;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InputRecord {

    private ObjectInputStream ois;// = new ObjectInputStream(new BufferedInputStream(new FileInputStream(
            //"C:/Users/luowebb/OneDrive - smail.nju.edu.cn/Desktop/output.record")));

    public InputRecord(File file) throws IOException {
        ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));

    }

    public synchronized  Object read() {
        Object o = null;
        try {
            o = ois.readObject();
        } catch (EOFException e) {
            Logger.writeLog("END");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    public void close() {
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
