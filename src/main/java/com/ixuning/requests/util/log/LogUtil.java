package com.ixuning.requests.util.log;

import com.ixuning.requests.core.Config;
import com.ixuning.requests.request.RequestClient;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Created by xuning on 2016/8/17.
 */
public final class LogUtil {
    private static LogUtil logUtil = new LogUtil();
    public FileHandler fileHeandler;
    Logger logger;
    private LogUtil() {
    }

    public static LogUtil getLogUtil() {
        return logUtil;
    }

    public void createLogFolder(String log) {
        hashCode();
        if (!Config.LOG_PATH.equals("") && null != Config.LOG_PATH && (!(Config.OPEN_LOG_OUTPUT == false))) {
            File file = new File(Config.LOG_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        try {
            fileHeandler = new FileHandler(Config.LOG_PATH + LogUtil.formateTime(System.currentTimeMillis(), "yyyy_MM_dd") + ".log", true);
            fileHeandler.setLevel(Level.INFO);
            fileHeandler.setFormatter(new MyLogHander());
            logger = Logger.getLogger("requestClient");
            logger.setLevel(Level.INFO);
            logger.addHandler(fileHeandler);
            logger.info(log);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            fileHeandler.close();
        }
    }

    public final void printLog(String log) {
        Logger logger = Logger.getLogger(RequestClient.class.getName());
        if (Config.OPEN_LOG_PRINT) {
            logger.info(log);
        }
        if (Config.OPEN_LOG_OUTPUT) {
            createLogFolder(log);
        }
    }

    public static final String formateTime(long milliseconds, String timeFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
        Date date = new Date(milliseconds);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String time = formatter.format(date);
        return time;
    }

    class MyLogHander extends Formatter {
        @Override
        public String format(LogRecord record) {
            return record.getLevel() + ":" + record.getMessage() + "\r\n";
        }
    }
}
