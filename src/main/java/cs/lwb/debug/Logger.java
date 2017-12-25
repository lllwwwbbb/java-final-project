package cs.lwb.debug;

public class Logger {
    private static Logger logger = new Logger();
    public static Logger getLogger() {
        return logger;
    }

    private Logger() {

    }

    public void writeLog(String msg) {
        System.out.println(msg);
    }
}
