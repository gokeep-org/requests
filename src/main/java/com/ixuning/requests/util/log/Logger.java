package com.ixuning.requests.util.log;

import com.ixuning.requests.core.Config;
import org.slf4j.helpers.MessageFormatter;

/**
 * Created by xuning on 2016/9/12.
 */
public class Logger {
    private org.slf4j.Logger myLogger;

    public Logger(org.slf4j.Logger myLogger) {
        this.myLogger = myLogger;
    }

    public boolean isTraceEnabled() {
        return this.myLogger.isTraceEnabled();
    }

    public boolean isDebugEnabled() {
        return this.myLogger.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return this.myLogger.isInfoEnabled();
    }

    public boolean isWarnEnabled() {
        return this.myLogger.isWarnEnabled();
    }

    public boolean isErrorEnabled() {
        return this.myLogger.isErrorEnabled();
    }

    private String getMessage(String message) {
        return message;
    }

    private String getMessage(String format, Object... args) {
        return MessageFormatter.arrayFormat(format, args).getMessage();
    }

    public void trace(String message) {
        if (this.isTraceEnabled()) {
            this.myLogger.trace(this.getMessage(message));
        }

    }

    public void trace(String message, Throwable e) {
        if (this.isTraceEnabled()) {
            this.myLogger.trace(this.getMessage(message), e);
        }

    }

    public void trace(String format, Object... args) {
        if (this.isTraceEnabled()) {
            this.myLogger.trace(this.getMessage(format, args));
        }

    }

    public void trace(Throwable e, String format, Object... args) {
        if (this.isTraceEnabled()) {
            this.myLogger.trace(this.getMessage(format, args), e);
        }

    }

    public void debug(String message) {
        if (this.isDebugEnabled() && Config.OPEN_DEBUG) {
            this.myLogger.debug(this.getMessage(message));
        }

    }

    public void debug(String message, Throwable e) {
        if (this.isDebugEnabled() && Config.OPEN_DEBUG) {
            this.myLogger.debug(this.getMessage(message), e);
        }

    }

    public void debug(String format, Object... args) {
        if (this.isDebugEnabled() && Config.OPEN_DEBUG) {
            this.myLogger.debug(this.getMessage(format, args));
        }

    }

    public void debug(Throwable e, String format, Object... args) {
        if (this.isDebugEnabled() && Config.OPEN_DEBUG) {
            this.myLogger.debug(this.getMessage(format, args), e);
        }

    }

    public void info(String message) {
        if (this.isInfoEnabled()) {
            this.myLogger.info(this.getMessage(message));
        }

    }

    public void info(String message, Throwable e) {
        if (this.isInfoEnabled()) {
            this.myLogger.info(this.getMessage(message), e);
        }

    }

    public void info(String format, Object... args) {
        if (this.isInfoEnabled()) {
            this.myLogger.info(this.getMessage(format, args));
        }

    }

    public void info(Throwable e, String format, Object... args) {
        if (this.isInfoEnabled()) {
            this.myLogger.info(this.getMessage(format, args), e);
        }

    }

    public void warn(String message) {
        if (this.isWarnEnabled()) {
            this.myLogger.warn(this.getMessage(message));
        }

    }

    public void warn(String message, Throwable e) {
        if (this.isWarnEnabled()) {
            this.myLogger.warn(this.getMessage(message), e);
        }

    }

    public void warn(String format, Object... args) {
        if (this.isWarnEnabled()) {
            this.myLogger.warn(this.getMessage(format, args));
        }

    }

    public void warn(Throwable e, String format, Object... args) {
        if (this.isWarnEnabled()) {
            this.myLogger.warn(this.getMessage(format, args), e);
        }

    }

    public void error(String message) {
        if (this.isErrorEnabled()) {
            this.myLogger.error(this.getMessage(message));
        }

    }

    public void error(String message, Throwable e) {
        if (this.isErrorEnabled()) {
            this.myLogger.error(this.getMessage(message), e);
        }

    }

    public void error(String format, Object... args) {
        if (this.isErrorEnabled()) {
            this.myLogger.error(this.getMessage(format, args));
        }

    }

    public void error(Throwable e, String format, Object... args) {
        if (this.isErrorEnabled()) {
            this.myLogger.error(this.getMessage(format, args), e);
        }

    }

}
