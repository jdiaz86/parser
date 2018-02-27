package com.ef.util;

import java.text.SimpleDateFormat;

/**
 * Constant class for Parser application
 * @author jdiaz86
 */
public final class Const {
    
    public static final SimpleDateFormat SDF_PARAM = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");
    public static final SimpleDateFormat SDF_LOG = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public static final String THRESHOLD_PARAM = "--threshold=";
    public static final String DURATION_PARAM = "--duration=";
    public static final String START_DATE_PARAM = "--startDate=";
    public static final String ACCESS_LOG_PARAM = "--accessLog=";
    public static final String EMPTY = "";
    public static final String DELIMITER = "\\|";
    public static final String EXCEPTION_IN_DATABASE = "Exception in database, cannot recover -> ";
    
}
