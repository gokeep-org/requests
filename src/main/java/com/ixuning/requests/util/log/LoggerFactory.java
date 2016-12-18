package com.ixuning.requests.util.log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xuning on 2016/8/28.
 */
public final class LoggerFactory {
    private static Map<String, Logger> loggers = new ConcurrentHashMap();

    public LoggerFactory() {
    }

    public static Logger getLogger(String key) {
        Logger logger = (Logger)loggers.get(key);
        if(logger == null) {
            Class var2 = LoggerFactory.class;
            synchronized(LoggerFactory.class) {
                logger = (Logger)loggers.get(key);
                if(logger == null) {
                    logger = new Logger(org.slf4j.LoggerFactory.getLogger(key));
                    loggers.put(key, logger);
                }
            }
        }

        return logger;
    }

    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

}
