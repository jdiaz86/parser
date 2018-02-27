package com.ef.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * util class to handle log outputs in Parser application
 * @author jdiaz86
 */
public final class LogUtil {

    private static Logger getLogger() {
        String callingClassName = Thread.currentThread().getStackTrace()[3].getClassName();
        return LoggerFactory.getLogger(callingClassName);
    }
    
    public static void info(String message) {
        getLogger().info(message);
    }
    
    public static void info(String message, Object... o) {
        getLogger().info(message, o);
    }
    
    public static void error(String message) {
        getLogger().error(message);
    }
    
    public static void error(String message, Object... o) {
        getLogger().error(message, o);
    }

}
