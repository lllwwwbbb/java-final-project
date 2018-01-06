package cs.lwb.log;

import java.io.*;

public class InputRecord {

    private ObjectInputStream ois;

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
