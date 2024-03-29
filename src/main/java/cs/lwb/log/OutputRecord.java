package cs.lwb.log;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OutputRecord {

    ObjectOutputStream oos;
    File file;

    public OutputRecord() throws IOException {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("HH-mm-ss_(yyyy.MM.dd)");
        file = new File(System.getProperty("user.dir") + "/" + ft.format(date) + ".huluwa");
        oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
    }

    public synchronized void write(Object o) {
        try {
            oos.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File close() {
        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
