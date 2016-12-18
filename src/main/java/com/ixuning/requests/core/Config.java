package com.ixuning.requests.core;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import com.ixuning.requests.bean.exception.RequestsForXNException;
import com.ixuning.requests.util.PropertiesUtil;
import org.apache.http.HttpVersion;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.Charset;

public class Config {
    private Config() {

    }

    private static Config config = new Config();

    public static Config getConfig() {
        return config;
    }

    //auth--
    public static int REFRESH_TOKEN_COUNT = 2;

    //system
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final Charset DEFAULT_CHARSET_TYPE = Charset.defaultCharset();

    //simple log
    public static boolean OPEN_LOG_PRINT = true;
    public static boolean OPEN_LOG_OUTPUT = true;
    public static boolean OPEN_DEBUG = false;

    public static String WIN_LOG_DIR = "/var/open-api-sdk/log/";
    public static String LINUX_LOG_DIR = "C:/var/open-api-sdk/log/";
    public static final String LOG_PATH = PropertiesUtil.isLinux() ? LINUX_LOG_DIR : WIN_LOG_DIR;

    //request
    public static final HttpVersion HTTP_VERSION = HttpVersion.HTTP_1_1;
    public static final int DEFAULT_SOCKET_TIMOUT_CHARSET = 60000;
    public static final int DEFAULT_CONNECTION_TIMOUT = 60000;
    public static final String DEFAULT_REQUEST_CHARSET = "UTF-8";
    public static final String DEFAULT_CONTENT_TYPE = "application/json";
    public static final String DEFAULT_CONTENT_VERSION_TYPE = "application/v1+json";

    //request method
    public static final String METHOD_GET = "get";
    public static final String METHOD_POST = "post";
    public static final String METHOD_PUT = "put";
    public static final String METHOD_DELETE = "delete";


    public static void openDebug(){
        OPEN_DEBUG=true;
    }

    public static void customLogPath(String path){
        File logbackFile = new File(path);
        if (logbackFile.exists()) {
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(lc);
            lc.reset();
            try {
                configurator.doConfigure(logbackFile);
            }
            catch (JoranException e) {
                throw new RequestsForXNException(e.getMessage());
            }
        }
    }
}
