package logger;

import java.io.IOException;
import java.util.logging.*;


public final class MyLogger {

    private static Handler handler;
    private static Logger logger;

    /**
     * This is use of singleton design pattern. This is to ensure that only one instance of this class can
     * be created. It is necessary to just have one logger.
     */

    private MyLogger() {}

    /**
     * Creates an instance of logger and handler
     * @return a static instance of logger. This is use of singleton design pattern.
     */
    public static Logger getLogger() {
        try {
            if(logger == null) {
                logger = Logger.getLogger(MyLogger.class.getName());
                //Creates a filehandler that streams all the lgo into a log file
                handler = new FileHandler("log.log", true);
                //A simpleformatter changes all the log format from XML to a format that is readable for the human.
                handler.setFormatter(new SimpleFormatter());
                //Sets the bar in which the logs will be logged or handled.
                handler.setLevel(Level.ALL);

                logger.addHandler(handler);
                logger.setLevel(Level.ALL);
            }
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
        return logger;
    }

    /**
     * Closes the handler after something has been logged.
     * This is necessary for not creating multiple log files.
     */
    public static void closeHandler() {
        handler.close();
    }
}
