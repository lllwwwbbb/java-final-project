package cs.lwb.huluwa.debug;

public class Logger {
    private static Logger logger = new Logger();
    public static Logger getLogger() {
        return logger;
    }

    Logger() {

    }

    public void writeLog(String msg) {
        System.out.println(msg);
    }
}
